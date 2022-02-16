package com.aarron.cloudinteractiveliuyuying.retrofit;

import com.aarron.cloudinteractiveliuyuying.model.Picture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {

    //取得所有照片
    @GET("photos")
    Call<List<Picture>> getPictures();

}
