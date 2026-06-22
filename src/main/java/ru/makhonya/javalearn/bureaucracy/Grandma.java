package ru.makhonya.javalearn.bureaucracy;

import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.*;

public class Grandma implements Bureaucracy {
    private final Queue<Document> queue = new ConcurrentLinkedQueue<>();
    private final Map<UUID, CompletableFuture<Document>> pending = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();
    private final Map<UUID, Document> documents = new ConcurrentHashMap<>();

    public Grandma() {
        // «Бабка» берёт документ раз в 15сек и подписывает его.
        scheduler.scheduleAtFixedRate(
                this::processNext,
                0,
                15,
                TimeUnit.SECONDS
        );
    }

    /**
     * Принимает неподписанный документ и возвращает Future,
     * который будет завершён, когда документ будет подписан.
     */
    @Override
    public Future<Document> sign(Document document) {
        CompletableFuture<Document> future = new CompletableFuture<>();

        pending.put(document.getDocumentId(), future);
        documents.put(document.getDocumentId(), document);

        queue.offer(document);

        return future;
    }


    @Override
    public void revoke(UUID documentId) {
        CompletableFuture<Document> future = pending.remove(documentId);

        if (future != null) {
            future.complete(documents.remove(documentId));
        }
    }

    @Override
    public void shutdown() {
        scheduler.shutdownNow();

        for (UUID id : documents.keySet()) {
            revoke(id);
        }
    }

    /**
     * Внутренний метод, вызываемый планировщиком каждые 15сек.
     */
    private void processNext() {
        Document doc;

        doc = queue.poll();
        if (doc == null) return;

        CompletableFuture<Document> future = pending.remove(doc.getDocumentId());
        if (future != null) {
            doc.setSignature();
            future.complete(doc);
        } else {
            processNext();
        }
    }
}
