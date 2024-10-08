package com.example.asynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RecordListActivity extends AppCompatActivity {

    private ListView listViewRecords;
    private DatabaseHelper dbHelper;
    private List<Item> recordList;
    private ArrayAdapter<String> adapter;
    private List<String> recordNames;

    // Dodane pola do obsługi odświeżania
    private Handler handler = new Handler();
    private Runnable runnable;
    private LoadRecordsTask loadRecordsTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_list_activity);

        listViewRecords = findViewById(R.id.list_item);
        dbHelper = new DatabaseHelper(this);

        recordList = new ArrayList<>();
        recordNames = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recordNames);
        listViewRecords.setAdapter(adapter);

        listViewRecords.setOnItemClickListener((parent, view, position, id) -> {
            Item selectedRecord = recordList.get(position);
            Intent intent = new Intent(RecordListActivity.this, EditRecordActivity.class);
            intent.putExtra("record_id", selectedRecord.getId());
            startActivity(intent);
        });

        startRepeatingTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    private void startRepeatingTask() {
        runnable = new Runnable() {
            @Override
            public void run() {
                loadRecordsTask = new LoadRecordsTask();
                loadRecordsTask.execute();


                handler.postDelayed(this, 5000);
            }
        };
        handler.post(runnable);
    }

    private void stopRepeatingTask() {
        handler.removeCallbacks(runnable);
        if (loadRecordsTask != null && !loadRecordsTask.isCancelled()) {
            loadRecordsTask.cancel(true);
        }
    }

    // AsyncTask do ładowania danych z bazy w tle
    private class LoadRecordsTask extends AsyncTask<Void, Void, List<Item>> {

        @Override
        protected List<Item> doInBackground(Void... voids) {
            // Sprawdzenie, czy zadanie nie zostało anulowane
            if (isCancelled()) {
                return null;
            }
            // Pobranie wszystkich rekordów z bazy danych
            return dbHelper.getAllItems();
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            if (items != null) {
                recordList.clear();
                recordNames.clear();
                recordList.addAll(items);

                for (Item item : recordList) {
                    recordNames.add(item.getName() + "    " + item.getDescription() + "   " + item.getPrice());
                }

                adapter.notifyDataSetChanged();
            }
        }
    }
}
