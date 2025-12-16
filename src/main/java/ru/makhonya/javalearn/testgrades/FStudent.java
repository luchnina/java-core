package ru.makhonya.javalearn.testgrades;

import java.util.Arrays;

public class FStudent extends Student {

	//Двоечники
	public FStudent(int name) {
		super(name);
	}

	@Override
	public int scoreCalculation(int[] student) {
		Arrays.sort(student);

		// Сортируется по возрастанию, поэтому самые большие значения в конце.
		int half = (student[1] + student[2]) / 2;
		return (half <= 1) ? 2 : half;
	}
}
