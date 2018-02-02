package com.example.acn0036.kakaotalkreceiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListview;
    private NotificationListAdapter mAdapter;
    private ArrayList<NotificationData> mNotificationDataArrayList;
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListview = (ListView) findViewById(R.id.listview);

        mNotificationDataArrayList = new ArrayList<>();
        mAdapter = new NotificationListAdapter(getApplicationContext(), mNotificationDataArrayList);

        mListview.setAdapter(mAdapter);

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String title = intent.getStringExtra("title");
                String text = intent.getStringExtra("text");
                String time = intent.getStringExtra("time");

                byte[] byteArray = intent.getByteArrayExtra("icon");
                Bitmap bmp = null;
                if (byteArray != null) {
                    bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                }
                NotificationData notificationData = new NotificationData();
                notificationData.setName(title);
                notificationData.setText(text);
                notificationData.setTime(time);
                notificationData.setImage(bmp);

                int cnt=0;

                if (mNotificationDataArrayList != null) {
                    mNotificationDataArrayList.add(notificationData);
                    mAdapter.notifyDataSetChanged();
                    cnt=0;
                } else {
                    mNotificationDataArrayList = new ArrayList<>();
                    mNotificationDataArrayList.add(notificationData);
                    mAdapter = new NotificationListAdapter(getApplicationContext(), mNotificationDataArrayList);
                    mListview.setAdapter(mAdapter);

                    cnt++;
                    notification(cnt);

                }
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter("Msg"));
    }

    public void notification(int num) {
        Resources res = getResources();

        Intent notificationIntent = new Intent(this, Notification.class);
        notificationIntent.putExtra("notificationId", 9999); //전달할 값
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("상태바 드래그시 보이는 타이틀")
                .setContentText( num + "개의 메시지 미리보기")
                .setTicker("카카오톡 메시지 미리보기")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(android.app.Notification.DEFAULT_ALL);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(android.app.Notification.CATEGORY_MESSAGE)
                    .setPriority(android.app.Notification.PRIORITY_HIGH)
                    .setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        }

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1234, builder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}