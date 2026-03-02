package ru.makhonya.javalearn.payment;

import ru.makhonya.javalearn.payment.bank.NetworkBank;
import ru.makhonya.javalearn.payment.bank.PankBank;
import ru.makhonya.javalearn.payment.paymentstatus.PaymentResult;
import ru.makhonya.javalearn.payment.transaction.Card;
import ru.makhonya.javalearn.payment.transaction.Money;
import ru.makhonya.javalearn.payment.util.LoggerUtil;

public class PaymentApplication {

    static void main() {

        Card card = new Card("**** 1234");

        PankBank realBank = new PankBank(
                card,
                Money.ofRubles(150)
        );

        Terminal terminal = new Terminal(
                "T123",
                new NetworkBank(realBank)
        );

        // Тест 1: Успешная оплата 100₽
        PaymentResult result1 = terminal.payment(card, Money.ofRubles(100));
        LoggerUtil.info("Тест 1: {}", result1);

        // Тест 2: Недостаточно средств
        PaymentResult result2 = terminal.payment(card, Money.ofRubles(200));
        LoggerUtil.info("Тест 2: {}", result2);
    }
}
