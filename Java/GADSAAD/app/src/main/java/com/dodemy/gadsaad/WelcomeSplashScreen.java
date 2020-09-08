package com.dodemy.gadsaad;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class WelcomeSplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("");
        EasySplashScreen config = new EasySplashScreen(WelcomeSplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(R.color.white)
                .withBackgroundResource(R.color.black)
                .withLogo(R.drawable.gads);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}