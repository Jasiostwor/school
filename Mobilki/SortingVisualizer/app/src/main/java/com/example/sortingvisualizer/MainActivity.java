package com.example.sortingvisualizer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText etElementCount;
    private Button btnStartSort;
    private LinearLayout sortingVisualization;
    private TextView tvStatus;

    private int[] arrayToSort;
    private int elementCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElementCount = findViewById(R.id.et_element_count);
        btnStartSort = findViewById(R.id.btn_start_sort);
        sortingVisualization = findViewById(R.id.sorting_visualization);
        tvStatus = findViewById(R.id.tv_status);

        btnStartSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etElementCount.getText().toString();
                if (!input.isEmpty()) {
                    elementCount = Integer.parseInt(input);
                    arrayToSort = generateRandomArray(elementCount);
                    new SortTask().execute();
                }
            }
        });
    }


    private int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        return array;
    }


    private void updateVisualization() {
        sortingVisualization.removeAllViews();
        for (int value : arrayToSort) {
            View bar = new View(this);
            bar.setLayoutParams(new LinearLayout.LayoutParams(0, value * 5, 1));
            bar.setBackgroundColor(getResources().getColor(R.color.black));
            sortingVisualization.addView(bar);
        }
    }


    private class SortTask extends AsyncTask<Void, int[], Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvStatus.setText("Rozpopczęto sortowanie...");
            updateVisualization();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i = 0; i < arrayToSort.length - 1; i++) {
                double status = (double) i/(double)arrayToSort.length;
                tvStatus.setText((int)(status * 100)+"%");

                for (int j = 0; j < arrayToSort.length - i - 1; j++) {
                    if (arrayToSort[j] > arrayToSort[j + 1]) {

                        int temp = arrayToSort[j];
                        arrayToSort[j] = arrayToSort[j + 1];
                        arrayToSort[j + 1] = temp;


                        publishProgress(arrayToSort);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(int[]... values) {
            super.onProgressUpdate(values);
            updateVisualization();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvStatus.setText("Sortowanie ukonczone!");
        }
    }
}