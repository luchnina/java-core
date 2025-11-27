package ru.makhonya.javalearn.testgrades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.makhonya.javalearn.testgrades.util.ClassroomSeatingHelper;
import ru.makhonya.javalearn.testgrades.util.NeighborhoodCalculationTool;

import java.util.Arrays;

public class CalculationTestGradesApplication {

	private static final Logger log = LoggerFactory.getLogger(CalculationTestGradesApplication.class);

	void main() {
		int[] classroom = ClassroomSeatingHelper.automatedSeatingAssignment();
		log.info("Исходный массив -> {}", Arrays.toString(classroom));

		int[] result = NeighborhoodCalculationTool.calculationGrades(classroom);
		log.info("Массив оценок   -> {}", Arrays.toString(result));
	}
}

