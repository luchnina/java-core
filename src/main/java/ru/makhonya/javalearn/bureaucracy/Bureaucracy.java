package ru.makhonya.javalearn.bureaucracy;

import java.util.UUID;
import java.util.concurrent.Future;

public interface Bureaucracy {

    /**
     * Принимает неподписанный документ и асинхронно возвращает подписанный.
     *
     * @param document документ, который нужно подписать
     * @return Future, в котором будет находиться подписанный документ
     * (может завершиться исключением, если подпись невозможна)
     */
    Future<Document> sign(Document document);

    /**
     * Запрашивает возврат уже выданного (или находящегося в обработке) документа.
     *
     * @param documentId уникальный идентификатор запрошенного документа
     */
    void revoke(UUID documentId);

    /**
     * Завершает работу бюрократа: останавливает все фоновые задачи,
     * возвращает неподписанные документы и освобождает ресурсы.
     */
    void shutdown();
}
