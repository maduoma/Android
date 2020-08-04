package com.dodemy.trackshipment.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * from https://github.com/ixistic/Shipment-Tracking-Service/
 */
public class Shipments {
    private static Shipments shipments = new Shipments();

    private Shipments() {
    }

    private List<Long> deleteID = new ArrayList<Long>();

    public static Shipments getInstance() {
        return shipments;
    }

    private List<Shipment> shipmentsList = new ArrayList<Shipment>();

    public List<Shipment> getShipmentsList() {
        return shipmentsList;
    }

    public void setShipmentsList(List<Shipment> shipmentsList) {
        this.shipmentsList = shipmentsList;
    }

    public void addDeleteID(long id) {
        this.deleteID.add(id);
    }

    public List<Long> getDeleteID() {
        return deleteID;
    }

    public void setDeleteID(List<Long> deleteID) {
        this.deleteID = deleteID;
    }


    public void addShipment(Shipment shipment) {
        this.shipmentsList.add(shipment);
    }
}
