package ru.makhonya.javalearn.payment;

import org.jspecify.annotations.NonNull;
import ru.makhonya.javalearn.payment.bank.Bank;
import ru.makhonya.javalearn.payment.exception.PaymentException;
import ru.makhonya.javalearn.payment.exception.business.InsufficientFundsException;
import ru.makhonya.javalearn.payment.exception.technical.NetworkException;
import ru.makhonya.javalearn.payment.paymentstatus.PaymentError;
import ru.makhonya.javalearn.payment.paymentstatus.PaymentResult;
import ru.makhonya.javalearn.payment.paymentstatus.PaymentSuccess;
import ru.makhonya.javalearn.payment.transaction.Card;
import ru.makhonya.javalearn.payment.transaction.Money;
import ru.makhonya.javalearn.payment.transaction.Transaction;
import ru.makhonya.javalearn.payment.util.LoggerUtil;

public class Terminal {

    private final String id;
    private final Bank bank;

    /**
     * Создаёт терминал с ID и банком.
     *
     * @param id уникальный ID терминала (например, "T123")
     * @param bank банк (обычно NetworkBank с 85% успехом)
     */
    public Terminal(@NonNull String id, @NonNull Bank bank) {
        this.id = id;
        this.bank = bank;
    }

    /**
     * Оплата покупки.
     *
     * @param card номер карты клиента
     * @param amount сумма списания
     * @throws PaymentException бизнес‑ошибки оплаты
     * @throws NetworkException проблемы сети (прокси NetworkBank исчерпал попытки)
     */
    public PaymentResult payment(Card card, Money amount) {

        String transactionId = null;

        try {
            Transaction transaction = bank.openTransaction(id, card);
            transactionId = transaction.id();

            bank.freeze(transactionId, amount);

            return PaymentSuccess.successfulPayment();

        } catch (InsufficientFundsException e) {
            LoggerUtil.warn("Бизнес-ошибка: {}", e.getMessage());
            return new PaymentError("Недостаточно средств", e);

        } catch (NetworkException e) {
            LoggerUtil.warn("Сетевая ошибка: {}", e.getMessage());
            return new PaymentError("Ошибка связи", e);

        } catch (Exception e) {
            LoggerUtil.warn("Неожиданная ошибка: {}", e.getMessage(), e);
            return new PaymentError("Ошибка связи", e);

        } finally {
            bank.commit(transactionId);
        }
    }
}
