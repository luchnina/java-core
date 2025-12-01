package ru.makhonya.javalearn.testgrades;

public class Student {

	//Хорошисты и отличники
	private final int name;

	public Student(int name) {
		this.name = name;
	}

	public int getName() {
		return name;
	}

	/// @param student соседи текущего ученика не учитывая его самого
	public int scoreCalculation(int[] student) {
		return getName();
	}
}