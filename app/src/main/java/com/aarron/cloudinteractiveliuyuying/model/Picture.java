package com.aarron.cloudinteractiveliuyuying.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Picture implements Serializable {

    @SerializedName("id")
    private String id ;
    @SerializedName("title")
    private String title;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    private Bitmap bitmap;

    public Picture(String id, String title, String thumbnailUrl, Bitmap bitmap) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.bitmap = bitmap;
    }

    public Picture() {
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Picture(String id, String title, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
