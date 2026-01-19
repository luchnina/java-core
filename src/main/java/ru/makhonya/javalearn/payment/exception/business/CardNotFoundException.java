package ru.makhonya.javalearn.payment.exception.business;

import ru.makhonya.javalearn.payment.transaction.CardNumber;

public final class CardNotFoundException extends BusinessException {

	/**
	 * Карта не найдена в банке.
	 *
	 * @param cardNumber номер карты
	 */
	public CardNotFoundException(CardNumber cardNumber) {
		super(String.format("Карта с номером %s не найдена", cardNumber.maskedNumber()));
	}
}

