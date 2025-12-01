package ru.makhonya.javalearn.geography;

import java.util.Objects;

public abstract class Miracles implements Location {

	private String name;

	public Miracles(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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

		Miracles miracles = (Miracles) o;
		return name.equals(miracles.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
