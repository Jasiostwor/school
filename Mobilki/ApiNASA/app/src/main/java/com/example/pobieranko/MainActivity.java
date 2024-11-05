package com.example.pobieranko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private NasaApiService nasaApiService;
    private String apiKey = "HBYXbI925gEJhSeW5KzOgtSwwG2XGo61frRSGBfv";

    private Button btnPrevious, btnNext;
    private ImageView imageView;
    private TextView tvTitle, tvExplanation, tvDaysAgo;

    private Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        imageView = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tvTitle);
        tvExplanation = findViewById(R.id.tvExplanation);
        tvDaysAgo = findViewById(R.id.tvDaysAgo);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nasaApiService = retrofit.create(NasaApiService.class);

        currentDate = new Date();

        fetchData(getFormattedDate(currentDate));

        btnPrevious.setOnClickListener(v -> {
            changeDateBy(-1);
        });

        btnNext.setOnClickListener(v -> {
            changeDateBy(1);
        });
    }

    private void changeDateBy(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);

        Date newDate = calendar.getTime();

        if (newDate.after(new Date())) {
            tvTitle.setText("Brak danych dla przyszłych dat");
        } else {
            currentDate = newDate;
            fetchData(getFormattedDate(currentDate));
        }
    }

    private String getFormattedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(date);
    }

    private void fetchData(String date) {
        Call<NasaPhoto> call = nasaApiService.getApod(apiKey, date);

        call.enqueue(new Callback<NasaPhoto>() {
            @Override
            public void onResponse(Call<NasaPhoto> call, Response<NasaPhoto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NasaPhoto photo = response.body();

                    tvTitle.setText(photo.getTitle());
                    tvExplanation.setText(photo.getExplanation());

                    String imageUrl = photo.getUrl();
                    String mediaType = photo.getMediaType();

                    if ("image".equals(mediaType)) {
                        Picasso.get().load(imageUrl).into(imageView);
                    } else {
                        imageView.setImageResource(R.drawable.ic_launcher_background);
                        tvTitle.setText("Brak zdjęcia dla tej daty.");
                    }

                    calculateDaysAgo(photo.getDate());
                } else {
                    tvTitle.setText("Błąd: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<NasaPhoto> call, Throwable t) {
                tvTitle.setText("Błąd połączenia: " + t.getMessage());
            }
        });
    }

    private void calculateDaysAgo(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date photoDate = sdf.parse(dateString);
            Date today = new Date();

            long diff = today.getTime() - photoDate.getTime();
            long days = diff / (24 * 60 * 60 * 1000);

            tvDaysAgo.setText("Opublikowano " + days + " dni temu");
        } catch (Exception e) {
            tvDaysAgo.setText("Nie można obliczyć liczby dni");
        }
    }
}
