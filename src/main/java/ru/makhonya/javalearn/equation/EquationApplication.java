package ru.makhonya.javalearn.equation;

public class EquationApplication {

    /**
     * @param bet ставка накопительного счета
     * @param savings первоначальные накопления
     * @param salary вносимая сумма на накопительный счет каждый месяц
     * @param savingsAccount итоговые накопления на конец периода в year лет
     * @return double год в который удастся накопить savingsAccount
     */
    public static double year(int bet, int savings, int salary, int savingsAccount) {
        double a = 1;
        double b = 20;
        double d = 0;

        do {
            d = (a + b) / 2;
            double leftValue = yearFormula(a, bet, savings, salary, savingsAccount);
            double rightValue = yearFormula(d, bet, savings, salary, savingsAccount);

            if (leftValue * rightValue < 0) {
                b = d;
            }

            if (leftValue * rightValue > 0) {
                a = d;
            }

        } while ((b - a) / 2 >= 0.01);

        return Math.round(d * 100.0) / 100.0;
    }

    private static double yearFormula(double a, int bet, int savings, int salary, int savingsAccount) {
        double exponentiation = Math.pow((1 + (double) bet / 100), a);
        return savingsAccount - salary * 12 * ((1 - exponentiation) / ((double) -bet / 100)) - savings * exponentiation;
    }
}
