package com.example.asynctask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRecordActivity extends AppCompatActivity {

    private EditText editTextName, editTextDesc, editTextPrice;
    private Button buttonSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_record_activity);

        dbHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.edittext_name);
        editTextDesc = findViewById(R.id.edittext_desc);
        editTextPrice = findViewById(R.id.edittext_price);
        buttonSave = findViewById(R.id.button_save);

        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String desc = editTextDesc.getText().toString();
            String priceString = editTextPrice.getText().toString();

            // Walidacja danych
            if (name.isEmpty() || desc.isEmpty() || priceString.isEmpty()) {
                Toast.makeText(this, "Wszystkie pola są wymagane", Toast.LENGTH_SHORT).show();
                return;
            }

            // Dodanie rekordu do bazy danych
            dbHelper.addItem(new Item(1,name,Double.parseDouble(priceString), desc));
            Toast.makeText(this, "Rekord dodany", Toast.LENGTH_SHORT).show();
            finish(); // Powrót do poprzedniego widoku
        });
    }
}
