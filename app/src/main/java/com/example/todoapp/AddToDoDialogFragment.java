package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;

public class AddToDoDialogFragment extends DialogFragment {

    interface saveButtonClickListener {
        void saveNewTodo(ToDo newtodo);
    }
Button savebutton;
DatePicker taskDatePicker;
SwitchCompat isUrgent;
EditText taskText;
    saveButtonClickListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View v =  inflater.inflate(R.layout.activity_add_to_do,container,false);

        taskText = v.findViewById(R.id.taskText);
        isUrgent = v.findViewById(R.id.isUrgentSwitch);
        taskDatePicker = v.findViewById(R.id.taskDatePicker);
        savebutton = v.findViewById(R.id.saveButton);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!taskText.getText().toString().isEmpty()){
                    String taskDate = taskDatePicker.getYear()+"/"+taskDatePicker.getMonth()+"/"+taskDatePicker.getDayOfMonth();
                    ToDo newTodo = new ToDo(taskText.getText().toString(),taskDate,isUrgent.isChecked(),false);
                    listener.saveNewTodo(newTodo);
                    dismiss();
                }
            }
        });
      return v;
    }
}
