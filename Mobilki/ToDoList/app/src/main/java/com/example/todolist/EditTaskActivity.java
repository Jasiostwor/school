package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription;
    private Button buttonUpdate;

    private Task task;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        buttonUpdate = findViewById(R.id.button_update);

        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task");
        position = intent.getIntExtra("position", -1);

        if (task != null) {
            editTextTitle.setText(task.getTitle());
            editTextDescription.setText(task.getDescription());
        }

        buttonUpdate.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();

            if (!title.isEmpty()) {
                task.setTitle(title);
                task.setDescription(description);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("task", task);
                resultIntent.putExtra("position", position);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "Tytuł nie może być pusty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
