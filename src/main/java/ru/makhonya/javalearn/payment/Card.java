package ru.makhonya.javalearn.payment;

import ru.makhonya.javalearn.payment.exception.bank.Bank;
import ru.makhonya.javalearn.payment.transaction.CardNumber;
import ru.makhonya.javalearn.payment.transaction.TransactionId;

/**
 * Карта пассажира - только индикатор.
 * <p>
 * Нет баланса внутри карты, потому что он хранится в банке и запрашивается {@link Bank#getBalance(TransactionId)}.
 * То есть карта это физический носитель данных счета, но деньги физически в банке на счете.
 *
 * @param cardNumber замаскированный номер карты
 */
public record Card(CardNumber cardNumber) {

}
