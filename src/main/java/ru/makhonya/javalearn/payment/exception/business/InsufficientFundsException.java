package ru.makhonya.javalearn.payment.exception.business;

import ru.makhonya.javalearn.payment.transaction.CardNumber;
import ru.makhonya.javalearn.payment.transaction.Money;

public final class InsufficientFundsException extends BusinessException {

	public InsufficientFundsException() {
		super("Недостаточно средства на карте");
	}

	/**
	 * Недостаточно средств на карте.
	 *
	 * @param available доступный баланс
	 * @param required требуемая сумма
	 */
	public InsufficientFundsException(CardNumber cardNumber, Money available, Money required) {
		super(String.format("Недостаточно средств на карте %s: доступно %s, требуется %s",
				cardNumber,
				available.amountInKopecks(),
				required.amountInKopecks())
		);
	}
}
