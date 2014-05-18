package com.dijoux.marc.todolist;

/**
 * Created by Cram on 18/05/2014.
 */
public class Task {
    int id;
    String name;
    int priority;

    public Task(){
    }

    public Task(int id, String name, int priority){
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    public void setId(int id){
        this.id = id;
    }

    public  void setName(String name){
        this.name = name;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getPriority(){
        return this.priority;
    }
}
