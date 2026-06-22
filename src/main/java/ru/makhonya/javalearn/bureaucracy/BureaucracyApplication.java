package ru.makhonya.javalearn.bureaucracy;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BureaucracyApplication {

    static void main() {
        Document doc = new Document(UUID.randomUUID(), "Заявление на отпуск", LocalDateTime.now());
        System.out.println("Документ: " + doc);
        Bureaucracy grandma = new Grandma();

        System.out.println("Тест Grandma");
        Instant start = Instant.now();
        Future<Document> future = grandma.sign(doc);

        try {
            future.get(15, TimeUnit.SECONDS);
            Instant finish = Instant.now();
            System.out.println("Время ожидания: "
                    + Duration.between(start, finish).toSeconds() + " сек");
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Ошибка подписи в Grandma: " + e.getCause());
        } catch (TimeoutException e) {
            System.err.println("Grandma не успела подписать документ за 15сек.");
        } finally {
            grandma.shutdown();
        }
    }


}
