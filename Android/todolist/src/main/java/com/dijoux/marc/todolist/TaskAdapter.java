package com.dijoux.marc.todolist;

import android.content.Context;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class TaskAdapter extends ArrayAdapter<Task> {

    private final Context context;

    public TaskAdapter(Context context, Task[] values) {
        super(context, R.layout.task_cell, values);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView = convertView;
        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.task_cell, parent, false);
        }

        TextView titleView = (TextView)cellView.findViewById(R.id.title);
        TextView subtitleView = (TextView)cellView.findViewById(R.id.subtitle);
        ImageView imageView = (ImageView)cellView.findViewById(R.id.image);

        Task t = getItem(position);
        titleView.setText(t.name);
        subtitleView.setText("priority: " + t.priority);
        int iconId = R.drawable.prio0;
        switch (t.priority){
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
        return cellView;
    }
}
