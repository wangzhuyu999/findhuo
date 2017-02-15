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
              requestPay();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private LotteryDetailPayBean mPayBean;
    private void requestPay() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        Log.d("LotteryDetailActivity", DataValue.LOTTERY_MAIN_ID);
        params.addBodyParameter("account", DataValue.DRIVER_YES_OR_NOT_TEL);
        params.addBodyParameter("paypwd", DataValue.LOTTERY_PASSWORD);
        params.addBodyParameter("Id_order",DataValue.LOTTERY_ORDER_ID);

        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_LOTTERY_PAY_CREATE, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("pay请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mPayBean = gson.fromJson(json, LotteryDetailPayBean.class);
                            if (mPayBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mPayBean.getRes() == 10001) {
                                Intent intent = new Intent(PayExtraActivity.this, PaySureActivity.class);
                                startActivity(intent);
                            } else if (mPayBean.getRes() == 10002) {
                                Toast.makeText(PayExtraActivity.this, "支付失败 ", Toast.LENGTH_SHORT).show();
                            } else if (mPayBean.getRes() == 10004) {
                                Toast.makeText(PayExtraActivity.this, "订单不存在", Toast.LENGTH_SHORT).show();
                            }else if (mPayBean.getRes() == 10003) {
                                Toast.makeText(PayExtraActivity.this, "您的账户余额不足请去充值", Toast.LENGTH_SHORT).show();
                            }else if (mPayBean.getRes() == 10005) {
                                Toast.makeText(PayExtraActivity.this, "支付密码输入错误", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(PayExtraActivity.this, PayExtraActivity.class);
//                                startActivity(intent);

                            }else if (mPayBean.getRes() == 10000) {
                                Toast.makeText(PayExtraActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                            }else if (mPayBean.getRes() == 10006) {
                                Toast.makeText(PayExtraActivity.this, "该手机号的账号不存在", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }
}
