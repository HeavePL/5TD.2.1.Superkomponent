package com.example.superkomponenty;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class CustomCircleView extends View {


    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setCenterX((float) getWidth()-50);
        setCenterY((float) getHeight()-50);
        setRadius((float) 100);

    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
        invalidate();
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
        invalidate();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public View getCircleView() {
        circleView = findViewById(R.id.customCircleView);
        return circleView;
    }


    public int[] getRandomRGB(){
        Random random = new Random();
        int randomRGB[] = new int[3];
        randomRGB[0] = random.nextInt(255);
        randomRGB[1] = random.nextInt(255);
        randomRGB[2] = random.nextInt(255);
        return randomRGB;
    }
    private float centerX;
    private float centerY;
    private float radius;
    private View circleView;


    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int randomRGB[] = getRandomRGB();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(255,randomRGB[0],randomRGB[1],randomRGB[2]));
        paint.setAntiAlias(true);
        canvas.drawCircle(getCenterX(),getCenterY(),getRadius(),paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            Random random = new Random();
            float oldCX = getCenterX();
            setCenterX(random.nextInt((int) (getWidth()-getRadius())-30)+getRadius()+10);
            float oldCY = getCenterY();
            setCenterY(random.nextInt((int) (getHeight()-getRadius())-60)+getRadius()+10);
            float oldRadius = getRadius();
            setRadius(random.nextInt(100)+10);
            ObjectAnimator positionAnimatorX = ObjectAnimator.ofFloat(this,"centerX",oldCX,getCenterX())
                    .setDuration(1000);
            positionAnimatorX.start();
            ObjectAnimator positionAnimatorY = ObjectAnimator.ofFloat(this,"centerY",oldCY,getCenterY())
                    .setDuration(1000);
            positionAnimatorY.start();
            ObjectAnimator radiusAnimator = ObjectAnimator.ofFloat(this,"radius",oldRadius,getRadius())
                    .setDuration(1000);
            radiusAnimator.start();
        }


        return super.onTouchEvent(event);
    }
}
