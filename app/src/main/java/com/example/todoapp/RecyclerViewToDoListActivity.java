package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerViewToDoListActivity
        extends AppCompatActivity
        implements ToDoRecyclerAdapter.ToDoClickListener // step 3
{
    RecyclerView todoRecyclerView;
    ArrayList<ToDo> todoData;
    ToDoRecyclerAdapter adapter;
    FileManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_to_do_list);
        todoData =  ((MyApp)getApplication()).listOfTodos; // data
        fm = ((MyApp)getApplication()).fileManager;
        todoRecyclerView = findViewById(R.id.recyclerlist);
        adapter = new ToDoRecyclerAdapter(todoData,this);
        adapter.listener = this; // step 4
        todoRecyclerView.setAdapter(adapter);

        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                todoData.remove(position);
                fm.updateToDoFile(RecyclerViewToDoListActivity.this, todoData);
                adapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(todoRecyclerView);
    }

    @Override
    public void onToDoClicked(int i) { // step 6
        // here we will get the notification of a todo clicking
        Toast.makeText(this,todoData.get(i).getTask(),Toast.LENGTH_LONG).show();
        // start another activity
        // update one todo
        // update the table

    }

    @Override
    public void onDoneClicked(int i) {
        todoData.get(i).setDone(true);
        fm.updateToDoFile(this,todoData );
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSwtichClicked(int i) {
        todoData.get(i).setUrgent(!todoData.get(i).isUrgent());
        fm.updateToDoFile(this,todoData );
        adapter.notifyDataSetChanged();

    }
}