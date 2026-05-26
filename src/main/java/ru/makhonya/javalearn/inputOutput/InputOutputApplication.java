package ru.makhonya.javalearn.inputOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class InputOutputApplication {

    private static final Logger log = LoggerFactory.getLogger(InputOutputApplication.class);
    private static final JsonMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .build();

    private static final String INPUT_FILE = "human.json";
    private static final String OUTPUT_FILE = "human_updated.json";

    static void main() {
        try {
            Path sourcePath = getSourcePath();
            List<Human> humans = loadHumans(sourcePath);
            List<Human> updated = incrementAge(humans);
            String json = toJson(updated);
            Path targetPath = sourcePath.getParent().resolve(OUTPUT_FILE);
            saveJson(targetPath, json);
            log.info("Successfully written {} to {}", OUTPUT_FILE, targetPath.toAbsolutePath());
        } catch (Exception e) {
            log.error("Processing failed", e);
            System.exit(1);
        }
    }

    private static Path getSourcePath() throws URISyntaxException {
        URL url = InputOutputApplication.class.getClassLoader().getResource(INPUT_FILE);
        Objects.requireNonNull(url, INPUT_FILE + " not found");
        return Paths.get(url.toURI());
    }

    private static List<Human> loadHumans(Path path) throws IOException {
        String content = Files.readString(path, StandardCharsets.UTF_8);
        return mapper.readValue(content, new TypeReference<>() {
        });
    }

    private static List<Human> incrementAge(List<Human> list) {
        return list.stream()
                .peek(h -> h.setAge(h.getAge() + 1))
                .toList();
    }

    private static String toJson(List<Human> list) {
        return mapper.writeValueAsString(list);
    }

    private static void saveJson(Path target, String json) throws IOException {
        Files.writeString(target, json, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
