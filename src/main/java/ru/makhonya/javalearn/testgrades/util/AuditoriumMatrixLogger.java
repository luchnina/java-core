package ru.makhonya.javalearn.testgrades.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditoriumMatrixLogger {

	private static final Logger log = LoggerFactory.getLogger(AuditoriumMatrixLogger.class);

	/// @param array массив всех студентов в классе
	/// @param textDesignation текст для отображения и обозначения массива
	public static void arrayLogger(int[] array, String textDesignation) {
		log.info("{} \n{}", textDesignation, matrix(array));
	}

	/// @param array массив всех студентов в классе
	public static String matrix(int[] array) {
		StringBuilder newMatrix = new StringBuilder();
		int line = 3;

		int[][] rows = RowsHelper.getRows(array, 10);

		for (int i = 0; i < rows[0].length; i += 2) {
			for (int j = 0; j < line; j++) {
				newMatrix.append(rows[j][i]).append(rows[j][i + 1]);

				if (j != line - 1) {
					newMatrix.append(" ");
				}
			}

			if (i != rows[0].length - 2) {
				newMatrix.append("\n");
			}
		}

		return newMatrix.toString();
	}
}
