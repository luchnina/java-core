package ru.makhonya.javalearn.payment.exception.technical;

import ru.makhonya.javalearn.payment.transaction.CardNumber;
import ru.makhonya.javalearn.payment.transaction.TerminalId;

public final class TransactionFailedException extends NetworkException {

	/**
	 * Не удалось открыть транзакцию.
	 *
	 * @param terminalId ID терминала
	 * @param cardNumber номер карты
	 */
	public TransactionFailedException(TerminalId terminalId, CardNumber cardNumber) {
		super(String.format("Не удалось открыть транзакцию для терминала %s, карта %s",
				terminalId.terminalId(),
				cardNumber.maskedNumber()
		));
	}
}
