package ru.makhonya.javalearn.geography;

public class District extends City {

    private int quantity;

    District(String name) {
        super(name);
    }

    District(String name, int quantity) {
        super(name);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
