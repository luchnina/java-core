package ru.makhonya.javalearn.testgrades.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RowsHelperTest {

	@Test
	public void getNeighbourhood_whenArrayClassroomPositionNumberItemRow_thenArrayNeighbourhoods() {

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

		for (int i = 0; i < position.length; ) {
			for (int[] neighbourhood : neighbourhoods) {
				assertArrayEquals(
						neighbourhood,
						RowsHelper.getNeighbourhood(classroom, position[i], numberItemRow)
				);
				i++;
			}
		}
	}

	@Test
	public void getRows_whenArrayClassroomRowSize_thenArrayRows() {

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

		int[][] result = RowsHelper.getRows(classroom, numberItemRow);

		for (int i = 0; i < numberRow; i++) {
			assertArrayEquals(rows[i], result[i]);
		}
	}
}