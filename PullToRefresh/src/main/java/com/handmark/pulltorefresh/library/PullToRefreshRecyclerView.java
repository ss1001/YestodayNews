package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 宫成 on 16/12/1 下午8:05.
 * 让RecyclerView支持此lib
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {

    private Orientation mOrientation;

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return mOrientation == null ? Orientation.VERTICAL : mOrientation;
    }

    public void setOrientation(Orientation orientation) {
        this.mOrientation = orientation;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context,attrs);
        return recyclerView;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        if (mRefreshableView.getChildCount() > 1) {
            //屏幕中最后一个Item View
            View lastVisibleItemView = mRefreshableView.getChildAt(mRefreshableView.getChildCount() - 1);
            // 屏幕中最后一个Item在Adapter中的 position
            int adapterPositionOfLastVisibleItem = mRefreshableView.getChildLayoutPosition(lastVisibleItemView);
            //Adapter中最后一个position
            int lastAdapterPosition = mRefreshableView.getAdapter().getItemCount() - 1;
            //判断 Footer View被拉出来的时刻
            if (adapterPositionOfLastVisibleItem >= lastAdapterPosition) {
                // TODO: 16/12/1 测试Footer view的position  ,adapterPositionOfLastVisibleItem的值
                //判断 Footer View被全部拉出来时候返回true
                return lastVisibleItemView.getBottom() <= mRefreshableView.getBottom();
            }
        }
        return false;
    }


    @Override
    protected boolean isReadyForPullStart() {
        if (mRefreshableView.getChildCount() <= 0) {
            return true;
        }
        int firstAdapterPosition = 0;
        View firstVisibleItemView = mRefreshableView.getChildAt(0);
        int adapterPositionOfFirstVisibleItem = mRefreshableView.getChildLayoutPosition(firstVisibleItemView);
        if (adapterPositionOfFirstVisibleItem <= firstAdapterPosition) {
            // TODO: 16/12/1 测试Header view的position  ,adapterPositionOfFirstVisibleItem的值
            return firstVisibleItemView.getTop() >= mRefreshableView.getTop();
        }
        return false;
    }
}
