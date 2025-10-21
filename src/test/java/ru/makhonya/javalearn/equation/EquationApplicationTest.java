package ru.makhonya.javalearn.equation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationApplicationTest {

    @Test
    public void year_whenBetSavingsSalarySavingsAccount_thenDouble() {
        int bet = 10;
        int savings = 500_000;
        int salary = 50_000;
        int salarySavings = 10_000_000;
        double result = 9.44;

        assertEquals(result, EquationApplication.year(bet, savings, salary, salarySavings));
    }

}