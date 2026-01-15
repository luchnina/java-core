package ru.makhonya.javalearn.payment;

import ru.makhonya.javalearn.payment.exception.technical.NetworkException;
import ru.makhonya.javalearn.payment.exception.technical.TransactionFailedException;
import ru.makhonya.javalearn.payment.transaction.CardNumber;
import ru.makhonya.javalearn.payment.transaction.Money;
import ru.makhonya.javalearn.payment.transaction.TerminalId;
import ru.makhonya.javalearn.payment.transaction.TransactionId;

public interface Bank {
	/**
	 * @param terminalId ID терминал (источник запроса)\
	 * @param cardNumber номер карты
	 * @return открытая транзакция
	 * @throws TransactionFailedException если не удалось открыть транзакцию
	 */
	Transaction openTransaction(TerminalId terminalId, CardNumber cardNumber);

	/**
	 * @param transactionId ID транзакции (контекст запроса)
	 * @return текущий доступ баланс
	 * @throws NetworkException если запрос не прошел на получение денег
	 */
	Money getBalance(TransactionId transactionId);

	/**
	 * @param transactionId ID транзакции (контекст запроса)
	 * @param amount сумма для заморозки на оплату
	 * @throws NetworkException если запрос не прошел
	 * @throws IllegalArgumentException если на балансе не хватает денег
	 */
	void freeze(TransactionId transactionId, Money amount);

	/**
	 * @param transactionId ID транзакции (контекст запроса)
	 * @throws NetworkException если запрос не прошел (но изменения применены)
	 */
	void commit(TransactionId transactionId);
}
