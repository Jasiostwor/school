package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewDescription;
    private Button buttonEdit;

    private Task task;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        textViewTitle = findViewById(R.id.text_view_title);
        textViewDescription = findViewById(R.id.text_view_description);
        buttonEdit = findViewById(R.id.button_edit);

        // Odbierz dane z Intent
        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task");
        position = intent.getIntExtra("position", -1);

        if (task != null) {
            textViewTitle.setText(task.getTitle());
            textViewDescription.setText(task.getDescription());
        }

        buttonEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(TaskDetailActivity.this, EditTaskActivity.class);
            editIntent.putExtra("task", task);
            editIntent.putExtra("position", position);
            startActivityForResult(editIntent, 2);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            task = (Task) data.getSerializableExtra("task");
            textViewTitle.setText(task.getTitle());
            textViewDescription.setText(task.getDescription());
            Intent resultIntent = new Intent();
            resultIntent.putExtra("task", task);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
        }
    }
}
