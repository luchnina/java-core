package ru.makhonya.javalearn.geography;

public class GeographyApplication {

    private static final Object[] places = {
            new MiraclesArchitecture("Тадж-Махал"),
            new MiraclesArchitecture("Колизей"),
            new MiraclesArchitecture("Стоунхендж"),
            new MiraclesArchitecture("Петра"),
            new MiraclesArchitecture("Ангкор-Ват"),
            new MiraclesArchitecture("Храм Василия Блаженного"),

            new MiraclesNature("Гранд-Каньон"),
            new MiraclesNature("Амазонские тропические леса"),
            new MiraclesNature("Водопад Виктория"),
            new MiraclesNature("Северное сияние"),
            new MiraclesNature("Большой Барьерный риф"),
            new MiraclesNature("Йеллоустонский национальный парк"),

            new Country("Япония"),
            new Country("Канада"),
            new Country("Индия"),
            new Country("Египет"),
            new Country("Италия"),
            new Country("Новая Зеландия"),

            new Region("Бавария"),
            new Region("Каталония"),
            new Region("Квебек"),
            new Region("Калифорния"),
            new Region("Шотландия"),
            new Region("Сычуань"),

            new City("Прага"),
            new City("Рио-де-Жанейро"),
            new City("Токио"),
            new City("Кейптаун"),
            new City("Сидней"),
            new City("Стамбул"),

            new District("Бруклин"),
            new District("Гринвич"),
            new District("Монмартр"),
            new District("Гинза"),

            Ocean.ATLANTIC,
            Ocean.INDIAN,
            Ocean.SOUTHERN,
            Mainland.AFRICA,
            Mainland.EUROPE,
            Mainland.ASIA
    };

    static void main() {

        Object a = places[randomValue()];
        Object b = places[randomValue()];

        int distance = (int) calculatingDistance(a, b);

        System.out.println("\n Расстояние между " + a + " и " + b + " равна " + distance);
    }

    private static int randomValue() {
        return (int) (0 + Math.random() * (places.length - 1));
    }

    private static double calculatingDistance(Object a, Object b) {
        boolean objectCheckingEquality = a.equals(b);

        if (objectCheckingEquality) {
            return 0;
        }

        PositionMap point1 = new PositionMap(a.toString());
        PositionMap point2 = new PositionMap(b.toString());

        double[] coordinatesPoint1 = point1.coordinates();
        double[] coordinatesPoint2 = point2.coordinates();

        double translationUnitsMeasurement = translationUnitsMeasurement(coordinatesPoint2, coordinatesPoint1);

        double R = 6371.0; // радиус Земли в километрах
        return R * translationUnitsMeasurement;
    }

    private static double translationUnitsMeasurement(double[] coordinatesPoint2, double[] coordinatesPoint1) {
        double dLat = Math.toRadians(coordinatesPoint2[0] - coordinatesPoint1[0]);
        double dLon = Math.toRadians(coordinatesPoint2[1] - coordinatesPoint1[1]);

        double trigonometryCalc = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(coordinatesPoint1[0])) * Math.cos(Math.toRadians(coordinatesPoint2[0]))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        return 2 * Math.atan2(Math.sqrt(trigonometryCalc), Math.sqrt(1 - trigonometryCalc));
    }

    private record PositionMap(String namePlace) implements Location {

        @Override
        public double[] coordinates() {
            double latitude = calculationCoordinateValue();
            double longitude = calculationCoordinateValue();

            return new double[]{latitude, longitude};
        }

        private static double calculationCoordinateValue() {
            return 0 + Math.random() * 100;
        }
    }
}
