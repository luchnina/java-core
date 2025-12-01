package ru.makhonya.javalearn.testgrades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    public void scoreCalculation_whenArrayNeighbourhoods_thenRating() {
        Student[] student = {
                new Student(5),
                new Student(4)
        };
        int[] arrays = new int[3];
        int[] ratings = new int[]{5, 4};

        for (int i = 0; i < student.length;) {
            for (int rating : ratings) {
                assertEquals(rating, student[i].scoreCalculation(arrays));
                i++;
            }
        }

    }

}