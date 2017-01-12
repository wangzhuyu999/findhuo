package com.jinyuankeji.yxm.findhuo.lottery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.base_chat.BaseChatActivity;
import com.jinyuankeji.yxm.findhuo.base.base_chat.MyConnectionListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class RegistActivity extends BaseChatActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.et_register_repwd)
    EditText etRegisterRepwd;
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
                    Toast.makeText(mActivity, "该用户已经注册过了,请换一个号码再试", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(RegistActivity.this, "注册失败了", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.inject(this);
        tvTitle.setText("用户注册");
        mActivity=this;

        EMClient.getInstance().addConnectionListener(new MyConnectionListener(this));
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        String userPhone = etRegisterPhone.getText().toString().trim();
        String pwd = etRegisterPwd.getText().toString().trim();
        String rePwd = etRegisterRepwd.getText().toString().trim();
        if (TextUtils.isEmpty(userPhone)) {
            Toast.makeText(mActivity, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(rePwd)) {
            Toast.makeText(mActivity, "密码不能为空", Toast.LENGTH_SHORT).show();
        }
        if (!pwd.equals(rePwd)) {
            Toast.makeText(mActivity, "两次密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show();
            etRegisterRepwd.setText("");
            etRegisterRepwd.setFocusable(true);
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
