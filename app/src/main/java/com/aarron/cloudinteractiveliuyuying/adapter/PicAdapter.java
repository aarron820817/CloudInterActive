package com.aarron.cloudinteractiveliuyuying.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aarron.cloudinteractiveliuyuying.databinding.ItemPictureBinding;
import com.aarron.cloudinteractiveliuyuying.model.Picture;
import com.aarron.cloudinteractiveliuyuying.util.MyBitmapUtils;

import java.util.ArrayList;
import java.util.List;

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.PicViewHolder>{
    private List<Picture> pictureList = new ArrayList<>();
    private  OnItemClickListener listener;
    private Context context;
    private MyBitmapUtils utils;


    public PicAdapter(Context context) {
        this.context = context;
        utils=new MyBitmapUtils(context);

    }

    public void setPictureList(List<Picture> pictureList){
        this.pictureList = pictureList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemPictureBinding itemPictureBinding = ItemPictureBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PicViewHolder(itemPictureBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PicViewHolder holder, int position) {

        final Picture picture = pictureList.get(position);
        holder.itemPictureBinding.ivPic.setImageBitmap(null);
        holder.itemPictureBinding.ivPic.setTag(picture.getThumbnailUrl());
        utils.show(holder.itemPictureBinding.ivPic,picture.getThumbnailUrl());
        holder.itemPictureBinding.tvId.setText(picture.getId());
        holder.itemPictureBinding.tvTitle.setText(picture.getTitle());
    }


    @Override
    public int getItemCount() {
        return pictureList  == null ? 0 : pictureList.size();
    }



    public class PicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemPictureBinding itemPictureBinding;

        public PicViewHolder( ItemPictureBinding b) {
          super(b.getRoot());
          itemPictureBinding = b;
          itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            listener.onItemClick(pictureList.get(getPosition()));
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Picture pic);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener =  listener;
    }



}
