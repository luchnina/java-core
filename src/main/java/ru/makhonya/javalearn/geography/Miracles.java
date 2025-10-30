package ru.makhonya.javalearn.geography;

public abstract class Miracles {

    private String name;

    Miracles(String name) {
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
        String getSimpleNameClass = this.getClass().getSimpleName();

        String getRuName = switch (getSimpleNameClass) {
            case "MiraclesNature" -> "чудо природы";
            case "MiraclesArchitecture" -> "чудо архитектуры";
            default -> "Если бы мы знали что это такое ...";
        };

        return getRuName + " " + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Miracles miracles = (Miracles) o;
        return name.equals(miracles.name);
    }
}
