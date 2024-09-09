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
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        Random r = new Random();

        dx = r.nextInt(rozmiar);
        dy = r.nextInt(rozmiar);
        x = r.nextInt(szer2 - dx);
        y = r.nextInt(wys - dy);
        RectF rect = new RectF(x, y, x + dx, y + dy);
        canvas.drawOval(rect, p);


        super.onDraw(canvas);
    }
}
