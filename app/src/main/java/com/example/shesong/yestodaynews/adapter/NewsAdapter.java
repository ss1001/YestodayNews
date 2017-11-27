package com.example.shesong.yestodaynews.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shesong.yestodaynews.R;
import com.example.shesong.yestodaynews.entity.NewsEntity;
import com.example.shesong.yestodaynews.ui.ICustumClickListener;
import com.example.shesong.yestodaynews.util.NetUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by shesong on 2017/11/17.
 */

public class NewsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private View headerView;
    private View toilView;
    private List<NewsEntity> newsLists;
    public static final int HEADER_VIEW = 0;
    public static final int CONTENT_VIEW = 1;
    public static final int TOIL_VIEW = 2;
    private ICustumClickListener custumClickListener;

    public NewsAdapter(Context context, List<NewsEntity> newsLists) {
        this.mContext = context;
        this.newsLists = newsLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW && headerView != null) {
            return new myViewHolder(headerView, custumClickListener);
        }
        if (viewType == TOIL_VIEW && toilView != null) {
            return new myViewHolder(toilView, custumClickListener);
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        myViewHolder myHolder = new myViewHolder(itemView, custumClickListener);
        return myHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView == null)
            return CONTENT_VIEW;
        if (position == 0)
            return HEADER_VIEW;
        if (position == newsLists.size() + 1)
            return TOIL_VIEW;
        return CONTENT_VIEW;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEADER_VIEW || getItemViewType(position) == TOIL_VIEW)
            return;
        int pos = getRealPosition(holder);
        if (holder instanceof myViewHolder) {
            (((myViewHolder) holder)).simpleDraweeView.setImageURI(Uri.parse(NetUtil.getPhotoUrl()));
            DraweeController draweeController = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(NetUtil.getPhotoUrl())).setTapToRetryEnabled(true).build();
            (((myViewHolder) holder)).simpleDraweeView.setController(draweeController);
            ((myViewHolder) holder).tvTitle.setText(newsLists.get(pos).getDescription());
            ((myViewHolder) holder).tvContent.setText(newsLists.get(pos).getTitle());
            final ImageView goodJobView = (((myViewHolder) holder)).goodJob;
            final TextView goodJobNum = (((myViewHolder) holder)).goodNum;
            goodJobView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!((myViewHolder) holder).isClicked) {
                        goodJobView.setImageResource(R.drawable.post_more_thumb_up_clicking);
                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.my_anim);
                        goodJobView.startAnimation(animation);
                        goodJobNum.setText((Integer.parseInt(goodJobNum.getText().toString()) + 1) + "");
                        ((myViewHolder) holder).isClicked = true;
                    } else {
                        goodJobView.setImageResource(R.drawable.post_more_thumb_up_unclick);
                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.my_anim);
                        goodJobView.startAnimation(animation);
                        goodJobNum.setText((Integer.parseInt(goodJobNum.getText().toString()) - 1) + "");
                        ((myViewHolder) holder).isClicked = false;
                    }
                }
            });
        }
        return;
    }

    private int getRealPosition(RecyclerView.ViewHolder viewHolder) {
        int pos = viewHolder.getLayoutPosition();
        return headerView == null ? pos : pos - 1;
    }

    @Override
    public int getItemCount() {

        if (headerView != null && toilView != null)
            return newsLists.size() + 2;
        if (headerView == null && toilView == null)
            return newsLists.size();
        else
            return newsLists.size() + 1;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    public void setToilView(View toilView) {
        this.toilView = toilView;
        notifyItemInserted(getItemCount() - 1);
    }

    public void setOnClickListener(ICustumClickListener listener) {
        this.custumClickListener = listener;
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SimpleDraweeView simpleDraweeView;
        private TextView tvTitle;
        private TextView tvContent;
        private ICustumClickListener clickListener;
        private ImageView goodJob;
        private TextView goodNum;
        private boolean isClicked = false;

        public myViewHolder(View itemView, ICustumClickListener clickListener) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.simpleDraweeView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            goodJob = (ImageView) itemView.findViewById(R.id.headerview_section_post_detail_iv_share);
            goodNum = (TextView) itemView.findViewById(R.id.item_section_info_post_user_tv_msgcount);
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.custumClick(v, getPosition());
            }
        }
    }

}
