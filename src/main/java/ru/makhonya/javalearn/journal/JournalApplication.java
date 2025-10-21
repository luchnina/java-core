package ru.makhonya.javalearn.journal;

import java.util.Arrays;

public class JournalApplication {

    public static int[] addRating(int[] array, int rating) {
        if (array == null) {
            return new int[]{rating};
        }

        int[] newArray = new int[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[newArray.length - 1] = rating;

        return newArray;
    }

    public static float averageValue(int[] array) {
        int sum = 0;

        for (int j : array) {
            sum += j;
        }

        float average = (float) sum / array.length;
        return Math.round(average * 100f) / 100f;
    }

    public static int[][] amountRatings(int[] array) {
        int[][] newArray = {
                {5, 0},
                {4, 0},
                {3, 0},
                {2, 0},
        };

        for (int j : array) {

            switch (j) {
                case 5 -> newArray[0][1] += 1;
                case 4 -> newArray[1][1] += 1;
                case 3 -> newArray[2][1] += 1;
                case 2 -> newArray[3][1] += 1;
            }
        }

        return newArray;
    }
}
