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

public class CustomSquareView extends View {

    public CustomSquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTop((float) 60);
        setLeft((float) 60);
        setLength((float) 100);
    }

    public float getX() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
        invalidate();
    }

    public float getY() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
        invalidate();
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
        invalidate();
    }

    public View getRectView() {
        rectView = findViewById(R.id.customCircleView);
        return rectView;
    }


    public int[] getRandomRGB(){
        Random random = new Random();
        int randomRGB[] = new int[3];
        randomRGB[0] = random.nextInt(255);
        randomRGB[1] = random.nextInt(255);
        randomRGB[2] = random.nextInt(255);
        return randomRGB;
    }
    private float top;
    private float left;
    private float length;
    private View rectView;


    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int randomRGB[] = getRandomRGB();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(255,randomRGB[0],randomRGB[1],randomRGB[2]));
        paint.setAntiAlias(true);
        canvas.drawRect(getX(), getY(), getX()+ getLength(),getY()+ getLength(),paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            Random random = new Random();
            float oldCX = getX();
            setTop(random.nextInt((int) (getWidth()- getLength())-30)+ getLength()+10);
            float oldCY = getY();
            setLeft(random.nextInt((int) (getHeight()- getLength())-60)+ getLength()+10);
            float oldLength = getLength();
            setLength(random.nextInt(100)+10);
            ObjectAnimator positionAnimatorX = ObjectAnimator.ofFloat(this,"top",oldCX, getX())
                    .setDuration(1000);
            positionAnimatorX.start();
            ObjectAnimator positionAnimatorY = ObjectAnimator.ofFloat(this,"left",oldCY, getY())
                    .setDuration(1000);
            positionAnimatorY.start();
            ObjectAnimator radiusAnimator = ObjectAnimator.ofFloat(this,"length",oldLength, getLength())
                    .setDuration(1000);
            radiusAnimator.start();
        }


        return super.onTouchEvent(event);
    }


}
