package ru.makhonya.javalearn.payment.transaction;

/**
 * Номер карты в маскированном формате для безопасности.
 * <p> Поддерживает форматы: "**** 1234", "****1234"
 *
 * @param maskedNumber замаскированный номер карты
 */
public record Card(String maskedNumber) {

    /**
     * Конструктор с валидацией на правильную маску
     *
     * @param maskedNumber замаскированный номер карты
     * @throws IllegalArgumentException если формат неверный
     */
    public Card {
        if (maskedNumber == null || !maskedNumber.matches("\\*\\*\\*\\*\\s?\\d{4}")) {
            throw new IllegalArgumentException(
                    "Неправильный формат карты: " + maskedNumber + ". Правильный: **** 1234 и ****1234"
            );
        }
    }
}
