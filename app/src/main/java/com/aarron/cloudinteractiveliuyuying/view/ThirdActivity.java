package com.aarron.cloudinteractiveliuyuying.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.aarron.cloudinteractiveliuyuying.R;
import com.aarron.cloudinteractiveliuyuying.databinding.ActivityThirdBinding;
import com.aarron.cloudinteractiveliuyuying.model.Picture;
import com.aarron.cloudinteractiveliuyuying.util.MyBitmapUtils;
import com.aarron.cloudinteractiveliuyuying.util.Utility;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private  ActivityThirdBinding binding ;
    private MyBitmapUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Picture picture = (Picture) intent.getSerializableExtra("Pic");
        utils = new MyBitmapUtils(this);
        if(picture != null){
            binding.ivPic.setTag(picture.getThumbnailUrl());
            utils.show(binding.ivPic,picture.getThumbnailUrl());
            binding.tvId.setText(picture.getId());
            binding.tvTitle.setText(picture.getTitle());
        }
        binding.backLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.backLayout){
            onBackPressed();
        }
    }

    class DownLoadTask extends AsyncTask<String ,Void, BitmapDrawable> {
        private ImageView mImageView;
        String url;
        Context context;
        public DownLoadTask(ImageView imageView, Context context){
            mImageView = imageView;
            this.context = context;
        }
        @Override
        protected BitmapDrawable doInBackground(String... params) {
            url = params[0];
            Bitmap bitmap = downLoadBitmap(url);
            BitmapDrawable drawable = new BitmapDrawable(context.getResources(),bitmap);
            return  drawable;
        }

        private Bitmap downLoadBitmap(String url) {
            Bitmap bitmap = null;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = client.newCall(request).execute();
                bitmap = BitmapFactory.decodeStream(response.body().byteStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(BitmapDrawable drawable) {
            super.onPostExecute(drawable);

            if ( mImageView != null && drawable != null){
                mImageView.setImageDrawable(drawable);
            }
        }
    }
}