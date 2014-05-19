package com.dijoux.marc.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Cram on 19/05/2014.
 */
public class AddTaskActivity extends Activity implements View.OnClickListener {
    private EditText title;
    private EditText priority;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        this.title = (EditText)findViewById(R.id.task_name);
        this.priority = (EditText)findViewById(R.id.task_priority);
        //this.priority.setFilters(new InputFilter[]{new PriorityFilter()});

        Task t = (Task)this.getIntent().getSerializableExtra("Task");
        if(t == null )
            t = new Task();
        this.setTask(t);

    }

    private void setTaskName(String name){
        title.setText(name);
    }

    private void setTaskPriority(int priority){
        if(priority >= 0 && priority <=4){
            this.priority.setText((new Integer(priority)).toString());
        }
    }

    private String getTaskName(){
        return this.title.getText().toString();
    }

    private int getTaskPriority(){
        String p = this.priority.getText().toString();
        return Integer.parseInt(p);
    }

    private void setTask(Task t){
        this.setTaskName(t.getName());
        this.setTaskPriority(t.getPriority());
    }

    private Task getTask(){
        String name = this.getTaskName();
        int priority = this.getTaskPriority();
        return new Task(name, priority);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Task", this.getTask());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.button_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
