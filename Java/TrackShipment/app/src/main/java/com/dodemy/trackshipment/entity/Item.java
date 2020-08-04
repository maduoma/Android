package com.dodemy.trackshipment.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * from https://github.com/ixistic/Shipment-Tracking-Service/
 */
public class Item {
    private static final long serialVersionUID = 5460610151721574876L;

    private long id;
    private String name;
    private float weightPerUnit;
    private int quantity;
    private Shipment shipment;

    public Item() {
    }

    public Item(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weightPerUnit;
    }

    public void setWeight(float weight) {
        this.weightPerUnit = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalWeight() {
        return this.weightPerUnit * this.quantity;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
        if (!shipment.getItem().contains(this)) {
            shipment.getItem().add(this);
        }
    }

    @Override
    public String toString() {
        return String.format("[%d]", id);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass())
            return false;
        Item item = (Item) other;
        return item.getId() == this.getId();
    }

    /**
     * Construct sha1(secure hash) of a text string.
     *
     * @return string string of sha1
     */
    public String sha1() {
        int text = this.hashCode();
        String input = "" + id + text;
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }

        return sb.toString();
    }
}