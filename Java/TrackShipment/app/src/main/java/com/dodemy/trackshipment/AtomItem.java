package com.dodemy.trackshipment;

import java.io.Serializable;

/**
 * AtomItem is a item of each shipment.
 */
public class AtomItem implements Serializable {
    private static final long serialVersionUID = -5435670920302756945L;

    private String name = "";
    private double weight;
    private int quantity;

    public AtomItem(String name, double weight, int quantity) {
        this.setName(name);
        this.setWeight(weight);
        this.setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
