package com.aarron.cloudinteractiveliuyuying.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aarron.cloudinteractiveliuyuying.model.Picture;
import com.aarron.cloudinteractiveliuyuying.repository.PictureRepository;

import java.util.List;

public class SecondViewModel extends ViewModel {
    private PictureRepository pictureRepository;

    public SecondViewModel() {
       pictureRepository = new PictureRepository();
    }

    public LiveData<List<Picture>> getAllPictures(){
        return pictureRepository.getPictures();
    }
}
