package ru.makhonya.javalearn.geography.util;

import ru.makhonya.javalearn.geography.Location;

import static java.lang.Math.*;

public class DistanceCalculator {

	private static final double EARTH_RADIUS_KM = 6371.0;

	public static double calculate(Location a, Location b) {
		if (a.equals(b)) return 0;

		double[] coordinatesPointA = a.coordinates();
		double[] coordinatesPointB = b.coordinates();

		double dLat = toRadians(coordinatesPointB[0] - coordinatesPointA[0]);
		double dLon = toRadians(coordinatesPointB[1] - coordinatesPointA[1]);

		double trigonometryCalc = sin(dLat / 2) * sin(dLat / 2)
				+ cos(toRadians(coordinatesPointA[0])) * cos(toRadians(coordinatesPointB[0]))
				* sin(dLon / 2) * sin(dLon / 2);

		double angle = 2 * atan2(sqrt(trigonometryCalc), sqrt(1 - trigonometryCalc));
		return EARTH_RADIUS_KM * angle;
	}

}
