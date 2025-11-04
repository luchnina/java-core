package ru.makhonya.javalearn.geography;

public interface Location {

    default double[] coordinates() {
        double latitude = Math.random() * 100;
        double longitude = Math.random() * 100;

        return new double[]{latitude, longitude};
    }
}
