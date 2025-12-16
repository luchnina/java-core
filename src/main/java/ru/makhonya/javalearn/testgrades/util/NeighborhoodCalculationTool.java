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
		Student[] studentOptions = {
				new Student(5),
				new Student(4),
				new CStudent(3),
				new FStudent(2)
		};

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
					neighbourhoods = RowsHelper.getNeighbourhood(classroom, j, numberItemRow);
					calculatedClassroom[j] = studentOptions[2].scoreCalculation(neighbourhoods);
					j++;
				}
				case 2 -> {
					neighbourhoods = RowsHelper.getNeighbourhood(classroom, j, numberItemRow);
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
}

