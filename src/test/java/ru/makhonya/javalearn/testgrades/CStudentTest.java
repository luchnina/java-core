package ru.makhonya.javalearn.testgrades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CStudentTest {

    // Троечник обращает внимание только на соседа по парте. Поэтому мы смотрим на среднее значение массив, так как
    // предполагаем, что это и есть его сосед по парте.
    // Если сидит один, то получает оценку 4.
    // Если сидит с хорошистом или отличником, то получит их оценку.
    // Если сидит с троечником или двоечником, то получит оценку 3.
    // То есть вариантов соседей всего 5.
    @Test
    public void scoreCalculation_whenArrayNeighbourhoods_thenRating() {
        Student student = new CStudent(3);
        int[][] neighbourhoods = new int[][]{
                {0, 3, 0},
                {0, 4, 0},
                {0, 5, 0},
                {0, 0, 0},
                {0, 2, 0},
        };
        int[] ratings = new int[]{3, 4, 5, 4, 3};

        for (int i = 0; i < neighbourhoods.length;) {
            for (int rating : ratings) {
                assertEquals(rating, student.scoreCalculation(neighbourhoods[i]));
                i++;
            }
        }

    }
}