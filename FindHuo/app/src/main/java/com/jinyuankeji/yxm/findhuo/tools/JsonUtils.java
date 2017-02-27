package com.jinyuankeji.yxm.findhuo.tools;

import com.google.gson.Gson;

/**
 * Created by lenovo on 2016/10/9.
 */
public class JsonUtils {


    public static <T> T getJtoC(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }


}
