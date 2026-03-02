package ru.makhonya.javalearn.payment.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger("PaymentApplication");

  public static void info(String message, Object... args) {
    LOGGER.info(message, args);
  }

  public static void warn(String message, Object... args) {
    LOGGER.warn(message, args);
  }
}
