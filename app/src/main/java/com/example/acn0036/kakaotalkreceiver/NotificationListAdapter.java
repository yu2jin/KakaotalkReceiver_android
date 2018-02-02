package com.example.acn0036.kakaotalkreceiver;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ACN0036 on 2017-10-17.
 */

public class NotificationListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<NotificationData> mNotificationDataArrayList = new ArrayList<>();

    public NotificationListAdapter(Context context, ArrayList<NotificationData> notificationDataArrayList) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
        this.mNotificationDataArrayList = notificationDataArrayList;
    }

    @Override
    public int getCount() {
        return mNotificationDataArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.list_item, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView txtTitle = (TextView) view.findViewById(R.id.Itemname);
        TextView txtText = (TextView) view.findViewById(R.id.ItemText);
        TextView txtTime = (TextView) view.findViewById(R.id.ItemTime);

        NotificationData notificationData = mNotificationDataArrayList.get(position);

        if (notificationData != null && notificationData.getImage() != null)
            imageView.setImageBitmap(notificationData.getImage());

        txtTitle.setText(notificationData.getName());
        txtText.setText(notificationData.getText());
        txtTime.setText(notificationData.getTime());

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mNotificationDataArrayList.get(position);
    }

}
