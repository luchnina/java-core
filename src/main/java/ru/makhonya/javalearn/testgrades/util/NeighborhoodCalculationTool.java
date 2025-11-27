package ru.makhonya.javalearn.testgrades.util;

import ru.makhonya.javalearn.testgrades.CStudent;
import ru.makhonya.javalearn.testgrades.FStudent;
import ru.makhonya.javalearn.testgrades.Student;

public class NeighborhoodCalculationTool {

	/// Расчет оценок в зависимости от позиции ученика
	///
	/// @param classroom весь список класса
	public static int[] calculationGrades(int[] classroom) {

		int numberItemRow = 10;
		int[] calculatedClassroom = new int[classroom.length];
		int[] neighbourhoods = new int[3];
		// Создаем массив возможных студентов, чтобы не создавались каждый раз новые объекты в цикле
		Student[] studentOptions = {new Student(5), new Student(4), new CStudent(3), new FStudent(2),};

		for (int j = 0; j < classroom.length; ) {
			switch (classroom[j]) {
				case 5 -> {
					calculatedClassroom[j] = studentOptions[0].scoreCalculation(neighbourhoods);
					j++;
				}
				case 4 -> {
					calculatedClassroom[j] = studentOptions[1].scoreCalculation(neighbourhoods);
					j++;
				}
				case 3 -> {
					neighbourhoods = getNeighbourhood(classroom, j, numberItemRow);
					calculatedClassroom[j] = studentOptions[2].scoreCalculation(neighbourhoods);
					j++;
				}
				case 2 -> {
					neighbourhoods = getNeighbourhood(classroom, j, numberItemRow);
					calculatedClassroom[j] = studentOptions[3].scoreCalculation(neighbourhoods);
					j++;
				}
				default -> {
					calculatedClassroom[j] = 0;
					j++;
				}
			}

		}

		return calculatedClassroom;
	}

	/// Расчет соседей для заданного элемента
	///
	/// @param classroom весь список класса
	/// @param position позиция рассматриваемого ученика в классе для составления массива его соседей
	/// @param numberItemRow количество элементов в ряду
	private static int[] getNeighbourhood(int[] classroom, int position, int numberItemRow) {
		int rowSearch = position / numberItemRow; // Если позиция 23, то первое значение ряд
		int positionItemRow = position % numberItemRow; // Если позиция 23, второе значение позиция в ряду.
		int[][] rows = getRows(classroom, numberItemRow);
		int[] neighbourhood = new int[3];

		neighbourhood[0] = (positionItemRow - 2 >= 0) ? rows[rowSearch][positionItemRow - 2] : 0;  // Сосед спереди

		//У нас есть сосед справа или слева в зависимости какой элемент берём.
		int idx = ((float) positionItemRow % 2 == 0) ? positionItemRow + 1 : positionItemRow - 1;
		neighbourhood[1] = (idx < rows[rowSearch].length) ? rows[rowSearch][idx] : 0; // Сосед по парте

		// Сосед сзади
		neighbourhood[2] = (positionItemRow + 2 < rows[rowSearch].length) ? rows[rowSearch][positionItemRow + 2] : 0;

		return neighbourhood;
	}

	/// Расчет рядов заданного класса и в зависимости от длины ряда
	///
	/// @param classroom весь список класса
	/// @param rowSize количество элементов в ряду
	private static int[][] getRows(int[] classroom, int rowSize) {
		// Рассчитывает количество рядов в данном конкретном классе.
		// Деление с плавающей точкой, затем округление вверх, что учесть неполный ряд, если он есть.
		int totalRows = (int) Math.ceil(classroom.length / (double) rowSize);

		// У нас есть значение того, сколько элементов должно быть в массиве, но мы заранее не указываем, чтобы
		// учесть неполный ряд.
		int[][] rows = new int[totalRows][];

		for (int i = 0; i < totalRows; i++) {
			// Значение количества элементов может меняться, поэтому мы и определяем границы внутри цикла.
			int start = i * rowSize; // i = 0, start = 0; i = 1, start = 10; i = 2, start = 20;
			int end = (i + 1) * rowSize; // i = 1, end = 10; i = 2, end = 20; i = 3, end = 30;
			int length = end - start;

			rows[i] = new int[length];
			// копируем из изначального массива в новые значения относительно начальной позиции
			System.arraycopy(classroom, start, rows[i], 0, length); // Например, с 10 до 19.
		}

		return rows;
	}
}

