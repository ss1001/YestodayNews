package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;

public class CustomLoadingLayout extends LoadingLayout {
    private AnimationDrawable animation;

    public CustomLoadingLayout(Context context, Mode mode, Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        Drawable bgDrawable = mHeaderImage.getBackground();
        if (bgDrawable instanceof AnimationDrawable) {
            animation = (AnimationDrawable) bgDrawable;
        }
    }

    @Override
    protected int getVerticalLayoutID() {
        return R.layout.custom_header_vertical;
    }

    public void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    protected void onPullImpl(float scaleOfLayout) {
        if (null != animation && !animation.isRunning()) {
            animation.start();
        }
    }

    @Override
    protected void refreshingImpl() {
        // NO-OP
    }

    @Override
    protected void resetImpl() {
        if (null != animation) {
            animation.stop();
        }
    }

    @Override
    protected void pullToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected void releaseToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.empty_image_holder;
    }

}
