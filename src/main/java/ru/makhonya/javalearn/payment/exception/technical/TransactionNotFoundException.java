package ru.makhonya.javalearn.payment.exception.technical;

import ru.makhonya.javalearn.payment.exception.business.BusinessException;
import ru.makhonya.javalearn.payment.transaction.TransactionId;

public final class TransactionNotFoundException extends BusinessException {

	/**
	 * Транзакция не найдена или уже закрыта.
	 *
	 * @param transactionId ID транзакции
	 */
	public TransactionNotFoundException(TransactionId transactionId) {
		super(String.format("Транзакция %s не найдена или уже закрыта", transactionId.terminalId()));
	}
}

