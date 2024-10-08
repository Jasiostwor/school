package com.example.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button buttonAddRecord, buttonViewRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddRecord = findViewById(R.id.button_add_record);
        buttonViewRecords = findViewById(R.id.button_view_records);

        buttonAddRecord.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
            startActivity(intent);
        });

        buttonViewRecords.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecordListActivity.class);
            startActivity(intent);
        });
    }
}