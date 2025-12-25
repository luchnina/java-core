package ru.makhonya.javalearn.payment.transaction;

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
	 * Сравнение эту сумму с другой (на случай списания)
	 *
	 * @param other другая сумма
	 * @return true если текущее значение больше другого
	 */
	public boolean isGreaterThan(Money other) {
		Objects.requireNonNull(other, "другое значение не может быть нулевым");
		return amountInKopecks > other.amountInKopecks;
	}

	/**
	 * Форматирование к виду 123.32 ₽.
	 */
	@Override
	public String toString() {
		return String.format("%d.%02d ₽", amountInKopecks / 100, amountInKopecks % 100);
	}
}
