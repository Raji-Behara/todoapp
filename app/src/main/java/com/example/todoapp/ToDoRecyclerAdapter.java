package com.example.todoapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoRecyclerAdapter extends
        RecyclerView.Adapter<ToDoRecyclerAdapter.ToDoViewHolder>
{

    interface ToDoClickListener{ // step 1
        void onToDoClicked(int i);
        void onDoneClicked(int i);
        void onSwtichClicked(int i);
    }

    ArrayList<ToDo> todo_list;
    Context context;
    ToDoClickListener listener;// step 2

    public ToDoRecyclerAdapter(ArrayList<ToDo> todo_list, Context context) {
        this.todo_list = todo_list;
        this.context = context;
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder {
                public ToDoViewHolder(@NonNull View itemView) {
                    super(itemView);
                 }

           }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.todo_row_update,parent,false);
        return  new ToDoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
       TextView todo_date = holder.itemView.findViewById(R.id.todo_row_date_update);
        TextView todo_task = holder.itemView.findViewById(R.id.todo_row_task_update);

        ImageButton doneBut = holder.itemView.findViewById(R.id.doneButton);
        SwitchCompat urgentSwitch = holder.itemView.findViewById(R.id.updateswitch);

        doneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // notify the activity after this click
                listener.onDoneClicked(holder.getAdapterPosition());
            }
        });

        todo_date.setText(todo_list.get(position).getDate());
        todo_task.setText(todo_list.get(position).getTask());

        if (todo_list.get(position).isDone()){
            holder.itemView.setBackgroundColor(context.getColor(R.color.white));
            doneBut.setVisibility(View.INVISIBLE);
            urgentSwitch.setVisibility(View.INVISIBLE);

        }else {
            if (todo_list.get(position).isUrgent())
                holder.itemView.setBackgroundColor(context.getColor(R.color.lightred));
            else
                holder.itemView.setBackgroundColor(context.getColor(R.color.lightgreen));
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onToDoClicked(holder.getAdapterPosition()); // step 5
            }
        });


        urgentSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                listener.onSwtichClicked(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return todo_list.size();
    }





}




