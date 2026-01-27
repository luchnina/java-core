package ru.makhonya.javalearn.payment.transaction;

/**
 * Уникальный идентификатор терминала оплаты.
 * <p>Формат: произвольная непустая строка (например, "T75B", "PJJJJ-JJ5")
 *
 * @param terminalId строка идентификатора
 */
public record TerminalId(String terminalId) {

	/**
	 *
	 * @param terminalId строка идентификатора
	 * @throws IllegalArgumentException значение не может быть нулевым или отсутствовать
	 */
	public TerminalId {
		if (terminalId == null || terminalId.trim().isEmpty()) {
			throw new IllegalArgumentException("terminalId — null или пустой");
		}
	}
}
