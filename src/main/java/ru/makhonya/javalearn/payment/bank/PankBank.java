package ru.makhonya.javalearn.payment.bank;

import ru.makhonya.javalearn.payment.Transaction;
import ru.makhonya.javalearn.payment.exception.business.CardNotFoundException;
import ru.makhonya.javalearn.payment.exception.business.InsufficientFundsException;
import ru.makhonya.javalearn.payment.exception.technical.TransactionLimitExceededException;
import ru.makhonya.javalearn.payment.exception.technical.TransactionNotFoundException;
import ru.makhonya.javalearn.payment.transaction.*;

public class PankBank implements Bank {

	private static final int MAX_CARDS = 100;
	private static final int MAX_TRANSACTIONS = 1000;

	// "Таблица" карт
	private final CardNumber[] cardNumbers = new CardNumber[MAX_CARDS];
	private final Money[] cardBalances = new Money[MAX_CARDS];
	private int cardsCount = 0;

	// "Таблица" транзакций
	private final TransactionId[] txIds = new TransactionId[MAX_TRANSACTIONS];
	private final TerminalId[] txTerminalIds = new TerminalId[MAX_TRANSACTIONS];
	private final CardNumber[] txCardNumbers = new CardNumber[MAX_TRANSACTIONS];
	private final Money[] txAmounts = new Money[MAX_TRANSACTIONS];
	private final TransactionStatus[] txStatuses = new TransactionStatus[MAX_TRANSACTIONS];
	private int transactionsCount = 0;

	private int nextTxNumericId = 1;

	/**
	 * Создаёт банк с одной картой и начальными деньгами на ней.
	 *
	 * @param cardNumber номер карты, которая будет доступна в банке
	 * @param initialBalance начальный баланс карты
	 */
	public PankBank(CardNumber cardNumber, Money initialBalance) {
		addCard(cardNumber, initialBalance);
	}

	/**
	 * Создаёт банк без карт.
	 * Карты можно добавить позже через {@link #addCard(CardNumber, Money)}.
	 */
	public PankBank() {

	}

	/**
	 * Добавляет новую карту во внутренний список банка.
	 *
	 * @param cardNumber номер карты, по которому будут проходить транзакции
	 * @param initialBalance начальный баланс этой карты
	 * @throws IllegalStateException если превышен лимит MAX_CARDS
	 */
	public void addCard(CardNumber cardNumber, Money initialBalance) {
		if (cardsCount >= MAX_CARDS) {
			throw new IllegalStateException("Слишком много карт");
		}

		cardNumbers[cardsCount] = cardNumber;
		cardBalances[cardsCount] = initialBalance;
		cardsCount++;
	}

