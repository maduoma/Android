package com.dodemy.room_bakingapp.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.dodemy.room_bakingapp.R;
import com.dodemy.room_bakingapp.data.FoodRepository;
import com.dodemy.room_bakingapp.ui.main.MainActivity;
import com.dodemy.room_bakingapp.utils.Constant;
import com.dodemy.room_bakingapp.utils.InjectorUtil;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        FoodRepository repository = InjectorUtil.provideRepository(context);
        String text = repository.getCurrentRecipeIngredient();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);
        views.setTextViewText(R.id.ingredient_text, text);


        // Create an Intent to launch ExampleActivity
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, Constant.PENDING_INTENT_REQUEST_CODE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the layout for the App Widget and attach an on-click listener
        // to the button
        views.setOnClickPendingIntent(R.id.ingredient_text, pendingIntent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

