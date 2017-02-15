package com.jinyuankeji.yxm.findhuo.lottery.detail;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */
public class PaySureActivity extends BaseActivity {
    private Button rvPay;
    private ImageView back;
    private EditText etPassword;

    @Override
    protected int initLayout() {
        return R.layout.activity_lottery_pay_sure;
    }

    @Override
    protected void initView() {
        rvPay = (Button) findViewById(R.id.btn_lottery_pay_sure);
        back = (ImageView) findViewById(R.id.lottery_pay_sure_back);
        etPassword = (EditText) findViewById(R.id.et_lottery_pay_sure_password);
    }

    @Override
    protected void initData() {
        etPassword.setFocusable(false);
//        etPassword.clearFocus();

        etPassword.setFocusable(true);
        etPassword.setFocusableInTouchMode(true);
        etPassword.requestFocus();
        etPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        if (etPassword.getText().toString().length()>= 0){
            Toast.makeText(PaySureActivity.this, "获取焦点" + etPassword.getText().toString().length(), Toast.LENGTH_SHORT).show();
            rvPay.setBackgroundResource(R.mipmap.btn_pay_selected3x);
            rvPay.setTextColor(Color.WHITE);

        }
            }
        });


        rvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void showAlertDialog() {

        AlertDialog.Builder alert1 = new AlertDialog.Builder(this);
        alert1.setMessage("付款成功。");

        alert1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert1.show();

    }
}
