package ru.makhonya.javalearn.payment.paymentstatus;

public record PaymentSuccess(String message) implements PaymentResult {

  @Override
  public boolean isSuccess() {
    return true;
  }

  public static PaymentSuccess successfulPayment() {
    return new PaymentSuccess("Успешная оплата");
  }
}
