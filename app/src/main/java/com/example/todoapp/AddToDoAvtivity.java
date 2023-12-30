package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddToDoAvtivity extends AppCompatActivity {

    EditText taskText;
    SwitchCompat isUrgent;
    DatePicker taskDatePicker;
    Button savebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);


        taskText = findViewById(R.id.taskText);
        isUrgent = findViewById(R.id.isUrgentSwitch);
        taskDatePicker = findViewById(R.id.taskDatePicker);
        savebutton = findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!taskText.getText().toString().isEmpty()){
                    String taskDate = taskDatePicker.getYear()+"/"+taskDatePicker.getMonth()+"/"+taskDatePicker.getDayOfMonth();
                    ToDo newTodo = new ToDo(taskText.getText().toString(),taskDate,isUrgent.isChecked(),false);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newtodo",newTodo);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });


    }
}