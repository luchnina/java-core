package ru.makhonya.javalearn.testgrades.util;

import java.util.random.RandomGenerator;

public record ClassroomSeatingHelper() {

	static int[] validValues = {0, 2, 3, 4, 5};
	static final RandomGenerator generator = RandomGenerator.getDefault();

	/// Заполнение массива рандомными элементами из доступных значений, которые прописаны заранее в классе
	public static int[] automatedSeatingAssignment() {
		int numberValueGenerations = 30;
		int[] classroom = new int[numberValueGenerations];

		for (int i = 0; i < numberValueGenerations; i++) {
			int randomIndex = generator.nextInt(validValues.length);
			classroom[i] = validValues[randomIndex];
		}

		return classroom;
	}
}
