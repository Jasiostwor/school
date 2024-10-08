package com.example.asynctask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditRecordActivity extends AppCompatActivity {

    private EditText editTextName, editTextDesc, editTextPrice;
    private Button buttonSave, buttonDelete;
    private DatabaseHelper dbHelper;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_record_activity);

        dbHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.edittext_name1);
        editTextDesc = findViewById(R.id.edittext_desc1);
        editTextPrice = findViewById(R.id.edittext_price1);
        buttonSave = findViewById(R.id.button_save1);
        buttonDelete = findViewById(R.id.button_delete);

        // Pobranie ID rekordu przekazanego w Intent
        int recordId = getIntent().getIntExtra("record_id", -1);
        if (recordId != -1) {
            // Pobranie rekordu z bazy danych
            item = dbHelper.getItemById(recordId);
            if (item != null) {
                editTextName.setText(item.getName());
                editTextDesc.setText(item.getDescription());
                editTextPrice.setText(String.valueOf(item.getPrice()));
            }
        }

        // Aktualizacja rekordu
        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String desc = editTextDesc.getText().toString();
            String price = editTextPrice.getText().toString();

            if (name.isEmpty() || desc.isEmpty() || price.isEmpty()) {
                Toast.makeText(this, "Wszystkie pola są wymagane", Toast.LENGTH_SHORT).show();
                return;
            }


            dbHelper.updateItem(item.getId(),new Item(item.getId(), name,Double.parseDouble(price), desc));
            Toast.makeText(this, "Rekord zaktualizowany", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Usuwanie rekordu
        buttonDelete.setOnClickListener(v -> {
            dbHelper.deleteItem(item.getId());
            Toast.makeText(this, "Rekord usunięty", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
