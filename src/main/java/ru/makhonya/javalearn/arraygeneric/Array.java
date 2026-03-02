package ru.makhonya.javalearn.arraygeneric;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;


/// Удобная реализация массива
public class Array<Type> implements Iterable<Type> {

    private Type[] elements;

    private Array(Type[] elements) {
        this.elements = elements;
    }

    /// Использовать фабричный метод
    @Deprecated
    @SuppressWarnings("unchecked")
    public Array(Class<Type> clazz) {
        this.elements = (Type[]) java.lang.reflect.Array.newInstance(clazz, 0);
    }

    /// Получить "удобный массив" по элементам заданного класса
    @SuppressWarnings("unchecked")
    public static <ParamType> Array<ParamType> of(Class<ParamType> clazz) {
        ParamType[] array = (ParamType[]) java.lang.reflect.Array.newInstance(clazz, 0);
        return new Array<>(array);
    }

    /// Пустой ли массив
    public boolean isEmpty() {
        return size() == 0;
    }

    /// Размер массива, количество элементов
    public int size() {
        return elements.length;
    }

    /// Получить элемент по индексу
    ///
    /// @throws ArrayIndexOutOfBoundsException если индекс превышает размер массива
    public Type get(int index) {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Array is empty");
        }

        return elements[index];
    }

    /// Получить первый элемент
    ///
    /// @throws ArrayIndexOutOfBoundsException если массив пуст
    /// @see Array#get(int)
    public Type getFirst() {
        return get(0);
    }

    /// Получить последний элемент
    ///
    /// @throws ArrayIndexOutOfBoundsException если массив пуст
    /// @see Array#get(int)
    public Type getLast() {
        int[] i = new int[0];
        return get(size() - 1);
    }

    /// Получить java-массив из объекта
    public Type[] toArray() {
        return Arrays.copyOf(elements, size());
    }

    /// Добавление нового элемента в конец (массив динамически будет расширен)
    ///
    /// @return этот же расширенный массив, например для цепочек
    public Array<Type> add(Type element) {
        Type[] newElements = Arrays.copyOf(elements, size() + 1);
        newElements[size()] = element;
        this.elements = newElements;

        return this;
    }

    /// Добавление нового элемента по индексу, все элементы за ним будут
    /// сдвинуты для освобождения ему места (массив динамически будет расширен)
    ///
    /// @param index индекс на место которое встанет элемент
    /// @param element элемент для вставки
    /// @throws ArrayIndexOutOfBoundsException индекс превышает размерность
    public Array<Type> add(int index, Type element) {
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Array has size = %s but index = %s".formatted(size(), index));
        }

        if (index == size() - 1) {
            return add(element);
        }

        Type[] newElements = Arrays.copyOf(elements, size() + 1);
        System.arraycopy(elements, // откуда копировать
                index, // с какой позиции/индекса (включительно)
                newElements, // куда вставлять
                index + 1, // с какой позиции/индекса (включительно)
                size() - index // сколько скопировать
        );
        newElements[index] = element;

        elements = newElements;
        return this;
    }

    /// Удаление элемента по индексу, все элементы за ним будут сдвинуты для
    /// замещения пустоты на его месте (массив динамически будет сужен)
    ///
    /// @param index индекс с места которого удалят элемент
    /// @return удаленный элемент, null если сам объект null
    /// @throws ArrayIndexOutOfBoundsException индекс превышает размерность
    @Nullable
    public Type remove(int index) {
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Array has size = %s but index = %s".formatted(size(), index));
        }

        Type element = elements[size() - 1];

        if (index == size() - 1) {
            elements = Arrays.copyOf(elements, size() - 1);
        } else {
            Type[] newElements = Arrays.copyOf(elements, size());
            System.arraycopy(elements, // откуда копировать
                    index + 1, // с какой позиции/индекса (включительно)
                    newElements, // куда вставлять
                    index, // с какой позиции/индекса (включительно)
                    size() - index - 1 // сколько скопировать
            );
            elements = Arrays.copyOf(newElements, size() - 1);
        }

        return element;
    }

    /**
     * Создаёт и возвращает новый массив, содержащий элементы перданного.
     *
     * @param array массив, элементы которого будут добавлены в конец возвращаемого массива.
     * @throws NullPointerException если исходный массив равен null
     */
    public void addAll(Array<? extends Type> array) {
        if (array == null) {
            throw new NullPointerException("Исходный массив не может быть null");
        }

        int oldSize = size();
        int addSize = array.size();

        elements = Arrays.copyOf(elements, oldSize + addSize);
        System.arraycopy(array.elements, 0, elements, oldSize, addSize);
    }

    /**
     * Создает новый массив, содержащий элементы исходного массива от индекса start (включительно)
     * до индекса end (не включительно).
     *
     * <p>Пример: copyOfRange(array, 1, 4) вернет элементы с индексами 1, 2, 3</p>
     *
     * @param array исходный массив, из которого копируются элементы
     * @param start начальный индекс (включительно). Должен быть >= 0 и <= array.size()
     * @param end конечный индекс (не включительно). Должен быть > start и <= array.size()
     * @throws IndexOutOfBoundsException если start или end выходят за границы массива
     * @throws IllegalArgumentException если start > end
     * @throws NullPointerException если исходный массив равен null
     */
    public void copyOfRange(Array<? extends Type> array, int start, int end) {
        if (array == null) {
            throw new NullPointerException("Исходный массив не может быть null");
        }

        int size = array.size();

        if (start < 0 || start > size) {
            throw new IndexOutOfBoundsException(String.format("Начальный индекс start должен быть в диапазоне [0, %d], получено: %d", size, start));
        }

        if (end < 0 || end > size) {
            throw new IndexOutOfBoundsException(String.format("Конечный индекс end должен быть в диапазоне [0, %d], получено: %d", size, end));
        }

        if (start > end) {
            throw new IllegalArgumentException(String.format("Начальный индекс start (%d) не может быть больше конечного end (%d)", start, end));
        }

        elements = Arrays.copyOfRange(array.elements, start, end);
    }

    @NonNull
    public Iterator<Type> iterator() {
        return new ArrayIterator();
    }

    public boolean contains(Type value) {
        for (Type element : elements) {
            if (Objects.equals(element, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Arrays.equals(elements, ((Array<?>) o).elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    private class ArrayIterator implements Iterator<Type> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Type next() {
            return elements[index++];
        }
    }
}
