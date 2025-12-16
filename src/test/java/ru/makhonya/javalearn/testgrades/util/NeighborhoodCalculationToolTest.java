package ru.makhonya.javalearn.testgrades.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
}