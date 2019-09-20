package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Random;

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/8/8
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
public class ChartMusicView extends View {


    public Random random;
    public Paint paint;
    public int barWidth;
    public int barMargin;

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    float degree = 0;

    public void setDegree1(float degree1) {
        this.degree1 = degree1;
    }

    public void setDegree2(float degree2) {
        this.degree2 = degree2;
    }

    public float getDegree1() {
        return degree1;
    }

    public float getDegree2() {
        return degree2;
    }

    float degree1 = 0;
    float degree2 = 0;

    public ChartMusicView(Context context) {
        this(context,null);
    }


    public ChartMusicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }


    public ChartMusicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
//        an();
//        an1();
//        an2();
    }

    private void init() {
        random = new Random();
        paint = new Paint();
        paint.setColor(Color.RED);
        barWidth = 20;
        barMargin = 20;
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        int height = getMeasuredHeight();

        final RectF one = new RectF(0,random.nextInt(height),barWidth,height);
        final RectF one1 = new RectF(one.right+barMargin,random.nextInt(height),one.right+barMargin+barWidth,height);
        final RectF one2 = new RectF(one1.right+barMargin,random.nextInt(height),one1.right+barMargin+barWidth,height);
        final RectF one3 = new RectF(one2.right+barMargin,random.nextInt(height),one2.right+barMargin+barWidth,height);



        canvas.drawRect(one,paint);
        canvas.drawRect(one1,paint);
        canvas.drawRect(one2,paint);
        canvas.drawRect(one3,paint);
       /* postDelayed(new Runnable() {
            @Override
            public void run() {
                canvas.drawRect(one,paint);
            }
        },random.nextInt(20));
        postDelayed(new Runnable() {
            @Override
            public void run() {
                canvas.drawRect(one1,paint);
            }
        },random.nextInt(20));
        postDelayed(new Runnable() {
            @Override
            public void run() {
                canvas.drawRect(one2,paint);
            }
        },random.nextInt(20));

        postDelayed(new Runnable() {
            @Override
            public void run() {
                canvas.drawRect(one3,paint);
            }
        },random.nextInt(20));*/
        postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        },500);

    }


    public  void an(){

        PropertyValuesHolder sX = PropertyValuesHolder.ofFloat("degree", 0, 120);
        ObjectAnimator  mAnimator = ObjectAnimator.ofPropertyValuesHolder(this, sX);
        mAnimator.setDuration(600);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                post(new Runnable() {
                    @Override
                    public void run() {

                        invalidate();
                    }
                });
            }
        });
        mAnimator.start();
    }

    public  void an2(){

        PropertyValuesHolder sX = PropertyValuesHolder.ofFloat("degree2", 30, 120);
        ObjectAnimator  mAnimator = ObjectAnimator.ofPropertyValuesHolder(this, sX);
        mAnimator.setDuration(600);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                post(new Runnable() {
                    @Override
                    public void run() {

                        invalidate();
                    }
                });
            }
        });
        mAnimator.start();
    }

    public  void an1(){

        PropertyValuesHolder sX = PropertyValuesHolder.ofFloat("degree1", 120, 0);
        ObjectAnimator  mAnimator = ObjectAnimator.ofPropertyValuesHolder(this, sX);
        mAnimator.setDuration(600);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                post(new Runnable() {
                    @Override
                    public void run() {

                        invalidate();
                    }
                });
            }
        });
        mAnimator.start();
    }
}
