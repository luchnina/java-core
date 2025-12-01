package ru.makhonya.javalearn.geography;

public class MiraclesArchitecture extends Miracles {

    public MiraclesArchitecture(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Чудо архитектуры " + getName();
    }
}
