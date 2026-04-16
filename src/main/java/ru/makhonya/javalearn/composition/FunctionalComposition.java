package ru.makhonya.javalearn.composition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.makhonya.javalearn.composition.user.Passport;
import ru.makhonya.javalearn.composition.user.Person;

public class FunctionalComposition {

    private static final Logger log = LoggerFactory.getLogger(FunctionalComposition.class);

    void main() {

        Passport passport = new Passport("1234", "1234");
        Person person = new Person(passport);

        log.info(person.getPassportNumberAndSeries().apply(person));

        Person personWithoutPassport = new Person(null);
        log.info(person.getPassportNumberAndSeries().apply(personWithoutPassport));
    }
}
