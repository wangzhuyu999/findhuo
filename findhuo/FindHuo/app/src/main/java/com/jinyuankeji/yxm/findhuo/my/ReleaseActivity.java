package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jinyuankeji.yxm.findhuo.R;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class ReleaseActivity extends Activity{
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_release);
        initView();
    }
    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
