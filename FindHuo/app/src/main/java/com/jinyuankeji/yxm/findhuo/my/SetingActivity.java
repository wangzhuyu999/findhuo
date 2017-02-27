package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.my.seting.AboutOurActivtiy;
import com.jinyuankeji.yxm.findhuo.my.seting.RemindActivity;
import com.jinyuankeji.yxm.findhuo.my.seting.SafeActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class SetingActivity extends Activity{
    private ImageView back;
    private RelativeLayout safe_relativelayout,relativeLayout2,about_our;
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
        safe_relativelayout = (RelativeLayout)findViewById(R.id.safe_relativelayout);
        safe_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SetingActivity.this, SafeActivity.class);
                startActivity(it);
            }
        });
        relativeLayout2 = (RelativeLayout)findViewById(R.id.relativeLayout2);
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent remind = new Intent(SetingActivity.this, RemindActivity.class);
                startActivity(remind);
            }
        });
        about_our = (RelativeLayout)findViewById(R.id.about_our);
        about_our.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(SetingActivity.this, AboutOurActivtiy.class);
                startActivity(about);
            }
        });

    }


}
