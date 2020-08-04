package com.dodemy.trackshipment.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * from https://github.com/ixistic/Shipment-Tracking-Service/
 */
public class Shipment implements Serializable {
    public static final String STATUS_CREATED = "created";
    public static final String STATUS_PACKED = "packed";
    public static final String STATUS_SENDING = "sending";
    public static final String STATUS_RECEIVED = "received";
    public static final String TYPE_EMS = "EMS";
    private static final long serialVersionUID = 3645343276027601559L;

    private long id;
    private String status;
    private String status_created_time;
    private String status_packed_time;
    private String status_sending_time;
    private String status_received_time;
    private String type;
    private String courier_name;
    private String courier_address;
    private String receive_name;
    private String receive_address;
    private float total_weight;
    private float total_cost;
    private List<Item> item = new ArrayList<Item>();

    public Shipment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_created_time() {
        return status_created_time;
    }

    public void setStatus_created_time(String status_created_time) {
        this.status_created_time = status_created_time;
    }

    public String getStatus_packed_time() {
        return status_packed_time;
    }

    public void setStatus_packed_time(String status_packed_time) {
        this.status_packed_time = status_packed_time;
    }

    public String getStatus_sending_time() {
        return status_sending_time;
    }

    public void setStatus_sending_time(String status_sending_time) {
        this.status_sending_time = status_sending_time;
    }

    public String getStatus_received_time() {
        return status_received_time;
    }

    public void setStatus_received_time(String status_received_time) {
        this.status_received_time = status_received_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourier_name() {
        return courier_name;
    }

    public void setCourier_name(String courier_name) {
        this.courier_name = courier_name;
    }

    public String getCourier_address() {
        return courier_address;
    }

    public void setCourier_address(String courier_address) {
        this.courier_address = courier_address;
    }

    public String getReceive_name() {
        return receive_name;
    }

    public void setReceive_name(String receive_name) {
        this.receive_name = receive_name;
    }

    public String getReceive_address() {
        return receive_address;
    }

    public void setReceive_address(String receive_address) {
        this.receive_address = receive_address;
    }

    public float getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(float total_cost) {
        this.total_cost = total_cost;
    }

    public float getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(float total_weight) {
        this.total_weight = total_weight;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public void addItem(Item item) {
        this.item.add(item);
        if (item.getShipment() != this) {
            item.setShipment(this);
        }
    }

    public float calTotalWeight() {
        float total = 0;
        for (Item single_item : item) {
            total += single_item.getTotalWeight();
        }
        return total;
    }

    public float calCostByFreightRates(float weight) {
        if (getType().equals(TYPE_EMS)) {
            return (weight / 20 * 8) + 20;

        } else {
            return (weight / 20 * 8) + 4;
        }
    }
}