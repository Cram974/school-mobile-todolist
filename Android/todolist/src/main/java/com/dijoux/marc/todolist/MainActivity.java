package com.dijoux.marc.todolist;


import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;

    private static final int EDIT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;

    private TaskDbAdapter mDbHelper;
    private TaskCursorAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDbHelper = new TaskDbAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
    }

    private void fillData() {
        Cursor tasksCursor = mDbHelper.fetchAllTasks();
        startManagingCursor(tasksCursor);

        mAdapter = new TaskCursorAdapter(this, tasksCursor,0);
        setListAdapter(mAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, EDIT_ID, Menu.NONE, R.string.edit);
        menu.add(0, DELETE_ID, Menu.NONE, R.string.delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()) {
            //Edit
            case EDIT_ID:
                Intent intent = new Intent(this, EditTaskActivity.class);
                intent.putExtra(TaskDbAdapter.KEY_ROWID, getItemId(menuInfo.position));
                startActivityForResult(intent, ACTIVITY_EDIT);
                return true;
            //Delete
            case DELETE_ID:
                //Create Confirmation Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.delete);
                builder.setMessage(R.string.are_you_sure);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        removeItem(menuInfo.position);
                        fillData();
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
        super.onActivityResult(requestCode, resultCode, data);
        fillData();
    }

    protected void removeItem(int position){
        mDbHelper.deleteTask(getItemId(position));
    }

    protected long getItemId(int position){
        Cursor c = (Cursor)mAdapter.getItem(position);
        return c.getLong(c.getColumnIndex(TaskDbAdapter.KEY_ROWID));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                Intent intent = new Intent(this, EditTaskActivity.class);
                startActivityForResult(intent, ACTIVITY_CREATE);
                break;
            case R.id.button_quit:
                finish();
                break;
        }
    }
}
