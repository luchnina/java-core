package ru.makhonya.javalearn.correspondenceFiles;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CorrespondenceFilesApplicationTest {

    @Test
    void getCorrespondenceFiles_whenString_thenReturnExpectedMap() {
        String str = "Слова могут быть использованы в разном регистре – регистр не должен учитываться, "
                + "считать это одним словом. Слова могут быть использованы в разном регистре " +
                "– регистр не должен учитываться,";
        Map<String, Integer> expected = Map.ofEntries(
                Map.entry("быть", 2),
                Map.entry("в", 2),
                Map.entry("должен", 2),
                Map.entry("использованы", 2),
                Map.entry("могут", 2),
                Map.entry("не", 2),
                Map.entry("разном", 2),
                Map.entry("регистр", 2),
                Map.entry("регистре", 2),
                Map.entry("слова", 2)
        );

        assertEquals(expected, CorrespondenceFilesApplication.getCorrespondenceFiles(str),
                "Map не соответствует ожидаемому набору 10 пар ключ -> значение");
    }
}