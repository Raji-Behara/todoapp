package com.example.todoapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileManager {

    String fileName = "ToDoFile.txt";
    void writeToDoToFile(Context context, ToDo newTodo){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            fos.write(newTodo.toString().getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // byte level array - stream of characters
        // MODE_APPEND = continue writing
        // MODE.PRIVATE = overwright

        //openFileOutput 1- the file is not exist ==> create a new file and open it
        //              2- the file is exist == > open it.

        // I have to write a byte array only
        // we convert the todo to a byte array
    }

    void deleteAllToDos(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write("".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateToDoFile(Context context,ArrayList<ToDo> updatedTodoList){
        // delete all content
        // rewrite them with the updated todo
        deleteAllToDos(context);
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            for (int i = 0 ; i< updatedTodoList.size(); i++) {
                fos.write(updatedTodoList.get(i).toString().getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<ToDo> getAllToDos(Context context) {
        ArrayList<ToDo> list = new ArrayList<>(0);
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();

                while (line != null) {
                    list.add(new ToDo(line));
                    line = reader.readLine();
                }
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return list;
        }

    }
