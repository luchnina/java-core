package ru.makhonya.javalearn.payment.exception.technical;

public class NetworkException extends TechnicalException {

    /**
     * Ошибка сети с количеством попыток.
     *
     * @param operation название операции
     * @param attempts количество попыток
     */
    public NetworkException(String operation, int attempts) {
        super(String.format("Сетевая ошибка в %s после %d попытка", operation, attempts));
    }

    /**
     * Общая сетевая ошибка.
     *
     * @param operation название операции
     */
    public NetworkException(String operation) {
        super("Сетевая ошибка в " + operation);
    }

    public NetworkException() {

    }
}
