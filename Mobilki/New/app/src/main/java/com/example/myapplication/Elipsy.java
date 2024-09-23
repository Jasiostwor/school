package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class Elipsy extends View {
    public Elipsy(Context context) {
        super(context);
    }
    public Elipsy(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Elipsy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){

        int szer = getWidth();
        int szer2 = szer / 2;
        int wys = getHeight();
        int rozmiar = (szer2 < wys ? szer2 : wys) - 10;
        int x, y, dx, dy;
        Paint p = new Paint();
        p.setColor(Color.GRAY);
        canvas.drawRect(0,0,szer-1,wys-1, p);

        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        Random r = new Random();



        for (int i =0; i<10;i++) {
            dx = r.nextInt(rozmiar);
            dy = r.nextInt(rozmiar);
            x = r.nextInt(szer2 - dx);
            y = r.nextInt(wys - dy);
            p.setARGB(255, r.nextInt(256),r.nextInt(256), r.nextInt(256));
            RectF rect = new RectF(x, y, x + dx, y + dy);
            canvas.drawOval(rect, p);
        }

        p.setTextSize(50);
        p.setTextAlign(Paint.Align.RIGHT);
        p.setColor(Color.BLACK);
        canvas.drawText("Elipsa", szer-20, wys/2, p);

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        p.setColor(Color.YELLOW);
        canvas.drawRect(2,2,szer-3,wys-3,p);
        super.onDraw(canvas);
    }
}
