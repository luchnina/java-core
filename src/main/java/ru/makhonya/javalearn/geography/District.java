package ru.makhonya.javalearn.geography;

public class District extends City {

    public District(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Округ " + getName();
    }
}
