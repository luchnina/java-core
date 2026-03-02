package ru.makhonya.javalearn.testgrades.util;

import ru.makhonya.javalearn.testgrades.CStudent;
import ru.makhonya.javalearn.testgrades.FStudent;
import ru.makhonya.javalearn.testgrades.Student;

public class NeighborhoodCalculationTool {

    /**
     * Рассчитывает итоговые оценки для всех учеников в классе.
     *
     * <p><strong>Логика:</strong>
     * <ul>
     *   <li>5 → использует логику Student(5), соседи игнорируются</li>
     *   <li>4 → использует логику Student(4), соседи игнорируются</li>
     *   <li>3 → CStudent(3) с учетом соседей</li>
     *   <li>2 → FStudent(2) с учетом соседей</li>
     *   <li>0 → итоговая оценка 0</li>
     * </ul></p>
     *
     * @param classroom массив оценок класса (длина ≥ 1).
     * Допустимые значения: 0,2,3,4,5.
     * @return массив тех же размеров с рассчитанными итоговыми оценками
     * @throws NullPointerException если {@code classroom == null}
     * @throws IllegalArgumentException если {@code classroom.length == 0}
     * @throws IllegalStateException если ошибка при получении соседей
     */
    public static int[] calculationGrades(int[] classroom) {

        if (classroom == null) {
            throw new NullPointerException("Массив класса не может быть null");
        }
        if (classroom.length == 0) {
            throw new IllegalArgumentException("Класс не может быть пустым");
        }

        int numberItemRow = 10;
        int[] calculatedClassroom = new int[classroom.length];
        int[] neighbourhoods = new int[3];
        // Создаем массив возможных студентов, чтобы не создавались каждый раз новые объекты в цикле
        Student[] studentOptions = {
                new Student(5),
                new Student(4),
                new CStudent(3),
                new FStudent(2)
        };

        for (int j = 0; j < classroom.length; ) {
            switch (classroom[j]) {
                case 5 -> {
                    calculatedClassroom[j] = studentOptions[0].scoreCalculation(neighbourhoods);
                    j++;
                }
                case 4 -> {
                    calculatedClassroom[j] = studentOptions[1].scoreCalculation(neighbourhoods);
                    j++;
                }
                case 3 -> {
                    try {
                        neighbourhoods = RowsHelper.getNeighbourhood(classroom, j, numberItemRow);
                    } catch (Exception e) {
                        throw new IllegalStateException(
                                String.format("Ошибка получения соседей для позиции %d: %s", j, e.getMessage()), e
                        );
                    }
                    calculatedClassroom[j] = studentOptions[2].scoreCalculation(neighbourhoods);
                    j++;
                }
                case 2 -> {
                    try {
                        neighbourhoods = RowsHelper.getNeighbourhood(classroom, j, numberItemRow);
                    } catch (Exception e) {
                        throw new IllegalStateException(
                                String.format("Ошибка получения соседей для позиции %d: %s", j, e.getMessage()), e
                        );
                    }
                    calculatedClassroom[j] = studentOptions[3].scoreCalculation(neighbourhoods);
                    j++;
                }
                default -> {
                    calculatedClassroom[j] = 0;
                    j++;
                }
            }

        }

        return calculatedClassroom;
    }
}

