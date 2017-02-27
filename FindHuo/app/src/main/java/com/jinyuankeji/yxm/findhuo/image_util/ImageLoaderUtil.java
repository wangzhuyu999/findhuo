package com.jinyuankeji.yxm.findhuo.image_util;

import android.widget.ImageView;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class ImageLoaderUtil {
    public static void getInternetImage(String url, ImageView imageView) {

        Instance.imageLoader.displayImage(url, imageView,
                Instance.optionsdefault);
    }

}
