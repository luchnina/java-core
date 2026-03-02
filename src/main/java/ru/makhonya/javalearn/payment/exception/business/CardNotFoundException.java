package ru.makhonya.javalearn.payment.exception.business;

import ru.makhonya.javalearn.payment.transaction.Card;

public final class CardNotFoundException extends BusinessException {

    /**
     * Карта не найдена в банке.
     *
     * @param card номер карты
     */
    public CardNotFoundException(Card card) {
        super(String.format("Карта с номером %s не найдена", card.maskedNumber()));
    }
}

