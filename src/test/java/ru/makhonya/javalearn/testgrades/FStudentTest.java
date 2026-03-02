package ru.makhonya.javalearn.testgrades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FStudentTest {

    // Расчет оценки двоечника идет сложнее из-за больше числа соседей, которых нужно рассмотреть.
    // Мы получаем соседей впереди и сзади, а также соседа по парте.
    // Нам нужно найти соседей с наивысшими оценками и найти среднее значение оценки округленную в меньшую сторону.
    @Test
    public void scoreCalculation_whenArrayNeighbourhoods_thenRating() {
        Student student = new FStudent(2);
        int[][] neighbourhoods = new int[][]{
                {2, 5, 0},
                {2, 0, 0},
                {2, 2, 4},
                {2, 3, 0},
                {2, 4, 4},
                {2, 5, 0},
                {2, 0, 4},
                {2, 2, 4},
        };
        int[] ratings = new int[]{3, 2, 3, 2, 4, 3, 3, 3};

        for (int i = 0; i < neighbourhoods.length;) {
            for (int rating : ratings) {
                assertEquals(rating, student.scoreCalculation(neighbourhoods[i]), i);
                i++;
            }
        }

    }
}