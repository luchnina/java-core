package ru.makhonya.javalearn.testgrades;

public class Student {

    //Хорошисты и отличники
    private final int name;

    public Student(int name) {
        if (name < 0 || name > 5) {
            throw new IllegalArgumentException(
                    String.format("Оценка должна быть от 0 до 5, получена: %d", name)
            );
        }
        this.name = name;
    }

    public int getName() {
        return name;
    }

    /// @param student соседи текущего ученика не учитывая его самого
    /// @return собственная оценка ученика ({@link #getName()})
    /// @throws NullPointerException если {@code student == null}
    /// @throws IllegalArgumentException если {@code student.length == 0}
    public int scoreCalculation(int[] student) {

        if (student == null) {
            throw new NullPointerException("Массив соседей не может быть null");
        }

        if (student.length == 0) {
            throw new IllegalArgumentException("Массив соседей не может быть пустым");
        }

        return getName();
    }
}