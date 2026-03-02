package ru.makhonya.javalearn.arraygeneric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ArrayApplication {

    private final static Logger log = LoggerFactory.getLogger(ArrayApplication.class);

    static void main() {
        Array<Integer> array = Array.of(Integer.class);
        Array<Integer> array1 = Array.of(Integer.class);

        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);

        array1.add(123);
        array1.add(345345);

        array.addAll(array1);

        array.copyOfRange(array, 1, 4);

        log.info(Arrays.toString(array.toArray()));
        log.info(Arrays.toString(array1.toArray()));
    }

}
