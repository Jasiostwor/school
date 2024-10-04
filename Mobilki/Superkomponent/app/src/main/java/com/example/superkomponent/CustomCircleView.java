package com.example.superkomponent;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class CustomCircleView  extends View {

    private Paint paint;
    private float radius = 100f;
    private int circleColor = Color.RED;
    private float xPos = 200f;
    private float yPos = 200f;

    public CustomCircleView(Context context){
        super(context);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawCircle(xPos, yPos, setCircleRadius(radius), paint);
    }

    public void setCircleColor(int color){
        circleColor = color;
        paint.setColor(circleColor);
        invalidate();
    }

    public float setCircleRadius(float newRadius){
        radius = newRadius;
        invalidate();
        return radius;
    }

    public void animateCircle(float startX, float startY, float endX, float endY, long duration){
        ValueAnimator animatorX = ValueAnimator.ofFloat(startX,endX);
        animatorX.setDuration(duration);
        animatorX.addUpdateListener(animation -> {
            xPos = (float) animation.getAnimatedValue();
            invalidate();
        });

        ValueAnimator animatorY = ValueAnimator.ofFloat(startY,endY);
        animatorY.setDuration(duration);
        animatorY.addUpdateListener(animation -> {
            yPos = (float) animation.getAnimatedValue();
            invalidate();
        });

        animatorX.start();
        animatorY.start();
    }

    public boolean onTouchEvent(MotionEvent event){
        float targetX = event.getX();
        float targetY = event.getY();

        animateCircle(xPos, yPos, targetX, targetY, 500);

        Random random = new Random();
        int randomColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        setCircleColor(randomColor);

        int randomRadius = random.nextInt(500) + 1;
        setCircleRadius(randomRadius);

        return true;
    }
}
