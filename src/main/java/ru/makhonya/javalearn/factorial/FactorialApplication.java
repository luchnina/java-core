package ru.makhonya.javalearn.factorial;

import java.math.BigInteger;

public class FactorialApplication {

	/**
	 * Вычисляет факториал n рекурсивно.
	 * @param n неотрицательное целое число
	 * @return n! как BigInteger
	 * @throws IllegalArgumentException если n < 0
	 */
	public static BigInteger calculationFactorial(int n) {

		if (n < 0) {
			throw new IllegalArgumentException("n должен быть неотрицательным, получился: " + n);
		}

		return (n == 1 || n == 0) ?
				BigInteger.ONE :
				BigInteger.valueOf(n).multiply(calculationFactorial(n - 1));
	}
}
