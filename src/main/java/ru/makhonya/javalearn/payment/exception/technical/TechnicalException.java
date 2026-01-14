package ru.makhonya.javalearn.payment.exception.technical;

import ru.makhonya.javalearn.payment.exception.PaymentException;

/**
 * Технические ошибки - проблемы инфраструктуры.
 */
public abstract class TechnicalException extends PaymentException {

	protected TechnicalException(String message) {
		super(message);
	}

	protected TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}
}