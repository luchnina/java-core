package ru.makhonya.javalearn.geography;

public class Country extends HumanSettlement {

	public Country(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Страна " + getName();
	}
}
