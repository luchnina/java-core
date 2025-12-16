package ru.makhonya.javalearn.testgrades.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditoriumMatrixLoggerTest {

	@Test
	void matrix_whenArrayItem_thenMatrix() {
		int[] classroom = {0, 3, 3, 3, 2, 4, 3, 2, 3, 5, 3, 4, 0, 5, 3, 0, 5, 5, 2, 3, 0, 4, 0, 5, 5, 0, 4, 3, 0, 0};

		String expectedMatrix = """
				03 34 04
				33 05 05
				24 30 50
				32 55 43
				35 23 00\
				""";

		assertEquals(expectedMatrix, AuditoriumMatrixLogger.matrix(classroom));
	}
}