package ru.makhonya.javalearn.geography;

public class Country {

    private String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String getSimpleNameClass = this.getClass().getSimpleName();

        String getRuName = switch (getSimpleNameClass) {
            case "Country" -> "Страна";
            case "Region" -> "Регион";
            case "City" -> "Город";
            case "District" -> "Округ";
            default -> "Если бы мы знали что это такое ...";
        };

        return getRuName + " " + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;
        return name.equals(country.name);
    }
}
