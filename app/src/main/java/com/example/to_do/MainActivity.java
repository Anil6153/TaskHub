package com.example.to_do;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView tasksRecyclerView;
    TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        //// retrive data from database

        List<Task> tasksList;
        databaseHelper = new DatabaseHelper(this);
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksList = databaseHelper.getAllTasks();
        taskAdapter = new TaskAdapter(this,tasksList);


        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(taskAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}