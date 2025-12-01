package ru.makhonya.javalearn.geography;

public class MiraclesNature extends Miracles {

    public MiraclesNature(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Чудо природы " + getName();
    }
}
