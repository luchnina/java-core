package ru.makhonya.javalearn.composition.user;

import java.util.Objects;

public class Passport {

    private String series;
    private String number;

    public Passport(String series, String number) {
        this.series = series;
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public String getSeriesNumber() {
        return String.join("-", series, number);
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport [series=" + series + ", number=" + number + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, number);
    }
}
