package ru.makhonya.javalearn.payment.exception.technical;

import ru.makhonya.javalearn.payment.transaction.Card;

public final class TransactionFailedException extends NetworkException {

  /**
   * Не удалось открыть транзакцию.
   *
   * @param terminalId ID терминала
   * @param card номер карты
   */
  public TransactionFailedException(String terminalId, Card card) {
    super(String.format("Не удалось открыть транзакцию для терминала %s, карта %s",
        terminalId,
        card.maskedNumber()
    ));
  }
}
