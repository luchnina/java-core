package ru.makhonya.javalearn.payment.paymentstatus;

public record PaymentSuccess() implements PaymentResult {

	@Override
	public boolean isSuccess() {
		return true;
	}

	@Override
	public String toString() {
		return "Успешная операция";
	}
}
