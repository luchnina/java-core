package ru.makhonya.javalearn.composition.user;

import java.util.Optional;
import java.util.function.Function;

public class Person {

    private Passport passport;

    public Person(Passport passport) {
        this.passport = passport;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Function<Person, String> getPassportNumberAndSeries() {
        return person -> Optional.ofNullable(person.getPassport())
                .map(Passport::getSeriesNumber)
                .orElse("Нет паспорта");
    }
}
