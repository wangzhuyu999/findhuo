package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jinyuankeji.yxm.findhuo.R;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class SetingActivity extends Activity{
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_seting);

        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.text1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
