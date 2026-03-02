package ru.makhonya.javalearn.payment.paymentstatus;

/**
 * Неудачный процесс оплаты.
 *
 * @param message сообщение для клиента в зависимости от типа ошибки.
 */
public record PaymentError(String message, Exception exception) implements PaymentResult {

    @Override
    public boolean isSuccess() {
        return false;
    }
}
