package com.dijoux.marc.todolist;

/**
 * Created by Cram on 18/05/2014.
 */
public class Task {
    int id;
    String name;
    int priority;
    String category;

    public Task(){
    }

    public Task(int id, String name, int priority, String category){
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.category = category;
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

    public void setCategory(String category){
        this.category = category;
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

    public String getCategory(){
        return this.category;
    }
}
