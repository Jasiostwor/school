package com.example.createbitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int shape = 0;
    private boolean isSelected = false;
    private Button rub, czysc, linie, elipse, kwadrat;
    private ImageView imageView;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        rub = findViewById(R.id.button1);
        czysc = findViewById(R.id.button2);
        linie = findViewById(R.id.button3);
        elipse = findViewById(R.id.button4);
        kwadrat = findViewById(R.id.button5);

        random = new Random();
        bitmap = Bitmap.createBitmap(800,1200, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setStrokeWidth(5);

        imageView.setImageBitmap(bitmap);

        rub.setOnClickListener(v -> {
            enableButtons(true);
            rysuj();
            shape = 0;
            isSelected = false;
        });

        czysc.setOnClickListener(v -> {
            clear();
            enableButtons(true);
            isSelected = false;
        });

        linie.setOnClickListener(v -> {
            if (!isSelected || shape == 3){
                shape = 3;
                isSelected = true;

                enableButtons(false);
            }
        });

        elipse.setOnClickListener(v -> {
            if (!isSelected || shape == 4){
                shape = 4;
                isSelected = true;

                enableButtons(false);
            }
        });

        kwadrat.setOnClickListener(v -> {
            if (!isSelected || shape == 5){
                shape = 5;
                isSelected = true;

                enableButtons(false);
            }
        });
    }

    private void enableButtons(boolean state){
        if (shape != 3){
            linie.setEnabled(state);
        }
        if (shape != 4){
            elipse.setEnabled(state);
        }
        if (shape != 5){
            kwadrat.setEnabled(state);
        }

    }

    private int getColor(){
        return Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
    }

    private void rysuj(){

        bitmap = Bitmap.createBitmap(800,1200, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        imageView.setImageBitmap(bitmap);

        for (int i=0;i<10;i++){
            int startX = random.nextInt(bitmap.getWidth());
            int startY = random.nextInt(bitmap.getHeight());
            int endX = random.nextInt(bitmap.getWidth());
            int endY = random.nextInt(bitmap.getHeight());

            paint.setColor(getColor());

            switch (shape){
                case 3:
                    canvas.drawLine(startX,startY,endX,endY,paint);
                    break;
                case 4:
                    canvas.drawOval(new RectF(startX,startY,endX,endY), paint);
                    break;

                case 5:
                    int side = Math.min(Math.abs(endX - startX), Math.abs(endY - startY));
                    canvas.drawRect(startX, startY,startX + side, startY +side, paint);
                    break;
            }
        }

    }

    private void clear(){
        canvas.drawColor(Color.WHITE);
        imageView.invalidate();
    }
}