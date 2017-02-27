package com.jinyuankeji.yxm.findhuo.lottery.detail;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.Constants;
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
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */
public class PaySureActivity extends BaseActivity {
    private Button rvPay;
    private ImageView back;
    private EditText etPassword;
    private Button btnPay;

    @Override
    protected int initLayout() {
        return R.layout.activity_lottery_pay_sure;
    }

    @Override
    protected void initView() {
        rvPay = (Button) findViewById(R.id.btn_lottery_pay_sure);
        back = (ImageView) findViewById(R.id.lottery_pay_sure_back);
        etPassword = (EditText) findViewById(R.id.et_lottery_pay_sure_password);
        btnPay = (Button) findViewById(R.id.pay);
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

                if (etPassword.getText().toString().length() >= 0) {
                    Toast.makeText(PaySureActivity.this, "获取焦点" + etPassword.getText().toString().length(), Toast.LENGTH_SHORT).show();
                    rvPay.setBackgroundResource(R.mipmap.btn_pay_selected3x);
                    rvPay.setTextColor(Color.WHITE);

                }
            }
        });


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

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pay();
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

    private LotteryDetailPayBean mPayBean;

    private void requestPay() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        Log.d("LotteryDetailActivity", DataValue.LOTTERY_MAIN_ID);
        params.addBodyParameter("account", DataValue.DRIVER_YES_OR_NOT_TEL);
        params.addBodyParameter("paypwd", etPassword.getText().toString());
        params.addBodyParameter("Id_order", DataValue.LOTTERY_ORDER_ID);

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
                                showAlertDialog();
                            } else if (mPayBean.getRes() == 10002) {
                                Toast.makeText(PaySureActivity.this, "支付失败 ", Toast.LENGTH_SHORT).show();
                            } else if (mPayBean.getRes() == 10004) {
                                Toast.makeText(PaySureActivity.this, "订单不存在", Toast.LENGTH_SHORT).show();
                            } else if (mPayBean.getRes() == 10003) {
                                Toast.makeText(PaySureActivity.this, "您的账户余额不足请去充值", Toast.LENGTH_SHORT).show();
                            } else if (mPayBean.getRes() == 10005) {
                                Toast.makeText(PaySureActivity.this, "支付密码输入错误", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(PayExtraActivity.this, PayExtraActivity.class);
//                                startActivity(intent);

                            } else if (mPayBean.getRes() == 10000) {
                                Toast.makeText(PaySureActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                            } else if (mPayBean.getRes() == 10006) {
                                Toast.makeText(PaySureActivity.this, "该手机号的账号不存在", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }


    IWXAPI api;

//    private void pay() {
//        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
//
//        try {
//            byte[] buf = Util.httpGet(url);
//            Log.d("PayActivity", "buf:" + buf);
//
//            if (buf != null && buf.length > 0) {
//                String content = new String(buf);
//                Log.e("get server pay params:", content);
//                JSONObject json = new JSONObject(content);
//                if (null != json && !json.has("retcode")) {
//                    PayReq request = new PayReq();
//                    request.appId = "wx63c88ed7e6190564";
//                    request.partnerId = "1900000109";
//                    request.prepayId = "1101000000140415649af9fc314aa427";
//                    request.packageValue = "Sign=WXPay";
//                    request.nonceStr = "1101000000140429eb40476f8896f4c9";
//                    request.timeStamp = "1398746574";
//                    request.sign = "c1a27813d84137e40538d2df93c800ff";
//                    api.sendReq(request);
//                    Toast.makeText(PaySureActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
//                    Toast.makeText(PaySureActivity.this, "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Log.d("PAY_GET", "服务器请求错误");
//                Toast.makeText(PaySureActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            Log.e("PAY_GET", "异常：" + e.getMessage());
//            Toast.makeText(PaySureActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }


}
