package com.dijoux.marc.todolist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTaskActivity extends Activity implements View.OnClickListener{

    private EditText mTitleText;
    private EditText mPriorityText;
    private Long mRowId;
    private TaskDbAdapter mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new TaskDbAdapter(this);
        mDbHelper.open();

        setContentView(R.layout.edit_task);
        setTitle(R.string.edit_task);

        mTitleText = (EditText) findViewById(R.id.task_name);
        mPriorityText = (EditText) findViewById(R.id.task_priority);

        mRowId = (savedInstanceState == null) ? null :
                (Long) savedInstanceState.getSerializable(TaskDbAdapter.KEY_ROWID);
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(TaskDbAdapter.KEY_ROWID)
                    : null;
        }

        populateFields();
    }

    private void populateFields() {
        if (mRowId != null) {
            Cursor task = mDbHelper.fetchTask(mRowId);
            startManagingCursor(task);
            setTaskName(task.getString(task.getColumnIndex(TaskDbAdapter.KEY_NAME)));
            setTaskPriority(task.getInt(task.getColumnIndex(TaskDbAdapter.KEY_PRIO)));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                saveState();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(TaskDbAdapter.KEY_ROWID, mRowId);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.button_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }

    private void saveState() {
        String name = getTaskName();
        int priority = getTaskPriority();

        if (mRowId == null) {
            long id = mDbHelper.createTask(name, priority);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updateTask(mRowId, name, priority);
        }
    }

    private void setTaskName(String name){
        mTitleText.setText(name);
    }

    private void setTaskPriority(int priority){
        if(priority >= 0 && priority <=4){
            this.mPriorityText.setText((new Integer(priority)).toString());
        }
    }

    private String getTaskName(){
        return this.mTitleText.getText().toString();
    }

    private int getTaskPriority(){
        String p = this.mPriorityText.getText().toString();
        return Integer.parseInt(p);
    }

}
