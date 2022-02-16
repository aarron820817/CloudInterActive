package com.aarron.cloudinteractiveliuyuying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import com.aarron.cloudinteractiveliuyuying.databinding.ActivityMainBinding;
import com.aarron.cloudinteractiveliuyuying.model.Picture;
import com.aarron.cloudinteractiveliuyuying.util.DataHelper;
import com.aarron.cloudinteractiveliuyuying.util.Utility;
import com.aarron.cloudinteractiveliuyuying.view.SecondActivity;
import com.aarron.cloudinteractiveliuyuying.viewmodel.SecondViewModel;

import java.io.Serializable;
import java.util.List;

import static com.aarron.cloudinteractiveliuyuying.util.PermisionUtils.verifyStoragePermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private SecondViewModel viewModel;
    private List<Picture> pictureList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        verifyStoragePermissions(this);
        binding.btnNext.setOnClickListener(this);
        initViewModel();

    }


    private void initViewModel() {
        Utility.showProgressDialog(this,"資料讀取中...");
        viewModel = ViewModelProviders.of(this).get(SecondViewModel.class);
        viewModel.getAllPictures().observe(this, new Observer<List<Picture>>() {
            @Override
            public void onChanged(List<Picture> pictures) {
                pictureList = pictures;
                DataHelper dataHelper = DataHelper.getInstance();
                dataHelper.saveData("piclist",pictureList);
                Utility.dismissProgressDialog();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_Next){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }
}