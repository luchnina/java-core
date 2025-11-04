package ru.makhonya.javalearn.geography;

public class Region extends Country{

    public Region(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Регион " + getName();
    }

}
