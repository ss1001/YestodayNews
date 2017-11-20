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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shesong.yestodaynews.R;
import com.example.shesong.yestodaynews.entity.NewsEntity;
import com.example.shesong.yestodaynews.util.NetUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by shesong on 2017/11/17.
 */

public class NewsAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private View headerView;
    private List<NewsEntity> newsLists;
    public static final int HEADER_VIEW=0;
    public static final int CONTENT_VIEW=1;
    public static final int TOIL_VIEW=2;

    public NewsAdapter(Context context,List<NewsEntity>newsLists){
        this.mContext=context;
        this.newsLists=newsLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==HEADER_VIEW&&headerView!=null){
            return new myViewHolder(headerView);
        }
//        if(viewType==TOIL_VIEW) {
//            View toilView =LayoutInflater.from(parent.getContext()).inflate(R.layout.toil_view,parent,false);
//            return new toilViewHolder(toilView);
//        }
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        myViewHolder myHolder=new myViewHolder(itemView);
        return myHolder;
    }

    @Override
    public int getItemViewType(int position){
        if(headerView==null)
            return CONTENT_VIEW;
        if(position==0)
            return HEADER_VIEW;
//        if(position==newsLists.size()+1)
//            return TOIL_VIEW;
        return  CONTENT_VIEW;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==HEADER_VIEW||getItemViewType(position)==TOIL_VIEW)
            return;
        int pos=getRealPosition(holder);
        if(holder instanceof myViewHolder){
            //(((myViewHolder) holder)).simpleDraweeView.setImageURI(Uri.parse("res://com.example.shesong.yestodaynews/"+R.drawable.activity_launching_drawable_logo));
            (((myViewHolder) holder)).simpleDraweeView.setImageURI(Uri.parse(NetUtil.getPhotoUrl()));
  //          Log.d("110",newsLists.get(pos).getTitle()+newsLists.get(pos).getDescription());
            ((myViewHolder) holder).tvTitle.setText(newsLists.get(pos).getDescription());
            ((myViewHolder) holder).tvContent.setText(newsLists.get(pos).getTitle());
       }
        return;
    }

    private int getRealPosition(RecyclerView.ViewHolder viewHolder){
        int pos=viewHolder.getLayoutPosition();
        return headerView==null?pos:pos-1;
    }
    @Override
    public int getItemCount() {
        return newsLists.size()+2;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView simpleDraweeView;
        private TextView tvTitle;
        private TextView tvContent;

        public myViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView=(SimpleDraweeView)itemView.findViewById(R.id.simpleDraweeView);
            tvTitle=(TextView)itemView.findViewById(R.id.tv_title);
            tvContent=(TextView)itemView.findViewById(R.id.tv_content);
        }
    }
//    public class toilViewHolder extends RecyclerView.ViewHolder{
//
//        private ProgressBar progressBar;
//        public toilViewHolder(View itemView) {
//            super(itemView);
//            progressBar=(ProgressBar)itemView.findViewById(R.id.progressBar);
//        }
//    }
}
