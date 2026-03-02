package ru.makhonya.javalearn.testgrades;

import java.util.Arrays;

public class FStudent extends Student {

  /**
   * Создает экземпляр двоечника.
   *
   * @param name оценка ученика
   * @throws IllegalArgumentException если оценка вне диапазона [0, 5] (унаследовано от {@link Student})
   */
  public FStudent(int name) {
    super(name);
  }

  /**
   * Вычисляет балл двоечника как среднее арифметическое двух лучших соседей
   * (после сортировки массива по возрастанию берется среднее элементов с индексами 1 и 2).
   *
   * <p><strong>Логика:</strong>
   * <ul>
   *   <li>Сортирует массив соседей по возрастанию</li>
   *   <li>Берет среднее `student[1]` и `student[2]` (два лучших соседа)</li>
   *   <li>Если среднее ≤ 1, возвращает 2, иначе возвращает среднее</li>
   * </ul></p>
   *
   * @param student массив оценок трех соседей:
   * <ul>
   *   <li>индекс 0 — сосед спереди</li>
   *   <li>индекс 1 — сосед по парте</li>
   *   <li>индекс 2 — сосед сзади</li>
   * </ul>
   * Значение 0 означает отсутствие соседа.
   * @return вычисленный балл двоечника
   * @throws NullPointerException если {@code neighbours == null}
   * @throws IllegalArgumentException если {@code neighbours.length != 3}
   */
  @Override
  public int scoreCalculation(int[] student) {

    if (student == null) {
      throw new NullPointerException("Массив соседей не может быть null");
    }

    if (student.length != 3) {
      throw new IllegalArgumentException(
          String.format("Ожидается массив из 3 соседей, получено: %d", student.length));
    }

    int[] sortedNeighbours = Arrays.copyOf(student, student.length);
    Arrays.sort(sortedNeighbours);

    int half = (sortedNeighbours[1] + sortedNeighbours[2]) / 2;
    return (half <= 1) ? 2 : half;
  }
}
