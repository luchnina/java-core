package ru.makhonya.javalearn.testgrades;

public class CStudent extends Student {

  /**
   * Создает экземпляр троечника с указанной оценкой.
   *
   * @param name оценка троечника (от 0 до 5 включительно)
   * @throws IllegalArgumentException если оценка вне диапазона [0, 5]
   * @see Student#Student(int)
   */
  public CStudent(int name) {
    super(name);
  }

  /**
   * Вычисляет итоговый балл троечника на основе оценки соседа по парте.
   *
   * <p><strong>Логика вычисления:</strong></p>
   * <ul>
   *   <li>Если сосед по парте имеет оценку 2 или 3 → возвращает 3</li>
   *   <li>Если сосед по парте имеет оценку 4 или 5 → возвращает оценку соседа</li>
   *   <li>Если соседа нет, то возвращаем 4</li>
   * </ul>
   *
   * @param student массив оценок соседей (ожидется минимум 2 элемента):
   * индекс 0 — сосед спереди,
   * индекс 1 — сосед по парте (основной для расчета),
   * индекс 2 — сосед сзади (игнорируется).
   * @return итоговый балл троечника согласно логике выше
   * @throws NullPointerException если {@code student == null}
   * @throws IllegalArgumentException если {@code student.length < 2}
   * (нет соседа по парте на позиции [1])
   */
  @Override
  public int scoreCalculation(int[] student) {

    if (student == null) {
      throw new NullPointerException("Массив соседей не может быть null");
    }

    if (student.length < 2) {
      throw new IllegalArgumentException(
          String.format("Требуется минимум 2 соседа для анализа, получено: %d", student.length));
    }

    int deskNeighbor = student[1];

    switch (deskNeighbor) {
      case 3, 2 -> {
        return 3;
      }
      case 4, 5 -> {
        return deskNeighbor;
      }
      case 0 -> {
        return 4;
      }
      default -> {
        throw new IllegalStateException(
            String.format("Непредвиденное значение соседа по парте: %d", deskNeighbor));
      }
    }
  }
}
