package ru.makhonya.javalearn.payment.exception.technical;

import ru.makhonya.javalearn.payment.exception.business.BusinessException;

public final class TransactionNotFoundException extends BusinessException {

    /**
     * Транзакция не найдена или уже закрыта.
     *
     * @param transactionId ID транзакции
     */
    public TransactionNotFoundException(String transactionId) {
        super(String.format("Транзакция %s не найдена или уже закрыта", transactionId));
    }
}

