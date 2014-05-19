package com.dijoux.marc.todolist;


import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import android.app.AlertDialog;
/**
 * Created by Cram on 18/05/2014.
 */
public class MainActivity extends ListActivity implements View.OnClickListener {

    private static final int EDIT_ACTIVITY = 1;
    private static final int ADD_ACTIVITY = 2;
    private int lastPosition = -1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerForContextMenu(getListView());

        ArrayList<Task> tasks = new ArrayList<Task>();

        tasks.add(new Task("Manger de la soupe", 3));

        this.setListAdapter(new TaskListAdapter(this, tasks));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, Menu.FIRST, Menu.NONE, R.string.edit);
        menu.add(0, Menu.FIRST+1, Menu.NONE, R.string.delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        TaskListAdapter adapter = (TaskListAdapter)this.getListAdapter();
        lastPosition = menuInfo.position;

        switch(item.getItemId()) {
            //Edit
            case Menu.FIRST:
                Intent intent = new Intent(this, AddTaskActivity.class);
                intent.putExtra("Task", adapter.getItem(menuInfo.position));
                startActivityForResult(intent, EDIT_ACTIVITY);
                return true;
            //Delete
            case Menu.FIRST+1:
                //Create Confirmation Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.delete);
                builder.setMessage(R.string.are_you_sure);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        removeItem(menuInfo.position);
                    }
                });
                builder.setNegativeButton(R.string.cancel, null);
                builder.create().show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        TaskListAdapter adapter = (TaskListAdapter)this.getListAdapter();
        switch(requestCode) {
            case EDIT_ACTIVITY:
                switch (resultCode){
                    case RESULT_OK:
                        Task nt = (Task)data.getSerializableExtra("Task");
                        if(nt != null && lastPosition > -1){
                            Task old = adapter.getItem(lastPosition);
                            old.setName(nt.getName());
                            old.setPriority(nt.getPriority());
                            adapter.notifyDataSetChanged();
                        }
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
            case ADD_ACTIVITY:
                switch (resultCode){
                    case RESULT_OK:
                        Task nt = (Task)data.getSerializableExtra("Task");
                        if(nt != null) {
                            adapter.add(nt);
                            adapter.notifyDataSetChanged();
                        }
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
        }
    }

    protected void removeItem(int position){
        TaskListAdapter adapter = (TaskListAdapter)this.getListAdapter();
        adapter.remove(adapter.getItem(position));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                Intent intent = new Intent(this, AddTaskActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY);
                break;
            case R.id.button_quit:
                finish();
                break;
        }
    }
}
