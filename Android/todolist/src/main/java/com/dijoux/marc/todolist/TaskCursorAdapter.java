package com.dijoux.marc.todolist;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Cram on 19/05/2014.
 */
public class TaskCursorAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TaskCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleView = (TextView)view.findViewById(R.id.title);
        TextView subtitleView = (TextView)view.findViewById(R.id.subtitle);
        ImageView imageView = (ImageView)view.findViewById(R.id.image);

        String name = cursor.getString(cursor.getColumnIndex(TaskDbAdapter.KEY_NAME));
        int priority = cursor.getInt(cursor.getColumnIndex(TaskDbAdapter.KEY_PRIO));

        titleView.setText(name);
        subtitleView.setText("priority: " + priority);
        int iconId = R.drawable.prio0;
        switch (priority){
            case 1:
                iconId = R.drawable.prio1;
                break;
            case 2:
                iconId = R.drawable.prio2;
                break;
            case 3:
                iconId = R.drawable.prio3;
                break;
            case 4:
                iconId = R.drawable.prio4;
                break;
        }
        imageView.setImageResource(iconId);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mInflater.inflate(R.layout.task_cell, parent, false);
    }
}
