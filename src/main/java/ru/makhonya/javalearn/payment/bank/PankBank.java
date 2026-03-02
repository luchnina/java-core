package ru.makhonya.javalearn.payment.bank;

import ru.makhonya.javalearn.payment.exception.business.CardNotFoundException;
import ru.makhonya.javalearn.payment.exception.business.InsufficientFundsException;
import ru.makhonya.javalearn.payment.exception.technical.TransactionLimitExceededException;
import ru.makhonya.javalearn.payment.exception.technical.TransactionNotFoundException;
import ru.makhonya.javalearn.payment.transaction.Card;
import ru.makhonya.javalearn.payment.transaction.Money;
import ru.makhonya.javalearn.payment.transaction.Transaction;
import ru.makhonya.javalearn.payment.transaction.TransactionStatus;

public class PankBank implements Bank {

  private static final int MAX_TRANSACTIONS = 1000;

  private Card card;
  private Money cardBalance;

  // Массивы транзакций (для одной карты)
  private final String[] txIds = new String[MAX_TRANSACTIONS];
  private final String[] txTerminalIds = new String[MAX_TRANSACTIONS];
  private final Money[] txAmounts = new Money[MAX_TRANSACTIONS];
  private final TransactionStatus[] txStatuses = new TransactionStatus[MAX_TRANSACTIONS];
  private int transactionsCount = 0;

  private int nextTxNumericId = 1;

  /**
   * Создаёт банк с одной картой и начальными деньгами на ней.
   *
   * @param card номер карты, которая будет доступна в банке
   * @param initialBalance начальный баланс карты
   */
  public PankBank(Card card, Money initialBalance) {
    this.card = card;
    this.cardBalance = initialBalance;
  }

  /**
   * Создаёт банк без карты.
   * Карта может быть добавлена только один раз через {@link #addCard(Card, Money)}.
   *
   * @throws IllegalStateException если карта уже добавлена
   */
  public PankBank() {
    this.card = null;
    this.cardBalance = null;
  }

  /**
   * Добавляет карту во внутреннее состояние банка.
   * Можно вызвать только один раз.
   *
   * @param card номер карты, по которому будут проходить транзакции
   * @param initialBalance начальный баланс этой карты
   * @throws IllegalStateException если карта уже добавлена
   */
  public void addCard(Card card, Money initialBalance) {
    if (this.card != null) {
      throw new IllegalStateException("Карта уже добавлена");
    }
    this.card = card;
    this.cardBalance = initialBalance;
  }

  /**
   * Открывает новую транзакцию для указанного терминала и карты.
   * Транзакция создаётся со статусом OPEN и нулевой замороженной суммой.
   *
   * @param terminalId идентификатор терминала, который инициировал транзакцию
   * @param card номер карты, с которой будет происходить списание
   * @return созданная транзакция с присвоенным TransactionId
   * @throws CardNotFoundException если переданная карта не совпадает с картой банка
   * @throws TransactionLimitExceededException если достигнут лимит по количеству транзакций
   */
  @Override
  public Transaction openTransaction(String terminalId, Card card) {
    if (!this.card.maskedNumber().equals(card.maskedNumber())) {
      throw new CardNotFoundException(card);
    }

    if (transactionsCount >= MAX_TRANSACTIONS) {
      throw new TransactionLimitExceededException();
    }

    String txId = "TX" + nextTxNumericId++;

    txIds[transactionsCount] = txId;
    txTerminalIds[transactionsCount] = terminalId;
    txAmounts[transactionsCount] = Money.ZERO;
    txStatuses[transactionsCount] = TransactionStatus.OPEN;

    Transaction tx = new Transaction(
        terminalId,
        txId,
        card,
        Money.ZERO,
        TransactionStatus.OPEN
    );

    transactionsCount++;
    return tx;
  }

  /**
   * Возвращает доступный баланс для указанной транзакции.
   * Доступный баланс = сырой баланс карты - все замороженные суммы по другим открытым транзакциям этой карты.
   *
   * @param transactionId идентификатор транзакции, для которой нужно узнать баланс
   * @return доступный баланс по карте, связанной с этой транзакцией
   * @throws TransactionNotFoundException если транзакция не найдена или уже закрыта (COMMITTED)
   */
  @Override
  public Money getBalance(String transactionId) {

    Money total = cardBalance;
    Money frozen = getTotalFrozenAmount();

    int available = total.amountInKopecks() - frozen.amountInKopecks();
    if (available < 0) available = 0;

    return new Money(available);
  }

  /**
   * Возвращает "сырой" баланс карты без учёта замороженных сумм.
   * Используется в основном для тестов и демонстрации работы банка.
   *
   * @return текущий сохранённый баланс карты; если карта не добавлена, возвращается Money.ZERO
   */
  public Money getRawBalance() {
    if (card == null) {
      return Money.ZERO;
    }
    return cardBalance;
  }

  /**
   * Замораживает указанную сумму в рамках переданной транзакции.
   * После заморозки эта сумма учитывается как "занятая" и уменьшает доступный баланс.
   *
   * @param transactionId идентификатор транзакции, в которой нужно заморозить сумму
   * @param amount сумма, которую требуется заморозить
   * @throws TransactionNotFoundException если транзакция не найдена или уже закрыта
   * @throws InsufficientFundsException если доступного баланса карты недостаточно для заморозки
   */
  @Override
  public void freeze(String transactionId, Money amount) {
    int txIndex = findOpenTransactionIndex(transactionId);

    Money balance = getBalance(transactionId);
    if (!balance.canCover(amount)) {
      throw new InsufficientFundsException(card, balance, amount);
    }

    txAmounts[txIndex] = amount;
  }

  /**
   * Завершает транзакцию.
   * Если в транзакции есть замороженная сумма, она списывается с баланса карты,
   * после чего транзакция помечается как COMMITTED.
   *
   * @param transactionId идентификатор транзакции, которую нужно завершить
   * @throws TransactionNotFoundException если транзакция не найдена или уже закрыта
   */
  @Override
  public void commit(String transactionId) {
    int txIndex = findOpenTransactionIndex(transactionId);
    Money amount = txAmounts[txIndex];
    if (amount.amountInKopecks() == 0) {
      txStatuses[txIndex] = TransactionStatus.COMMITTED;
      return;
    }

    cardBalance = cardBalance.minus(amount);
    txStatuses[txIndex] = TransactionStatus.COMMITTED;
  }

  /**
   * Находит индекс открытой транзакции по её идентификатору.
   * Проверяет, что транзакция существует и имеет статус OPEN.
   *
   * @param transactionId идентификатор транзакции
   * @return индекс транзакции во внутренних массивах
   * @throws TransactionNotFoundException если транзакция не найдена или уже закрыта (COMMITTED)
   */
  private int findOpenTransactionIndex(String transactionId) {
    for (int i = 0; i < transactionsCount; i++) {
      if (txIds[i].equals(transactionId)
          && txStatuses[i] == TransactionStatus.OPEN) {
        return i;
      }
    }
    throw new TransactionNotFoundException(transactionId);
  }

  /**
   * Считает суммарную замороженную сумму по всем открытым транзакциям текущей карты.
   *
   * @return сумма всех txAmounts для этой карты со статусом OPEN
   */
  private Money getTotalFrozenAmount() {
    int sum = 0;
    for (int i = 0; i < transactionsCount; i++) {
      if (txStatuses[i] == TransactionStatus.OPEN) {
        sum += txAmounts[i].amountInKopecks();
      }
    }
    return new Money(sum);
  }
}