package com.example.acn0036.kakaotalkreceiver;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by ACN0036 on 2017-10-20.
 */

public class Notification extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.notification);
        CharSequence s = "전달 받은 값은 ";
        int id=0;

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            s = "error";
        }
        else {
            id = extras.getInt("notificationId");
        }
        TextView t = (TextView) findViewById(R.id.tv_notification);
        s = s+" "+id;
        t.setText(s);
        NotificationManager nm =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //노티피케이션 제거
        nm.cancel(id);
    }
}
