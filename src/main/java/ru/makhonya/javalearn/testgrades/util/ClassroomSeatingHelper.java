package ru.makhonya.javalearn.testgrades.util;

public record ClassroomSeatingHelper() {

	static int[] validValues = {0, 2, 3, 4, 5};

	/// Заполнение массива рандомными элементами из доступных значений, которые прописаны заранее в классе
	public static int[] automatedSeatingAssignment() {
		int[] classroom = new int[10];

		for (int i = 0; i < 10; i++) {
			classroom[i] = validValues[(int) (Math.random() * (validValues.length - 1))];
		}

		return classroom;
	}
}
