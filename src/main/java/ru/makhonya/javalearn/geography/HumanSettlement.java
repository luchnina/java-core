package ru.makhonya.javalearn.geography;

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

		Country country = (Country) o;
		return this.getName().equals(country.getName());
	}

	@Override
	public int hashCode() {
		return this.getName() != null ? 31 * this.getName().hashCode() : 0;
	}
}
