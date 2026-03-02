package ru.makhonya.javalearn.payment.exception.business;

/**
 * Бизнес-ошибка - критичны для клиента.
 *
 * <p>Например, недостаточно средств, неверные реквизиты.
 */
public abstract class BusinessException extends RuntimeException {

	protected BusinessException(String message) {
		super(message);
	}

	protected BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
