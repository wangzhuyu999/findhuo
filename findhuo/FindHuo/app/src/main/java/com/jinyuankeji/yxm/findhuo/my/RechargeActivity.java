package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jinyuankeji.yxm.findhuo.R;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public class RechargeActivity extends Activity{
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge);
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
