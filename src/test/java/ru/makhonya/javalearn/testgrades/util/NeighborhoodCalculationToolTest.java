package ru.makhonya.javalearn.testgrades.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class NeighborhoodCalculationToolTest {

    @Test
    public void calculationGrades_whenArrayClassroom_thenArrayRating() {
        int[] classroom = new int[]{
                0, 0, 4, 3, 0, 4, 2, 4, 4, 3, 4, 3, 2, 2, 0, 3, 2, 2, 4, 3, 2, 4, 4, 3, 4, 3, 0, 3, 0, 2
        };
        int[] result = new int[]{
                0, 0, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 3, 3, 0, 4, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 0, 2
        };

        assertArrayEquals(result, NeighborhoodCalculationTool.calculationGrades(classroom));
    }

    @Test
    public void getNeighbourhood_whenArrayClassroomPositionNumberItemRow_thenArrayNeighbourhoods() throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Класс сам за себя все знает и может рефлексировать, поэтому мы можем вызвать приватный метод.
        // Как раз из-за рефлексии мы получаем тип Method.
        Method m = NeighborhoodCalculationTool.class
                .getDeclaredMethod("getNeighbourhood", int[].class, int.class, int.class);
        // Мы просим JVM снять ограничение на модификатор и вызвать метод через рефлексию.
        m.setAccessible(true);

        int[] classroom = new int[]{
                0, 0, 4, 3, 0, 4, 2, 4, 4, 3, 4, 3, 2, 2, 0, 3, 2, 2, 4, 3, 2, 4, 4, 3, 4, 3, 0, 3, 0, 2
        };
        int[][] neighbourhoods = new int[][]{
                {0, 0, 4},
                {0, 0, 3},
                {3, 0, 4},
                {2, 3, 0},
                {0, 3, 2},
                {2, 4, 0},
                {0, 2, 3},
        };
        int numberItemRow = 10;
        // Берем крайние позиции в разных рядах, чтобы проверить учет деления на ряды.
        int[] position = new int[]{0, 1, 5, 8, 10, 19, 21};

        for (int i = 0; i < position.length;) {
            for (int[] neighbourhood : neighbourhoods) {
                assertArrayEquals(
                        neighbourhood,
                        (int[]) m.invoke(null, classroom, position[i], numberItemRow)
                );
                i++;
            }
        }
    }

    @Test
    public void getRows_whenArrayClassroomRowSize_thenArrayRows() throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Класс сам за себя все знает и может рефлексировать, поэтому мы можем вызвать приватный метод.
        // Как раз из-за рефлексии мы получаем тип Method.
        Method m = NeighborhoodCalculationTool.class
                .getDeclaredMethod("getRows", int[].class, int.class);
        // Мы просим JVM снять ограничение на модификатор и вызвать метод через рефлексию.
        m.setAccessible(true);

        int[] classroom = new int[]{
                0, 0, 4, 3, 0, 4, 2, 4, 4, 3, 4, 3, 2, 2, 0, 3, 2, 2, 4, 3, 2, 4, 4, 3, 4, 3, 0, 3, 0, 2
        };
        int[][] rows = new int[][]{
                {0, 0, 4, 3, 0, 4, 2, 4, 4, 3},
                {4, 3, 2, 2, 0, 3, 2, 2, 4, 3},
                {2, 4, 4, 3, 4, 3, 0, 3, 0, 2},
        };
        int numberItemRow = 10;
        int numberRow = classroom.length / numberItemRow;

        int[][] result = (int[][]) m.invoke(null, classroom, numberItemRow);

        for (int i = 0; i < numberRow; i++) {
            assertArrayEquals(rows[i], result[i]);
        }
    }
}