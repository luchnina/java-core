package ru.makhonya.javalearn.payment.transaction;

public enum TransactionStatus {
	/**
	 * Открыта, ожидает freeze/commit
	 */
	OPEN,
	/**
	 * Закрыта (успешно или с нулевой суммой)
	 */
	COMMITED
}
