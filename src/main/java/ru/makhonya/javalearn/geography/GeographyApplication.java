package ru.makhonya.javalearn.geography;

import ru.makhonya.javalearn.geography.utilities.DistanceCalculator;
import ru.makhonya.javalearn.geography.utilities.RepositoryPlace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeographyApplication {

    private static final Logger log = LoggerFactory.getLogger(GeographyApplication.class);

    void main() {
        Location a = RepositoryPlace.randomPlace();
        Location b = RepositoryPlace.randomPlace();

        double distance = DistanceCalculator.calculate(a, b);

        log.info("Расстояние между {} и {} равно {} км.", a, b, (int) distance);
    }
}
