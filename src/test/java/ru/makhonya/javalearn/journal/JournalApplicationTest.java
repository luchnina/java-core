package ru.makhonya.javalearn.journal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JournalApplicationTest {

    @Test
    public void addRating_whenNull_thenArray() {
        int[] expected = new int[] {4};

        assertArrayEquals(expected, JournalApplication.addRating(null, 4));
    }

    @Test
    public void addRating_whenArrayRating_thenArray() {
        int[] array = new int[] {5, 3, 4, 5, 2, 5, 3};
        int[] expected = new int[] {5, 3, 4, 5, 2, 5, 3, 4};

        assertArrayEquals(expected, JournalApplication.addRating(array, 4));
    }

    @Test
    public void averageValue_whenArrayRating_thenFloat() {
        int[] array = new int[]{5, 3, 4, 5, 2, 5, 3};
        float expected = 3.86f;

        assertEquals(expected, JournalApplication.averageValue(array));
    }

    @Test
    public void amountRatings_whenArrayRating_thenArrayArrays() {
        int[] array = new int[]{5, 3, 4, 5, 2, 5, 3};
        int[][] expected = {
                {5, 3},
                {4, 1},
                {3, 2},
                {2, 1},
        };

        assertArrayEquals(expected, JournalApplication.amountRatings(array));
    }
}