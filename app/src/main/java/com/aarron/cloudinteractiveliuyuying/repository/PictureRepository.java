package com.aarron.cloudinteractiveliuyuying.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aarron.cloudinteractiveliuyuying.MainActivity;
import com.aarron.cloudinteractiveliuyuying.model.Picture;
import com.aarron.cloudinteractiveliuyuying.retrofit.MyAPIService;
import com.aarron.cloudinteractiveliuyuying.retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created By - Aaron
 * Created On - 2/15/2022 : 04:22 PM
 */

public class PictureRepository {

    private List<Picture> pictureList = new ArrayList<>();
    private MutableLiveData<List<Picture>> mutableLiveData = new MutableLiveData<>();


    public MutableLiveData<List<Picture>> getPictures() {
        MyAPIService apiService = RetrofitManager.getInstance().getAPI();
        Call<List<Picture>> call = apiService.getPictures();
        call.enqueue(new Callback<List<Picture>>() {
            @Override
            public void onResponse(Call<List<Picture>> call, Response<List<Picture>> response) {
                if (response.body() != null) {
                    pictureList = response.body();
                    Log.e("Json",response.body().toString());
                    mutableLiveData.setValue(pictureList);
                }
            }
            @Override
            public void onFailure(Call<List<Picture>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
