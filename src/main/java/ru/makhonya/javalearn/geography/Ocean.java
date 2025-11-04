package ru.makhonya.javalearn.geography;

public enum Ocean implements  Location {
    ATLANTIC("Атлантический"),
    INDIAN("Индийский"),
    ARCTIC("Северный Ледовитый"),
    QUIET("Тихий"),
    SOUTHERN("Южный");

    final String name;

    Ocean(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name + " океан";
    }
}