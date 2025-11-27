package ru.makhonya.javalearn.testgrades;

public class FStudent extends Student {

	//Двоечники
	public FStudent(int name) {
		super(name);
	}

	@Override
	public int scoreCalculation(int[] student) {
		int front = student[0];
		int desk = student[1];
		int back = student[2];

		int maximumValue = Math.max(front + desk, Math.max(back + desk, front + back));
		int half = maximumValue / 2;
		return (half <= 1) ? 2 : half;
	}
}
