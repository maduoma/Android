package com.dodemy.trackshipment.http;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * AsyncHttpTask is a helper class around Thread and Handler and does not constitute a generic threading framework.
 *
 *
 */
public class AsyncHttpTask extends AsyncTask<String, Void, String> {
    private HttpHandler httpHandler;

    public AsyncHttpTask(HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    protected String doInBackground(String... params) {
        InputStream input = null;
        String result = "";

        try{

            HttpClient httpClient = new DefaultHttpClient();

            HttpResponse response = httpClient.execute(httpHandler.getHttpRequestMethod());

            input = response.getEntity().getContent();

            if(input != null){
                result = convertInputStreamToString(input);
            }
            else{
                result = "Did not work";
            }

        }catch (Exception e){
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        httpHandler.onResponse(s);
    }

    private static String convertInputStreamToString(InputStream input) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
        String line = "";
        String result = "";
        while ((line = buffer.readLine()) != null)
            result += line;
        input.close();
        return result;
    }
}
