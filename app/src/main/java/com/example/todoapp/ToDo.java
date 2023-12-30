package com.example.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity

public class ToDo implements Serializable { // MVC
    @PrimaryKey

    private  String task;
    private  String date;
    private  boolean isUrgent;
    private boolean isDone;

    @Override
    public String toString() {
        // Fix the door-30/11/2023-0-0
        // cook the meal-24/11/2023-1-1
        return task+ "-" + date +"-"+ (isUrgent?"1":"0") + "-" + (isDone?"1":"0")+'\n';
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public ToDo(String task, String date, boolean isUrgent, boolean isDone) {
        this.task = task;
        this.date = date;
        this.isUrgent = isUrgent;
        this.isDone = isDone;
    }
    public ToDo(String lineFromFile){
        // Fix the door-30/11/2023-0-0\n
        String[] todoParts = lineFromFile.split("-");
        this.task = todoParts[0];
        this.date = todoParts[1];
        this.isUrgent = !todoParts[2].equals("0");
        this.isDone = !todoParts[3].equals("0");

    }

    // get a string a return a ToDo Object
    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }

    public boolean isUrgent() {
        return isUrgent;
    }
}
