package com.dodemy.trackshipment.http;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * HttpHandler is basically a process that runs in response to a request made to an ASP.NET Web application.
 *HttpUrlConnection or okhttp instead
 */
public abstract class HttpHandler {
    public abstract HttpUriRequest getHttpRequestMethod();

    public abstract void onResponse(String result);

    public void execute(){
        new AsyncHttpTask(this).execute();
    }
}

