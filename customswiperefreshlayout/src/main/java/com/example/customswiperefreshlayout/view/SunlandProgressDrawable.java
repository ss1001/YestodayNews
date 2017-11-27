package com.example.customswiperefreshlayout.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.view.View;

import com.example.customswiperefreshlayout.R;

/**
 * Created by shesong on 2017/11/24.
 */

public class SunlandProgressDrawable extends MaterialProgressDrawable {
    private AnimationDrawable mAnimation;
    private View mParent;
    private Bitmap mBitmap;
    private Paint paint;
    private Context context;
    private  int index;
    private long mStartTime = -1;
    private long mDuration = 200;


    public SunlandProgressDrawable(Context context, View parent) {
        super(context, parent);
        mParent = parent;
        paint = new Paint();
        setBackgroundColor(Color.WHITE);
        this.context=context;
        mAnimation=(AnimationDrawable)context.getResources().getDrawable(R.drawable.loading_more);
        mBitmap=((BitmapDrawable)(mAnimation.getFrame(index))).getBitmap();
    }

    public void setAnimation(AnimationDrawable animation) {
        this.mAnimation=animation;
    }

    public void setmDuration(int ms){
        this.mDuration=ms;
    }

    @Override
    public void start(){
    index=0;
    }

    @Override
    public void stop(){
        mParent.setBackground(null);
    }
    @Override
    public void draw(Canvas canvas){
        if (mStartTime == -1) {
            mStartTime = SystemClock.uptimeMillis();
        }
        long curTime = SystemClock.uptimeMillis();
        boolean done = true;
        // t为一个0到1均匀变化的值
        float t = (curTime - mStartTime) / (float) mDuration;
        if (t < 1) {
            done = false;
        }

        if (t> 1) {
            done = true;
            if(index<(mAnimation.getNumberOfFrames()-1)) {
                index++;
            }
            else {
                index = 0;
            }
            mStartTime=-1;
        }

//        if (!done) {
//            invalidateSelf();
//        }
            Rect bound = getBounds();
            mBitmap=((BitmapDrawable)(mAnimation.getFrame(index))).getBitmap();
            Rect src = new Rect(0,0,mBitmap.getWidth(),mBitmap.getHeight());
            canvas.drawBitmap(mBitmap,src,bound,paint);


    }
}
