package ru.makhonya.javalearn.correspondenceFiles;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CorrespondenceFilesApplication {

    static void main() {

    }

    public static Map<String, Integer> getCorrespondenceFiles(String string) {

        if (string.isEmpty()) {
            throw new IllegalArgumentException("строка не может быть пустой");
        }

        return Stream.of(string)
                .map(s -> s
                        .toLowerCase()
                        .replaceAll("[^a-zа-яё ]", "")
                        .replaceAll("\\s+", " ")
                        .trim()
                )
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .sorted()
                .collect(Collectors.toMap(
                        Function.identity(),
                        s -> 1,
                        Integer::sum,
                        LinkedHashMap::new
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
