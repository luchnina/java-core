package ru.makhonya.javalearn.payment.bank;


import ru.makhonya.javalearn.payment.exception.technical.NetworkException;
import ru.makhonya.javalearn.payment.transaction.Card;
import ru.makhonya.javalearn.payment.transaction.Money;
import ru.makhonya.javalearn.payment.transaction.Transaction;

import java.util.Random;

/**
 * Прокси-класс для взаимодействия с банком через сеть.
 * Обеспечивает механизм повторных попыток при сетевых сбоях
 * и эмулирует нестабильное сетевое соединение (85% успеха).
 */
public class NetworkBank implements Bank {
    private final Bank realBank;
    private final int maxRetries = 3;

    Random random = new Random();

    public NetworkBank(Bank realBank) {
        this.realBank = realBank;
    }

    /**
     * Открывает транзакцию с повторными попытками при сетевых сбоях.
     *
     * @param terminalId ID терминала (источник запроса)
     * @param card номер карты клиента
     * @return объект транзакции
     * @throws NetworkException если все попытки соединения неудачны
     */
    @Override
    public Transaction openTransaction(String terminalId, Card card) {

        for (int i = 0; i < maxRetries; i++) {
            if (isConnectionSuccess()) {
                return realBank.openTransaction(terminalId, card);
            }
        }

        throw new NetworkException();
    }

    /**
     * Получает баланс счета по идентификатору транзакции.
     *
     * @param transactionId ID активной транзакции
     * @return текущий баланс счета
     * @throws NetworkException при сбоях сетевого соединения
     */
    @Override
    public Money getBalance(String transactionId) {

        for (int i = 0; i < maxRetries; i++) {
            if (isConnectionSuccess()) {
                return realBank.getBalance(transactionId);
            }
        }

        throw new NetworkException();
    }

    /**
     * Замораживает средства на счете клиента.
     *
     * @param transactionId идентификатор транзакции
     * @param amount сумма для заморозки
     * @throws NetworkException при сбоях сетевого соединения
     */
    @Override
    public void freeze(String transactionId, Money amount) {

        for (int i = 0; i < maxRetries; i++) {
            if (isConnectionSuccess()) {
                realBank.freeze(transactionId, amount);
                return;
            }
        }

        throw new NetworkException();
    }

    /**
     * Подтверждает и завершает транзакцию.
     *
     * @param transactionId ID завершаемой транзакции
     * @throws NetworkException при сбоях сетевого соединения
     */
    @Override
    public void commit(String transactionId) {

        // Всегда выполняем коммит локально/гарантированно
        realBank.commit(transactionId);

        for (int i = 0; i < maxRetries; i++) {
            if (isConnectionSuccess()) {
                // Сеть работает - синхронизация прошла успешно
                return;
            }
            // При неудаче продолжаем попытки, но НЕ бросаем исключение
        }
    }

    private boolean isConnectionSuccess() {
        int d100 = random.nextInt(100);
        return d100 <= 85;
    }
}
