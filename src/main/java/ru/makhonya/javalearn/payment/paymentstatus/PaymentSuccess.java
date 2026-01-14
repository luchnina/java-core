package ru.makhonya.javalearn.payment.paymentstatus;

import org.jspecify.annotations.NonNull;

public record PaymentSuccess() implements PaymentResult {

	@Override
	public boolean isSuccess() {
		return true;
	}

	@Override
	@NonNull
	public String toString() {
		return "Успешная операция";
	}
}
