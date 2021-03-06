package com.example.mfekr.bakiongapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.example.mfekr.bakiongapp.BuildConfig;
import com.example.mfekr.bakiongapp.MainActivity;
import com.example.mfekr.bakiongapp.Model.Ingredient;
import com.example.mfekr.bakiongapp.R;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgetProvider extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);
        SharedPreferences sharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        views.setTextViewText(R.id.tv_title, sharedPreferences.getString("WIDGET_TITLE", ""));
        views.setTextViewText(R.id.tv_content, sharedPreferences.getString("WIDGET_INGREDIENT", ""));


        Intent openAppIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity (context, 0, openAppIntent, 0);
        views.setOnClickPendingIntent (R.id.tv_title, pendingIntent);
        views.setOnClickPendingIntent (R.id.tv_content, pendingIntent);

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

