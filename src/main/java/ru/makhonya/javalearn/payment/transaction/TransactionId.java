package ru.makhonya.javalearn.payment.transaction;

/**
 * Уникальный идентификатор транзакции (генерируется банком)
 */
public record TransactionId(String terminalId) {

	/**
	 *
	 * @param terminalId уникальное значение транзакции
	 * @throws IllegalArgumentException значение не может быть нулевым или отсутствовать
	 */
	public TransactionId {
		if (terminalId == null || terminalId.trim().isEmpty()) {
			throw new IllegalArgumentException("terminalId не может быть пустым");
		}
	}
}
