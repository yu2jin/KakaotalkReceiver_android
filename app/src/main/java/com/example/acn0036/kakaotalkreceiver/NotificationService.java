package com.example.acn0036.kakaotalkreceiver;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NotificationService extends NotificationListenerService {
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String pack = sbn.getPackageName();

        if (pack.equals("com.kakao.talk")) {
            Log.e("Kakao Pack", pack);

            Bundle extras = sbn.getNotification().extras;
            String title = extras.getString("android.title");
            String text = extras.getCharSequence("android.text").toString();

            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            String current = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);

            Log.e("Date", current);

            Bitmap icon = sbn.getNotification().largeIcon;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            icon.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent msgrcv = new Intent("Msg");
            msgrcv.putExtra("icon", byteArray);
            msgrcv.putExtra("title", title);
            msgrcv.putExtra("text", text);
            msgrcv.putExtra("time", current);

            LocalBroadcastManager.getInstance(mContext).sendBroadcast(msgrcv);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);

        Log.e("notification removed", "notification removed");

        ArrayList<NotificationData> notificationDataArrayList = new ArrayList<>();
        notificationDataArrayList.clear();
        NotificationListAdapter adapter = new NotificationListAdapter(getApplicationContext(), notificationDataArrayList);
        adapter.notifyDataSetChanged();
    }
}