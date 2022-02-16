package com.aarron.cloudinteractiveliuyuying.util;

import com.aarron.cloudinteractiveliuyuying.model.Picture;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHelper {
    private static final DataHelper helper= new DataHelper();

    public static DataHelper getInstance() {return helper;}

    Map<String, WeakReference<List<Picture>>> data = new HashMap<String, WeakReference<List<Picture>>>();

    public void saveData(String id,List<Picture> Pictures) {
        data.put(id, new WeakReference<List<Picture>>(Pictures));
    }
    public List<Picture> getData(String id) {
        WeakReference<List<Picture>> objectWeakReference = data.get(id);
        return objectWeakReference.get();
    }
}
