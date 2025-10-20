package ru.makhonya.javalearn.factorial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

class FactorialApplicationTest {

    @Test
    public void calculationFactorial_whenInteger_thenBigInteger() {
        int[] numbers = {0, 1, 6, 10, 13};
        BigInteger[] result = {BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(720),
                BigInteger.valueOf(3_628_800), BigInteger.valueOf(6_227_020_800L)};

        for (int i = 0; i < numbers.length; i++) {
            String message = numbers[i] + "!" + " = " + result[i];
            assertEquals(result[i], FactorialApplication.calculationFactorial(numbers[i]), message);
        }
    }

}