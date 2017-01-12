package com.jinyuankeji.yxm.findhuo.lottery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.base_chat.BaseChatActivity;
import com.jinyuankeji.yxm.findhuo.base.base_chat.GlobalField;
import com.jinyuankeji.yxm.findhuo.base.base_chat.MyConnectionListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends BaseChatActivity {
    private static final int LOGIN_SUCCESS = 1;
    private static final int LOGIN_FAILED = 2;

    @InjectView(R.id.et_main_userName)
    EditText etMainUserName;
    @InjectView(R.id.et_main_pwd)
    EditText etMainPwd;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOGIN_SUCCESS:
                    Toast.makeText(LoginActivity.this, "登录成功了", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        checkIsLogin();

        EMClient.getInstance().addConnectionListener(new MyConnectionListener(this));
    }


    private void checkIsLogin() {
        if (EMClient.getInstance().getInstance().isLoggedInBefore()) {

            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
            startActivity(new Intent(LoginActivity.this, MainCActivity.class));
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


    @OnClick(R.id.btn_main_login)
    public void onClick() {
        String userName = "" + etMainUserName.getText().toString().trim();
        String pwd = "" + etMainPwd.getText().toString().trim();
        login(userName, pwd);
    }


    private void login(final String userName, String pwd) {
        EMClient.getInstance().getInstance().login(userName, pwd, new EMCallBack() {//回调
            @Override
            public void onSuccess() {

                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                getSharedPreferences(GlobalField.USERINFO_FILENAME, MODE_PRIVATE).edit().putString("username", userName).commit();
                startActivity(new Intent(LoginActivity.this, MainCActivity.class));
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
