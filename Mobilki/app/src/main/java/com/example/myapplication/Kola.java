package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class Kola extends View {
    public Kola(Context context) {
        super(context);
    }
    public Kola(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Kola(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){

        int szer = getWidth(), wys = getHeight();
        Paint p = new Paint();
        Random r = new Random();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);

        for (int i =0; i<10;i++){
            p.setARGB(255, r.nextInt(256),r.nextInt(256), r.nextInt(256));
            canvas.drawCircle(r.nextInt(szer), r.nextInt(wys), r.nextInt(100),p);
        }

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        p.setColor(Color.YELLOW);
        canvas.drawRect(2,2,szer-3,wys-3,p);


        super.onDraw(canvas);
    }
}
