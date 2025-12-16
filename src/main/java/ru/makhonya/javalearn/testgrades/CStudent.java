package ru.makhonya.javalearn.testgrades;

public class CStudent extends Student {

	//Троечники
	public CStudent(int name) {
		super(name);
	}

	@Override
	public int scoreCalculation(int[] student) {
		int deskNeighbor = student[1];

		switch (deskNeighbor) {
			case 3, 2 -> {
				return 3;
			}
			case 4, 5 -> {
				return deskNeighbor;
			}
			default -> {
				return 4;
			}
		}
	}
}
