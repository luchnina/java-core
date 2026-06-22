package ru.makhonya.javalearn.bureaucracy;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public class Classic implements Bureaucracy {
    private final Map<UUID, CompletableFuture<Document>> queue = new ConcurrentHashMap<>();

    @Override
    public Future<Document> sign(Document document) {
        CompletableFuture<Document> future = new CompletableFuture<>();
        queue.put(document.getDocumentId(), future);
        return future;
    }

    @Override
    public void revoke(UUID documentId) {
        CompletableFuture<Document> future = queue.remove(documentId);
        if (future != null) {
            future.completeExceptionally(new NoSuchElementException());
        }
    }

    @Override
    public void shutdown() {
        queue.keySet().forEach(this::revoke);
    }
}
