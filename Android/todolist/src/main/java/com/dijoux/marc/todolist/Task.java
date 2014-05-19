package com.dijoux.marc.todolist;

import java.io.Serializable;

/**
 * Created by Cram on 18/05/2014.
 */
public class Task implements Serializable {
    String name;
    int priority;

    public Task(){
        this.name = "default";
        this.priority = 0;
    }

    public Task(String name, int priority){
        this.name = name;
        this.priority = priority;
    }

    public  void setName(String name){
        this.name = name;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public String getName(){
        return this.name;
    }

    public int getPriority(){
        return this.priority;
    }
}
