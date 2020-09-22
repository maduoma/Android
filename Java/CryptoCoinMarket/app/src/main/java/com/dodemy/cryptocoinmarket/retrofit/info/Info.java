package com.dodemy.cryptocoinmarket.retrofit.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class Info implements Serializable {

    @SerializedName("status")
    @Expose
    private Status status;
    private final static long serialVersionUID = -7603899540880736836L;

    @SerializedName("data")
    @Expose
    private Map<String, Coin> data;

    public Map<String, Coin> getData() {
        return data;
    }

    public void setData(Map<String, Coin> data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
