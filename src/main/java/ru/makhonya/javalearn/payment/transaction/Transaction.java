package ru.makhonya.javalearn.payment.transaction;

import org.jspecify.annotations.NonNull;

/**
 * "Ячейка" в банке для оплаты и содержит всю информацию для платежа.
 *
 * @param terminalId ID терминалы, на котором производится оплата
 * @param id ID платежа (генерируется банком)
 * @param card номер карты
 * @param amount сумма платежа
 * @param status текущий статус платежа
 */
public record Transaction(
        @NonNull String terminalId,
        @NonNull String id,
        Card card,
        @NonNull Money amount,
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
