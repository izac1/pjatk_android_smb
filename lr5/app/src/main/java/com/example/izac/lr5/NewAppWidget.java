package com.example.izac.lr5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.File;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String UrlClick = "UrlClick";
    private static final String PicClick = "PicClick";
    private static final String SoundClick = "SoundClick";
    final String LOG_TAG = "myLogs";
    ImageView image;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setOnClickPendingIntent(R.id.button,getPendingSelfIntent(context, UrlClick));
        views.setOnClickPendingIntent(R.id.button2,getPendingSelfIntent(context, PicClick));
        views.setOnClickPendingIntent(R.id.button3,getPendingSelfIntent(context, SoundClick));
        //views.setTextViewText(R.id.appwidget_text, widgetText);

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
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (UrlClick.equals(intent.getAction())){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            context.startActivity(browserIntent);
        }else if(PicClick.equals(intent.getAction())){
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            views.setImageViewResource(R.id.imageView2,R.drawable.test);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            ComponentName name = new ComponentName(context, NewAppWidget.class);
            int [] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(name);
            manager.updateAppWidget(ids, views);
        }else if(SoundClick.equals(intent.getAction())){
            MediaPlayer mPlay = MediaPlayer.create(context, R.raw.testmp3);
            mPlay.start();
        }
    }

    protected static PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, NewAppWidget.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}

