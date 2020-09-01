package com.dodemy.retrofitnetworkcalls;

public class JSONResponse {
    private AndroidVersion[] android;

    public JSONResponse(AndroidVersion[] android) {
        this.android = android;
    }

    public AndroidVersion[] getAndroid() {
        return android;
    }

    /*
    private AndroidVersion[] android;

    public AndroidVersion[] getAndroid() {
        return android;
    }
     */
}