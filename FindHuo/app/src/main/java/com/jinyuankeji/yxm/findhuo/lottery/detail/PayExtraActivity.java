package com.jinyuankeji.yxm.findhuo.lottery.detail;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */
public class PayExtraActivity extends BaseActivity {
    private Button rvPay;
    private ImageView back;

    @Override
    protected int initLayout() {
        return R.layout.activity_lottery_pay_extra;
    }

    @Override
    protected void initView() {
        rvPay = (Button) findViewById(R.id.btn_lottery_pay_extra);
        back = (ImageView) findViewById(R.id.lottery_pay_extra_back);

    }

    @Override
    protected void initData() {
        rvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayExtraActivity.this, PaySureActivity.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
