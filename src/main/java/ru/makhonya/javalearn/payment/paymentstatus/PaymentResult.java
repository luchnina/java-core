package ru.makhonya.javalearn.payment.paymentstatus;

/**
 * Результаты оплаты для клиента.
 */
public sealed interface PaymentResult permits PaymentSuccess, PaymentError {

    boolean isSuccess();
}
