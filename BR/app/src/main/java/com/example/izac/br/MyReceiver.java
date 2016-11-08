package com.example.izac.br;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;

public class MyReceiver extends BroadcastReceiver {
    private final static AtomicInteger c = new AtomicInteger(3);
    public MyReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra("com.example.izac.send_messege.broadcast.Message");
        Intent intent1 = new Intent();
        intent1.putExtra("com.example.izac.send_messege.broadcast.Message",text.toString());
        intent1.setComponent(new ComponentName("com.example.izac.send_messege","com.example.izac.send_messege.Main2Activity"));
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent1, 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.alert_dark_frame)
                        .setContentTitle("My notification")
                        .setContentText(text)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true)
                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(getID(), mBuilder.build());


    }
    public static int getID() {
        return c.incrementAndGet();
    }
}
