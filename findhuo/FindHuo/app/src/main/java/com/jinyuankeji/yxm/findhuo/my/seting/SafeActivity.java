package com.jinyuankeji.yxm.findhuo.my.seting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jinyuankeji.yxm.findhuo.R;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class SafeActivity extends Activity{
    private RelativeLayout relativeLayout1,relativeLayout2;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_safety);
        relativeLayout1 = (RelativeLayout)findViewById(R.id.personal);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SafeActivity.this,SafeActivity1.class);
                startActivity(it);
            }
        });
        relativeLayout2 = (RelativeLayout)findViewById(R.id.personal2);
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SafeActivity.this,SafeActivity2.class);
                startActivity(it);
            }
        });

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
