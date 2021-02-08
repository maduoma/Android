package com.dodemy.todo.broadcast_receivers;

import com.dodemy.todo.services.DueCheckIntentService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DailyAlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 1201;
    public static final String ACTION = "com.dodemy.todo.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent dueCheckIntent = new Intent(context, DueCheckIntentService.class);
        dueCheckIntent.putExtra("foo", "bar");
        context.startService(dueCheckIntent);
    }
}
