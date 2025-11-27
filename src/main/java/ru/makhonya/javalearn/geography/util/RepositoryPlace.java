package ru.makhonya.javalearn.geography.util;

import ru.makhonya.javalearn.geography.*;

public class RepositoryPlace {

	private static final Location[] PLACES = {
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

	public static Location randomPlace() {
		return PLACES[(int) (Math.random() * PLACES.length - 1)];
	}

}
