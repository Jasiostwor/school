package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private FloatingActionButton fabAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_tasks);
        fabAddTask = findViewById(R.id.fab_add_task);

        taskList = new ArrayList<>();

        taskAdapter = new TaskAdapter(taskList, (task, position) -> {
            Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
            intent.putExtra("task", task);
            intent.putExtra("position", position);
            startActivityForResult(intent, 3);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Task newTask = (Task) data.getSerializableExtra("task");
            taskList.add(newTask);
            taskAdapter.notifyItemInserted(taskList.size() - 1);
        } else if (requestCode == 3 && resultCode == RESULT_OK && data != null) {
            Task updatedTask = (Task) data.getSerializableExtra("task");
            int position = data.getIntExtra("position", -1);

            if (position != -1) {
                taskList.set(position, updatedTask);
                taskAdapter.notifyItemChanged(position);
            }
        }
    }
}
