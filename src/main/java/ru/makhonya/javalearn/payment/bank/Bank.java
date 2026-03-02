package ru.makhonya.javalearn.payment.bank;

import ru.makhonya.javalearn.payment.exception.technical.NetworkException;
import ru.makhonya.javalearn.payment.exception.technical.TransactionFailedException;
import ru.makhonya.javalearn.payment.transaction.Card;
import ru.makhonya.javalearn.payment.transaction.Money;
import ru.makhonya.javalearn.payment.transaction.Transaction;

public interface Bank {
    /**
     * @param terminalId ID терминал (источник запроса)\
     * @param card номер карты
     * @return открытая транзакция
     * @throws TransactionFailedException если не удалось открыть транзакцию
     */
    Transaction openTransaction(String terminalId, Card card);

    /**
     * @param transactionId ID транзакции (контекст запроса)
     * @return текущий доступ баланс
     * @throws NetworkException если запрос не прошел на получение денег
     */
    Money getBalance(String transactionId);

    /**
     * @param transactionId ID транзакции (контекст запроса)
     * @param amount сумма для заморозки на оплату
     * @throws NetworkException если запрос не прошел
     * @throws IllegalArgumentException если на балансе не хватает денег
     */
    void freeze(String transactionId, Money amount);

    /**
     * @param transactionId ID транзакции (контекст запроса)
     * @throws NetworkException если запрос не прошел (но изменения применены)
     */
    void commit(String transactionId);
}
