package ru.makhonya.javalearn.geography;

public class City extends Region {

    public City(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Город " + getName();
    }
}
