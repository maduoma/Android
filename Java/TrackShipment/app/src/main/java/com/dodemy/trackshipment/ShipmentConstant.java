package com.dodemy.trackshipment;

import android.util.Log;

import java.util.ArrayList;

/**
 * ShipmentConstant contains accessToken, xml.
 *
 */
public class ShipmentConstant {

    private String xml;

    public String getAccessToken() {
        return accessToken;
    }

    private String accessToken;

    private boolean isLogin = false;

    private static ShipmentConstant shipment = new ShipmentConstant();

    private ShipmentConstant() {}

    public static ShipmentConstant getInstance(){
        return shipment;
    }

    public void createShipmentXML(ArrayList<AtomItem> items, String[] sender, String[] receiver, String type){
        xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>";
        xml += "<shipment>";
        xml += "<courier_name>" + sender[0] + "</courier_name>";
        xml += "<courier_address>" + sender[1] + "</courier_address>";
        xml += "<receive_name>" + receiver[0] + "</receive_name>";
        xml += "<receive_address>" + receiver[1] + "</receive_address>";
        xml += "<type>" + type;
        xml += "</type>";
        xml += "<items>";
        for(int i = 0 ; i < items.size(); i++){
            xml += "<item>";
            xml += "<name>" + items.get(i).getName() +"</name>";
            xml += "<weight>" + items.get(i).getWeight() +"</weight>";
            xml += "<quantity>" + items.get(i).getQuantity() +"</quantity>";
            xml += "</item>";
        }
        xml += "</items>";
        xml += "</shipment>";

        Log.d("show create xml",xml);
    }

    public void calculateXML(ArrayList<AtomItem> items, String type){
        xml = "<shipment>";
        xml += "<type>" + type + "</type>";
        xml += "<items>";
        for(int i = 0 ; i < items.size(); i++){
            xml += "<item>";
            xml += "<name>" + items.get(i).getName() +"</name>";
            xml += "<weight>" + items.get(i).getWeight() +"</weight>";
            xml += "<quantity>" + items.get(i).getQuantity() +"</quantity>";
            xml += "</item>";
        }
        xml += "</items>";
        xml += "</shipment>";

        Log.d("show calculate xml",xml);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getXml() { return xml; }
}
