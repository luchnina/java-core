package ru.makhonya.javalearn.bureaucracy;

import java.util.UUID;
import java.util.concurrent.*;

public class Inexperienced implements Bureaucracy {
    private final Grandma grandma;
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public Inexperienced(Grandma grandma) {
        this.grandma = grandma;
    }

    @Override
    public Future<Document> sign(Document document) {
        Future<Document> future = grandma.sign(document);
        CompletableFuture<Document> result = new CompletableFuture<>();

        executor.submit(() -> {
            try {
                Document signed = future.get(1, TimeUnit.MINUTES);
                result.complete(signed);
            } catch (TimeoutException e) {
                grandma.revoke(document.getDocumentId());
                result.complete(document);
            } catch (Exception e) {
                grandma.revoke(document.getDocumentId());
                result.completeExceptionally(e);
            }
        });

        return result;
    }

    @Override
    public void revoke(UUID documentId) {
        grandma.revoke(documentId);
    }

    @Override
    public void shutdown() {
        grandma.shutdown();
    }
}
