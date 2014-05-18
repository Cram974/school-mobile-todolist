package com.dijoux.marc.todolist;

/**
 * Created by Cram on 18/05/2014.
 */
public class Category {
    int id;
    String name;

    public Category(){

    }

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  void setName(String name){
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