	/**
	 * Открывает новую транзакцию для указанного терминала и карты.
	 * Транзакция создаётся со статусом OPEN и нулевой замороженной суммой.
	 *
	 * @param terminalId идентификатор терминала, который инициировал транзакцию
	 * @param cardNumber номер карты, с которой будет происходить списание
	 * @return созданная транзакция с присвоенным TransactionId
	 * @throws CardNotFoundException если карта с таким номером не найдена в банке
	 * @throws TransactionLimitExceededException если достигнут лимит по количеству транзакций
	 */
	@Override
	public Transaction openTransaction(TerminalId terminalId, CardNumber cardNumber) {
		int cardIndex = findCardIndex(cardNumber);
		if (cardIndex == -1) {
			throw new IllegalArgumentException("Неизвестная карта: " + cardNumber.maskedNumber());
		}

		if (transactionsCount >= MAX_TRANSACTIONS) {
			throw new IllegalStateException("Слишком много транзакций");
		}

		String txStringId = "TX" + nextTxNumericId++;
		TransactionId txId = new TransactionId(txStringId);

		txIds[transactionsCount] = txId;
		txTerminalIds[transactionsCount] = terminalId;
		txCardNumbers[transactionsCount] = cardNumber;
		txAmounts[transactionsCount] = Money.ZERO;
		txStatuses[transactionsCount] = TransactionStatus.OPEN;

		Transaction tx = new Transaction(
				terminalId,
				txId,
				cardNumber,
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
	public Money getBalance(TransactionId transactionId) {
		int txIndex = findOpenTransactionIndex(transactionId);
		int cardIndex = findCardIndex(txCardNumbers[txIndex]);

		Money total = cardBalances[cardIndex];
		Money frozen = getTotalFrozenAmount(txCardNumbers[txIndex]);

		int available = total.amountInKopecks() - frozen.amountInKopecks();
		if (available < 0) available = 0;

		return new Money(available);
	}

	/**
	 * Возвращает "сырой" баланс карты без учёта замороженных сумм.
	 * Используется в основном для тестов и демонстрации работы банка.
	 *
	 * @param cardNumber номер карты, баланс которой нужно узнать
	 * @return текущий сохранённый баланс карты; если карта не найдена, возвращается Money.ZERO
	 */
	public Money getRawBalance(CardNumber cardNumber) {
		int cardIndex = findCardIndex(cardNumber);
		if (cardIndex == -1) {
			return Money.ZERO;
		}
		return cardBalances[cardIndex];
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
	public void freeze(TransactionId transactionId, Money amount) {
		int txIndex = findOpenTransactionIndex(transactionId);
		CardNumber cardNumber = txCardNumbers[txIndex];

		Money balance = getBalance(transactionId);
		if (!balance.canCover(amount)) {
			throw new InsufficientFundsException(cardNumber, balance, amount);
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
	public void commit(TransactionId transactionId) {
		int txIndex = findOpenTransactionIndex(transactionId);
		Money amount = txAmounts[txIndex];
		if (amount.amountInKopecks() == 0) {
			txStatuses[txIndex] = TransactionStatus.COMMITTED;
			return;
		}

		CardNumber cardNumber = txCardNumbers[txIndex];
		int cardIndex = findCardIndex(cardNumber);

		Money current = cardBalances[cardIndex];
		cardBalances[cardIndex] = current.minus(amount);

		txStatuses[txIndex] = TransactionStatus.COMMITTED;
	}

	/**
	 * Находит индекс карты во внутреннем массиве по её номеру.
	 *
	 * @param cardNumber номер карты, которую нужно найти
	 * @return индекс карты в массиве или -1, если карта не найдена
	 */
	private int findCardIndex(CardNumber cardNumber) {
		for (int i = 0; i < cardsCount; i++) {
			if (cardNumbers[i].maskedNumber().equals(cardNumber.maskedNumber())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Находит индекс открытой транзакции по её идентификатору.
	 * Проверяет, что транзакция существует и имеет статус OPEN.
	 *
	 * @param transactionId идентификатор транзакции
	 * @return индекс транзакции во внутренних массивах
	 * @throws TransactionNotFoundException если транзакция не найдена или уже закрыта (COMMITTED)
	 */
	private int findOpenTransactionIndex(TransactionId transactionId) {
		for (int i = 0; i < transactionsCount; i++) {
			if (txIds[i].terminalId().equals(transactionId.terminalId())
					&& txStatuses[i] == TransactionStatus.OPEN) {
				return i;
			}
		}

		throw new TransactionNotFoundException(transactionId);
	}


	/**
	 * Считает суммарную замороженную сумму по всем открытым транзакциям указанной карты.
	 *
	 * @param cardNumber номер карты, по которой нужно посчитать замороженные суммы
	 * @return сумма всех txAmounts для этой карты со статусом OPEN
	 */
	private Money getTotalFrozenAmount(CardNumber cardNumber) {
		int sum = 0;
		for (int i = 0; i < transactionsCount; i++) {
			if (txCardNumbers[i].maskedNumber().equals(cardNumber.maskedNumber())
					&& txStatuses[i] == TransactionStatus.OPEN) {
				sum += txAmounts[i].amountInKopecks();
			}
		}
		return new Money(sum);
	}
}
