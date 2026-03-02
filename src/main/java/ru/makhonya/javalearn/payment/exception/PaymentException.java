package ru.makhonya.javalearn.payment.exception;

/**
 * Базовое исключение для всех ошибок платежной системы.
 */
public class PaymentException extends RuntimeException {

  public PaymentException() {
    super();
  }

  /**
   * @param message описание ошибки
   */
  public PaymentException(String message) {
    super(message);
  }

  /**
   * @param message описание ошибки
   * @param cause исходная причина
   */
  public PaymentException(String message, Throwable cause) {
    super(message, cause);
  }
}
