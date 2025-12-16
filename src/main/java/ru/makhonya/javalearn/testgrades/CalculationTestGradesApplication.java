package ru.makhonya.javalearn.testgrades;

import ru.makhonya.javalearn.testgrades.util.AuditoriumMatrixLogger;
import ru.makhonya.javalearn.testgrades.util.ClassroomSeatingHelper;
import ru.makhonya.javalearn.testgrades.util.NeighborhoodCalculationTool;

public class CalculationTestGradesApplication {

	void main() {
		int[] classroom = ClassroomSeatingHelper.automatedSeatingAssignment();
		AuditoriumMatrixLogger.arrayLogger(classroom, "Рассадка аудитории ->");

		int[] result = NeighborhoodCalculationTool.calculationGrades(classroom);
		AuditoriumMatrixLogger.arrayLogger(result, "Рассадка с учетом оценок ->");
	}
}