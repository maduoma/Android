package com.dodemy.todo.services;

import android.app.IntentService;
import android.content.Intent;

import android.util.Log;

import androidx.annotation.Nullable;

public class DueCheckIntentService extends IntentService {
    private static final String TAG = DueCheckIntentService.class.getSimpleName();
    public DueCheckIntentService() {
        super("DueCheckIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "Service running");
    }
}
