package com.example.todoapp;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    ArrayList<ToDo> listOfTodos = new ArrayList<>(0);
    FileManager fileManager = new FileManager();

}
