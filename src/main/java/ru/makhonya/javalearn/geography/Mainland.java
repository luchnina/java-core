package ru.makhonya.javalearn.geography;

public enum Mainland implements Location {
	AFRICA("Африка"),
	ANTARCTICA("Антарктида"),
	ASIA("Азия"),
	EUROPE("Европа"),
	NORTH_AMERICA("Северная Америка"),
	SOUTH_AMERICA("Южная Америка");

	private final String name;

	Mainland(String s) {
		this.name = s;
	}

	@Override
	public String toString() {
		return "Материк " + this.name;
	}
}