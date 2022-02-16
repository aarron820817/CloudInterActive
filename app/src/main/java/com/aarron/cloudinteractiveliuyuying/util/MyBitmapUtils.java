package com.aarron.cloudinteractiveliuyuying.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyBitmapUtils {
    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    private ExecutorService service;
    private Handler mainHandler;

    public MyBitmapUtils(Context context) {
        mMemoryCacheUtils = new MemoryCacheUtils();
        mLocalCacheUtils = new LocalCacheUtils(context);
        service = Executors.newFixedThreadPool(8);
        this.mainHandler = new Handler(Looper.getMainLooper());
        mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils, mMemoryCacheUtils, mainHandler);
    }

    public class MyThread1 extends Thread {
        private ImageView ivPic;
        private String url;

        public MyThread1(ImageView ivPic, String url) {
            this.ivPic = ivPic;
            this.url = url;
        }

        public void run() {
            disPlay(ivPic, url);
        }

    }

    public void show(ImageView ivPic, String url) {
//        new BitmapTask().execute(ivPic, url);//启动AsyncTask
        if (!ivPic.getTag().toString().equals(url)) {
            Log.e("DownLoadTask", "TAG 不對 1");
            return;
        }
        service.execute(new MyThread1(ivPic, url));
    }

    public void disPlay(ImageView ivPic, String url) {
//        ivPic.setImageResource(R.mipmap.pic_item_list_default);
        if (!ivPic.getTag().toString().equals(url)) {
            Log.e("DownLoadTask", "TAG 不對 2");
            return;
        }
        final Bitmap bitmap;
        final Bitmap bitmap2;
        //内存缓存
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap != null) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    ivPic.setImageBitmap(bitmap);
                }
            });

            return;
        }

        //本地缓存
        bitmap2 = mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap2 != null) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    ivPic.setImageBitmap(bitmap2);
                }
            });

            mMemoryCacheUtils.setBitmapToMemory(url, bitmap2);
            return;
        }
        //网络缓存
        mNetCacheUtils.getBitmapFromNet(ivPic, url);
    }
}