package com.dodemy.trackshipment;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * LoginActivity authorize to the service.
 *
 */
public class LoginActivity extends Activity{

    private String accessToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        WebView WebViw = (WebView) findViewById(R.id.webView1);
        WebViw.getSettings().setJavaScriptEnabled(true);
        WebViw.loadUrl("http://track-trace.tk:8080/shipments/auth");
//        WebViw.loadUrl("https://m.google.com");
        WebViw.setWebViewClient(new CustomWebViewClient());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_action,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_close){
            Intent newActivity = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(newActivity);

            overridePendingTransition(R.anim.fadein,
                    R.anim.fadeout);
        }
        return super.onOptionsItemSelected(item);
    }

    //web view client implementation
    private class CustomWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains("http://track-trace.tk:8080/shipments/access/")){
                String result = url.toString();
                Log.d("Response",result);

                accessToken = result.replace("http://track-trace.tk:8080/shipments/access/","");
                Log.d("AccessToken : " , accessToken);

                if(accessToken != null){
                    ShipmentConstant shipment = ShipmentConstant.getInstance();
                    shipment.setAccessToken(accessToken);
                    shipment.setLogin(true);
                }

                Intent newActivity = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(newActivity);
            }
            view.loadUrl(url);

            return true;
        }
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}

