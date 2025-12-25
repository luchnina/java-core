package ru.makhonya.javalearn.payment;

import ru.makhonya.javalearn.payment.transaction.*;

/**
 * "Ячейка" в банке для оплаты и содержит всю информацию для платежа.
 *
 * @param terminalId ID терминалы, на котором производится оплата
 * @param id ID платежа (генерируется банком)
 * @param cardNumber номер карты
 * @param amount сумма платежа
 * @param status текущий статус платежа
 */
public record Transaction(
		TerminalId terminalId,
		TransactionId id,
		CardNumber cardNumber,
		Money amount,
		TransactionStatus status
) {

	/**
	 * Проверяем, открыта ли транзакция
	 *
	 * @return true если транзакция открыта
	 */
	public boolean isOpen() {
		return status == TransactionStatus.OPEN;
	}
}
