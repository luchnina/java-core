package ru.makhonya.javalearn.payment.transaction;

import org.jspecify.annotations.NonNull;

import java.util.Objects;

/**
 * Деньги всегда положительные, в копейках (int для точности целой части).
 * <p>100 рублей = 10000 копеек, чтобы избежать проблемы округления, сравнение.
 * <p><b>Пример</b>
 * <p>
 * <pre>{@code
 *     Money.ofRubles(100);  // 100.00
 *     Money.ofRubles(10_50) // 10.50
 *     new Money(-100)       // IllegalArgumentException
 * }</pre>
 *
 * @param amountInKopecks сумма в копейках (>= 0)
 */
public record Money(int amountInKopecks) {

    // просто удобная константа «ноль денег»
    public static final Money ZERO = new Money(0);

    /**
     * Конструктор с валидацией
     *
     * @param amountInKopecks сумма в копейках (>= 0)
     * @throws IllegalArgumentException если amountInKopecks < 0
     */
    public Money {
        if (amountInKopecks < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными" + amountInKopecks);
        }
    }

    /**
     * Создаем Money из рублей (перевод в копейки).
     *
     * @param rubles сумма в рублях
     * @return Money в копейках
     * @throws IllegalArgumentException если rubles < 0
     */
    public static Money ofRubles(int rubles) {
        return new Money(rubles * 100);
    }

    /**
     * Сравнение эту сумму с другой (осталось ли больше, чем ноль при списании)
     *
     * @param other другая сумма
     * @return true если текущее значение больше другого
     */
    public boolean isGreaterThan(Money other) {
        Objects.requireNonNull(other, "другое значение не может быть нулевым");
        return amountInKopecks > other.amountInKopecks;
    }

    /**
     * Сравнивает эту сумму с другой (включительно).
     * Используется для проверки "можно ли списать сумму".
     *
     * @param other другая сумма для сравнения
     * @return true, если эта сумма больше или равна другой
     * @throws NullPointerException если other == null
     */
    public boolean isGreaterThanOrEqualTo(Money other) {
        Objects.requireNonNull(other, "другое значение не может быть null");
        return amountInKopecks >= other.amountInKopecks;
    }

    /**
     * Проверяет, хватит ли этой суммы на покрытие указанной.
     * Удобная обёртка над isGreaterThanOrEqualTo().
     *
     * @param amount сумма, которую нужно покрыть (списать)
     * @return true, если хватает денег на списание
     * @throws NullPointerException если amount == null
     */
    public boolean canCover(Money amount) {
        return isGreaterThanOrEqualTo(amount);
    }

    /**
     * Вычитание разницы.
     * Если результат отрицательный, возвращается ZERO (защита от ошибок).
     *
     * @param other сумма для вычитания
     * @return сумма после вычета
     * @throws IllegalArgumentException если other == null
     */
    public Money minus(Money other) {
        Objects.requireNonNull(other, "нельзя вычитать null");
        int result = amountInKopecks - other.amountInKopecks;
        if (result < 0) {
            return ZERO;
        }
        return new Money(result);
    }

    /**
     * Форматирование к виду 123.32 ₽.
     */
    @Override
    @NonNull
    public String toString() {
        return String.format("%d.%02d ₽", amountInKopecks / 100, amountInKopecks % 100);
    }
}
