package ru.makhonya.javalearn.budget;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class BudgetApplication {

    private static final Map<String, List<BigDecimal>> BUDGETS = new HashMap<>();
    private static final BigDecimal MROT = new BigDecimal("20000");

    /// На всех сотрудников бюджет
    public static BigDecimal getAllBudget() {
        return BUDGETS
                .values()
                .stream()
                .flatMap(List::stream)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Boolean isMROT() {
        return BUDGETS
                .values()
                .stream()
                .flatMap(List::stream)
                .anyMatch(salary -> salary.compareTo(MROT) < 0);
    }

    public static Map<String, BigDecimal> getAverageSalary() {
        return BUDGETS.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(
                        entry.getKey(),
                        entry.getValue()
                                .stream()
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .divide(BigDecimal.valueOf(entry.getValue().size()), 2, RoundingMode.HALF_UP)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static String getMaxSubdivision() {
        return BUDGETS.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(
                        entry.getKey(),
                        entry.getValue()
                                .stream()
                                .reduce(BigDecimal.ZERO, BigDecimal::add)))
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Не найдено");
    }
}
