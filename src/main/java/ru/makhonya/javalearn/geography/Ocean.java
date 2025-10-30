package ru.makhonya.javalearn.geography;

public enum Ocean {
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