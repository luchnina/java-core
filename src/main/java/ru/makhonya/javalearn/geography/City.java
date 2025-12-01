package ru.makhonya.javalearn.geography;

public class City extends HumanSettlement {

	public City(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Город " + getName();
	}
}
