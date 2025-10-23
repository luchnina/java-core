package ru.makhonya.javalearn.factorial;

import java.math.BigInteger;

public class FactorialApplication {

    public static BigInteger calculationFactorial(int n) {
        return (n == 1 || n == 0) ?
                BigInteger.ONE :
                BigInteger.valueOf(n).multiply(calculationFactorial(n - 1));
    }
}
