package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoBaseAdapter extends BaseAdapter {

    ArrayList<ToDo> todoListlist;
    Context context;

    public ToDoBaseAdapter(ArrayList<ToDo> list, Context context) {
        this.todoListlist = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return todoListlist.size();
    }

    @Override
    public Object getItem(int i) {
        return todoListlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // inflate xml file into a View in Java
       View v = LayoutInflater.from(context).inflate(R.layout.todo_row,viewGroup, false);
       TextView taskText = v.findViewById(R.id.todo_row_task);
       TextView dateText = v.findViewById(R.id.todo_row_date);

        taskText.setText(todoListlist.get(i).getTask());
        dateText.setText(todoListlist.get(i).getDate());

        if (todoListlist.get(i).isDone()){
            v.setBackgroundColor(context.getColor(R.color.white));
        }
        else{
            if (todoListlist.get(i).isUrgent()) {
                v.setBackgroundColor(context.getColor(R.color.red));
            } else {
                v.setBackgroundColor(context.getColor(R.color.green));
            }
        }

        return v;
    }
}
