package com.jinyuankeji.yxm.findhuo.my.seting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.platform.comapi.map.A;
import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.AuthenticationBean;
import com.jinyuankeji.yxm.findhuo.bean.Safe1Bean;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.authentication;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.pwdModify;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class SafeActivity2 extends Activity{
    private ImageView back;
    private TextView verification;
    private EditText editText,phone;
    private AuthenticationBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_safety2);
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        verification = (TextView)findViewById(R.id.verification);
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request1();
            }

        });
        editText = (EditText)findViewById(R.id.editText);
        phone = (EditText)findViewById(R.id.phone);

    }
    private void request1() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        // params.addBodyParameter("account","15998386324");
        params.addBodyParameter("account",phone.getText().toString().trim());
        params.addBodyParameter("vercode", editText.getText().toString().trim());


        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + authentication, params,
                new RequestCallBack<String>() {
                    // 请求接口失败 arg1 为后台返回的错误信息
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败",
                                "111111111111111111111 error: "
                                        + arg1.toString());
                    }

                    // 请求接口成功 arg0.tostring 为后台返回的信息
                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {

                        if (arg0.result.toString().equals("0")) {

                        } else {

                            Log.e("验证码请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                            Gson gson = new Gson();
                            bean = gson.fromJson(json, AuthenticationBean.class);

                            int res = bean.getRes();

                            if(res == 10001){
                                Toast.makeText(SafeActivity2.this, "成功", Toast.LENGTH_SHORT).show();
                            }if(res == 10002) {
                                Toast.makeText(SafeActivity2.this, "该手机号的用户不存在", Toast.LENGTH_SHORT).show();
                            }if(res == 10003) {
                                Toast.makeText(SafeActivity2.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                            }if(res == 10000) {
                                Toast.makeText(SafeActivity2.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });


    }
}
