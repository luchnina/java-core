package ru.makhonya.javalearn.testgrades.exception;

public class InvalidClassroomConfigurationException extends IllegalArgumentException {

  public InvalidClassroomConfigurationException(String message) {
    super(message);
  }

}
