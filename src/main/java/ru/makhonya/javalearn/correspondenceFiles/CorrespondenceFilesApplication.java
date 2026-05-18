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
                .flatMap(s -> s.isEmpty() ? Stream.empty() : Arrays.stream(s.split(" ")))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.summingInt(e -> 1)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
