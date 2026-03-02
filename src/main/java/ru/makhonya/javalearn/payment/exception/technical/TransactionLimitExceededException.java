package ru.makhonya.javalearn.payment.exception.technical;

public final class TransactionLimitExceededException extends TechnicalException {

	/**
	 * Превышен лимит по количеству транзакций в in-memory банке.
	 */
	public TransactionLimitExceededException() {
		super("Превышен лимит по количеству транзакций");
	}
}
