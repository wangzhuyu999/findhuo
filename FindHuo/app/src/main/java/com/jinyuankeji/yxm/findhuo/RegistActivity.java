package com.jinyuankeji.yxm.findhuo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.jinyuankeji.yxm.findhuo.base.base_chat.BaseChatActivity;
import com.jinyuankeji.yxm.findhuo.base.base_chat.MyConnectionListener;
import com.jinyuankeji.yxm.findhuo.bean.RegisterBean;
import com.jinyuankeji.yxm.findhuo.bean.VercodeBean;
import com.jinyuankeji.yxm.findhuo.tools.JsonUtils;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.appRegist;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.checkPhone;


public class RegistActivity extends BaseChatActivity {

    @InjectView(R.id.editText3)
    EditText etRegisterPhone;
    @InjectView(R.id.passwordd)
    EditText etRegisterPwd;

    private TextView verification;
    private VercodeBean bean1;
    private EditText editText5,number;
    private RegisterBean bean2;


    private static final int REG_SUCCESS = 1;
    private static final int REG_FAILED = 2;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REG_SUCCESS:
                    Toast.makeText(mActivity, "注册成功,请先登录", Toast.LENGTH_SHORT).show();
                    break;
                case REG_FAILED:
                    if (mActivity == null) Log.e("","是空了");
                    //Toast.makeText(mActivity, "该用户已经注册过了,请换一个号码再试", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.inject(this);
        mActivity=this;

        editText5 = (EditText)findViewById(R.id.editText5);
        number = (EditText)findViewById(R.id.number);

        verification();


        EMClient.getInstance().addConnectionListener(new MyConnectionListener(this));
    }

    //注册

    private void request2() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",etRegisterPhone.getText().toString().trim());
        params.addBodyParameter("vercode",editText5.getText().toString().trim());
        params.addBodyParameter("pwd",etRegisterPwd.getText().toString().trim());
        params.addBodyParameter("self_code",number.getText().toString().trim());

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST,URLValue.URL_NOR + appRegist, params,
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

                            Log.e("注册请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //  getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();
                            bean2 = JsonUtils.getJtoC(json, RegisterBean.class);
                            int i = bean2.getRes();

                            if(i == 10001){
                                Toast.makeText(getApplicationContext(), "成功",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10002){
                                Toast.makeText(getApplicationContext(), "用户添加失败",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10003){
                                Toast.makeText(getApplicationContext(), "用户数据验证失败",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10004){
                                Toast.makeText(getApplicationContext(), "用户验证码填写错误",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10005){
                                Toast.makeText(getApplicationContext(), "用户注册过",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10006){
                                Toast.makeText(getApplicationContext(), "手机号格式不正确",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10000){
                                Toast.makeText(getApplicationContext(), "请求失败",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                });


    }


    //短信验证
    private void verification() {
        verification = (TextView)findViewById(R.id.verification);
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request1();
            }
        });
    }

    //短信验证接口
    private void request1() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",etRegisterPhone.getText().toString().trim());
        params.addBodyParameter("actionid","1");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST,URLValue.URL_NOR + checkPhone, params,
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

                            Log.e("短信请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                          //  getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();
                            bean1 = JsonUtils.getJtoC(json, VercodeBean.class);
                            int i = bean1.getRes();

                            if(i == 10001){
                                Toast.makeText(getApplicationContext(), "成功",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10002){
                                Toast.makeText(getApplicationContext(), "短信记录存储失败",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10003){
                                Toast.makeText(getApplicationContext(), "短信发送失败",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10004){
                                Toast.makeText(getApplicationContext(), "手机格式不正确",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10000){
                                Toast.makeText(getApplicationContext(), "请求失败",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                });


    }

    @OnClick(R.id.register_re)
    public void onClick() {
        String userPhone = etRegisterPhone.getText().toString().trim();
        String pwd = etRegisterPwd.getText().toString().trim();
        request2();

        if (TextUtils.isEmpty(userPhone)) {
            Toast.makeText(mActivity, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pwd) ) {
            Toast.makeText(mActivity, "密码不能为空", Toast.LENGTH_SHORT).show();
        }
        regist(userPhone, pwd);
    }


    private void regist(final String userPhone, final String pwd) {
        new Thread() {//网络访问需要在子线程中进行
            @Override
            public void run() {
                //注册失败会抛出HyphenateException
                try {
                    EMClient.getInstance().createAccount(userPhone, pwd);//同步方法
                    mHandler.sendEmptyMessage(REG_SUCCESS);
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    finish();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e("","错误信息:" + e.getMessage());
                    e.getErrorCode();
                    mHandler.sendEmptyMessage(REG_FAILED);
                }
            }
        }.start();

    }
}
