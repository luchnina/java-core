package ru.makhonya.javalearn.geography;

import java.util.Objects;

public class HumanSettlement implements Location {

	private String name;

	public HumanSettlement(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HumanSettlement country = (HumanSettlement) o;
		return this.getName().equals(country.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
