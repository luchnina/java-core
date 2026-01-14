package ru.makhonya.javalearn.payment.exception.business;

public final class InsufficientFundsException extends BusinessException {

	public InsufficientFundsException() {
		super("Недостаточно средства на карте");
	}
}
