package com.aarron.cloudinteractiveliuyuying.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.aarron.cloudinteractiveliuyuying.adapter.PicAdapter;
import com.aarron.cloudinteractiveliuyuying.databinding.ActivitySecondBinding;
import com.aarron.cloudinteractiveliuyuying.model.Picture;
import com.aarron.cloudinteractiveliuyuying.util.DataHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SecondActivity extends AppCompatActivity {
    private ExecutorService mExecutorService;
    public static boolean mRecyclerViewIsIdle = true;
    private PicAdapter adapter;
    private ActivitySecondBinding binding;
    private List<Picture> pictureList = new ArrayList<>();
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
//        initViewModel();
        mExecutorService = Executors.newFixedThreadPool(30);
        dataHelper = DataHelper.getInstance();
        initRecyclerview();
        pictureList = dataHelper.getData("piclist");
        if (pictureList != null) {
            adapter.setPictureList(pictureList);
        }

        adapter.setOnItemClickListener(new PicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Picture pic) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("Pic", pic);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerview() {
        adapter = new PicAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(binding.rsv.getContext(), 4);
        binding.rsv.setLayoutManager(gridLayoutManager);
        binding.rsv.setAdapter(adapter);
    }

}