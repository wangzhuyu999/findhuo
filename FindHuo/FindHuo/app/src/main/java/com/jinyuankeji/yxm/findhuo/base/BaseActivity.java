package com.jinyuankeji.yxm.findhuo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public abstract class BaseActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initData();
    }
//kakkakakakakkakakakakaka
    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();
}
