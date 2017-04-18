package com.example.bikramkoju.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Bikramkoju on 3/16/2017.
 */

public class BroadcastClass extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri uri=Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+context.getPackageName()+"/"+R.raw.koju);
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        intent=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,10,intent,0);
        Notification notification=new Notification.Builder(context).setSmallIcon(R.mipmap.ic_launcher).setSound(uri)
                .setContentTitle("New Notification")
                .setContentText("a new notification from your app").setContentIntent(pendingIntent).build();
        notificationManager.notify(0,notification);

    }
}
