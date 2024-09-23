package com.example.blur;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bitmap;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView2);
        btn =  findViewById(R.id.button);

        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap().copy(Bitmap.Config.ARGB_8888, true);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int PixelsToMove = 10000;
                int Offset = 5;

                Random random = new Random();

                int width = bitmap.getWidth();
                int height = bitmap.getHeight();

                for (int i =0; i < PixelsToMove; i++){
                    int x1 = random.nextInt(width);
                    int y1 = random.nextInt(height);

                    int dx = random.nextInt(Offset * 2 + 1) - Offset;
                    int dy = random.nextInt(Offset * 2 + 1) - Offset;

                    int x2 = Math.min(Math.max(x1+dx,0), width-1);
                    int y2 = Math.min(Math.max(y1+dy,0), height-1);

                    int pixel1 = bitmap.getPixel(x1,y1);
                    int pixel2 = bitmap.getPixel(x2,y2);

                    bitmap.setPixel(x1,y1,pixel2);
                    bitmap.setPixel(x2,y2,pixel1);
                }

                imageView.setImageBitmap(bitmap);
            }
        });
    }

}