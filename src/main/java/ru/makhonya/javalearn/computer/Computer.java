package ru.makhonya.javalearn.computer;

import java.util.Arrays;

public class Computer {

    private Display display;
    private int serialNumber;
    private String[] operatingSystems;

    public Computer(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int number) {
        this.serialNumber = number;
    }

    public String[] getOperatingSystems() {
        return operatingSystems;
    }

    public void setOperatingSystems(String system) {
        String[] operatingSystem = this.operatingSystems;
        operatingSystem = Arrays.copyOf(operatingSystem, operatingSystem.length + 1);
        operatingSystem[operatingSystem.length - 1] = system;
    }

    public boolean checkingAvailabilitySystem(String system) {
        for(String operatingSystem : operatingSystems) {
            if(operatingSystem.contains(system)) {
                return true;
            }
        }

        return false;
    }

    public Display getDisplay() {
        return display;
    }

    public static class Display {

        private int width;
        private int height;
        private int numberOfDisplay;

        public Display(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getNumberOfDisplay() {
            return numberOfDisplay;
        }

        public void setNumberOfDisplay(int numberOfDisplay) {
            this.numberOfDisplay = numberOfDisplay;
        }

        public double getDiagonal() {
            return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        }
    }
}
