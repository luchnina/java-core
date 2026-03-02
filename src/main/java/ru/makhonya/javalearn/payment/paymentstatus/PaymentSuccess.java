package ru.makhonya.javalearn.payment.paymentstatus;

public record PaymentSuccess(String message) implements PaymentResult {

    public static PaymentSuccess successfulPayment() {
        return new PaymentSuccess("Успешная оплата");
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}
