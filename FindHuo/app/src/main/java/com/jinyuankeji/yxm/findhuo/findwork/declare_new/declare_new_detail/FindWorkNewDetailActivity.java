package com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;

/**
 * Created by  yxiaomin on 2016/12/21 0021.
 */

public class FindWorkNewDetailActivity extends BaseActivity {
    private ImageView back;
    private ImageView ivTel;
    private TextView tvTitle;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_declare_new_detail;
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.findwork_declare_new_detail_back);
ivTel = (ImageView) findViewById(R.id.iv_findwork_new_detail_tel_call);
        tvTitle = (TextView) findViewById(R.id.tv_findwork_declare_new_detail_title);

    }

    @Override
    protected void initData() {
        tvTitle.setText(DataValue.FINDHUO_DETAIL_NOR);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "34435353"));
                startActivity(phoneCall);
//                number="";
            }
        });


    }
}
