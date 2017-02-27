package com.jinyuankeji.yxm.findhuo.image_util;

import com.jinyuankeji.yxm.findhuo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class Instance {
    public static ImageLoader imageLoader = ImageLoader.getInstance();
    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true).cacheOnDisc(true).build();
    public static DisplayImageOptions optionsdefault = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(true)
            .cacheOnDisc(true).build();
    public static DisplayImageOptions chatoptions = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(true)
            .cacheOnDisc(true).build();

}
