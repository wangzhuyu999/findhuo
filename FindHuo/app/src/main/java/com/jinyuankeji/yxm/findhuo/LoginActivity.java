package com.jinyuankeji.yxm.findhuo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.jinyuankeji.yxm.findhuo.base.base_chat.BaseChatActivity;
import com.jinyuankeji.yxm.findhuo.base.base_chat.GlobalField;
import com.jinyuankeji.yxm.findhuo.base.base_chat.MyConnectionListener;
import com.jinyuankeji.yxm.findhuo.bean.LoginBean;
import com.jinyuankeji.yxm.findhuo.bean.RegisterBean;
import com.jinyuankeji.yxm.findhuo.lottery.ui.MainCActivity;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
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

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.appLogin;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.appRegist;


public class LoginActivity extends BaseChatActivity {
    private static final int LOGIN_SUCCESS = 1;
    private static final int LOGIN_FAILED = 2;

    @InjectView(R.id.editText3)
    EditText etMainUserName;
    @InjectView(R.id.editText4)
    EditText etMainPwd;
    private TextView pwd;
    private LoginBean bean;

    //    @InjectView(R.id.register)
//    Button btn;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOGIN_SUCCESS:
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    break;
                case LOGIN_FAILED:
                    int code = msg.arg1;
                    switch (code) {
                        case 202:
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
            }
        }
    };

private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        checkIsLogin();

        pwd();
        EMClient.getInstance().addConnectionListener(new MyConnectionListener(this));

    }

    //登录

    private void request(){
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",etMainUserName.getText().toString().trim());
        params.addBodyParameter("pwd",etMainPwd.getText().toString().trim());

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + appLogin, params,
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

                            Log.e("登录请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //  getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();
                            bean = JsonUtils.getJtoC(json, LoginBean.class);
                         //   Contants.account = etMainUserName.getText().toString().trim();

                            int i = bean.getRes();

                            if(i == 10001){
                                Toast.makeText(getApplicationContext(), "登录成功",
                                        Toast.LENGTH_SHORT).show();
                                //将数据保存至SharedPreferences:
                                SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("phone", etMainUserName.getText().toString().trim());
                                editor.commit();



                            }if(i == 10002){
                                Toast.makeText(getApplicationContext(), "用户密码错误",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10003){
                                Toast.makeText(getApplicationContext(), "用户不存在",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10004){
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

    private void pwd() {
        pwd = (TextView)findViewById(R.id.pwd);
        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this,PasswordActivity.class);
                startActivity(it);
            }
        });
    }


    private void checkIsLogin() {
        if (EMClient.getInstance().getInstance().isLoggedInBefore()) {

            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
            if (DataValue.FINDHUO_CHAT.equals("添加")) {
                startActivity(new Intent(LoginActivity.this, MainCActivity.class));
            } else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }



            finish();
            Log.e("main", "已经登录过了！");
        }
    }


    public void onLogout(View view) {
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.e("main", "下线成功了");
            }

            @Override
            public void onError(int i, String s) {
                Log.e("main", "下线失败了！" + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }


    @OnClick(R.id.login)
    public void onClick() {
        String userName = "" + etMainUserName.getText().toString().trim();
        String pwd = "" + etMainPwd.getText().toString().trim();
        Contants.count = etMainUserName.getText().toString().trim();
        Contants.pwd = etMainPwd.getText().toString().trim();
        request();

        if (userName.length()!= 0 && pwd.length() == 0) {
            Toast.makeText(this, "请输入密码.", Toast.LENGTH_SHORT).show();
            Log.d("LoginActivity", "请输入密码.");
        } else if (userName.length() == 0 && pwd.length() != 0) {
            Toast.makeText(this, "请输入手机号.", Toast.LENGTH_SHORT).show();
            Log.d("LoginActivity", "请输手机号.");
        } else if (userName.length() == 0 && pwd.length() == 0) {
            Toast.makeText(this, "请输入手机号和密码.", Toast.LENGTH_SHORT).show();
            Log.d("LoginActivity", "请输入手机号和密码");
        } else {
            login(userName, pwd);
        }
    }


    private void login(final String userName, String pwd) {
        EMClient.getInstance().getInstance().login(userName, pwd, new EMCallBack() {//回调
            @Override
            public void onSuccess() {

                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                getSharedPreferences(GlobalField.USERINFO_FILENAME, MODE_PRIVATE).edit().putString("username", userName).commit();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Log.e("main", "登录聊天服务器成功！");
                mHandler.sendEmptyMessage(LOGIN_SUCCESS);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.e("main", "登录聊天服务器失败！" + message + " code = " + code);
                Message msg = mHandler.obtainMessage();
                msg.obj = message;
                msg.arg1 = code;
                msg.what = LOGIN_FAILED;
                mHandler.sendMessage(msg);
            }
        });
    }


    public void onRegist(View view) {
        startActivity(new Intent(this, RegistActivity.class));
        finish();
    }


}
