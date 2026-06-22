package ru.makhonya.javalearn.bureaucracy;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class EQueue implements Bureaucracy {
    private final List<Bureaucracy> windows;
    private final Map<UUID, Bureaucracy> routing = new ConcurrentHashMap<>();

    public EQueue(Bureaucracy inexperienced, Bureaucracy grandma, Bureaucracy classic) {
        this.windows = List.of(inexperienced, grandma, classic);
    }

    private Bureaucracy pickWindow() {
        return windows.get(ThreadLocalRandom.current().nextInt(windows.size()));
    }

    @Override
    public Future<Document> sign(Document document) {
        Bureaucracy window = pickWindow();
        routing.put(document.getDocumentId(), window);
        return window.sign(document);
    }

    @Override
    public void revoke(UUID documentId) {
        Bureaucracy window = routing.remove(documentId);

        if (window != null) {
            window.revoke(documentId);
        }
    }

    @Override
    public void shutdown() {
        for (Bureaucracy b : windows) {
            b.shutdown();
        }

        routing.clear();
    }
}
