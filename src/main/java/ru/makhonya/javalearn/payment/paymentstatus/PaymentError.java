package ru.makhonya.javalearn.payment.paymentstatus;

/**
 * Неудачный процесс оплаты.
 *
 * @param message сообщение для клиента в зависимости от типа ошибки.
 */
public record PaymentError(String message) implements PaymentResult {

	@Override
	public boolean isSuccess() {
		return false;
	}

	public static PaymentError insufficientFunds() {
		return new PaymentError("Недостаточно средств");
	}

	public static PaymentError networkError() {
		return new PaymentError("Ошибка связи");
	}
}
