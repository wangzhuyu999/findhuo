package com.jinyuankeji.yxm.findhuo.lottery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.base_chat.BaseChatActivity;
import com.jinyuankeji.yxm.findhuo.base.base_chat.MyConnectionListener;
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

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.checkPhone;


public class RegistActivity extends BaseChatActivity {

//    @InjectView(R.id.tv_title)
//    TextView tvTitle;
    //@InjectView(R.id.editText3)
  //  EditText etRegisterPhone;
  //  @InjectView(R.id.passwordd)
   // EditText etRegisterPwd;

    private Button register;
    private EditText etRegisterPhone,etRegisterPwd;
    private TextView verification;

//    @InjectView(R.id.et_register_repwd)
//    EditText etRegisterRepwd;
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
        setContentView(R.layout.zregister_activity);
//        ButterKnife.inject(this);
//        tvTitle.setText("用户注册");
        mActivity=this;
        register();

        verification();

        EMClient.getInstance().addConnectionListener(new MyConnectionListener(this));
    }

    private void verification() {
        verification = (TextView)findViewById(R.id.verification);
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }


    /**
     *
     */
    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",etRegisterPhone.getText().toString().trim());
       // params.addBodyParameter("account","18242364815");
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

                            Log.e("短信验证请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                         //   getList(arg0.result.toString());// 请求返回的数据，json解析


                        }

                    }

                });


    }


    private void register() {
        register = (Button)findViewById(R.id.button_register);
        etRegisterPhone = (EditText)findViewById(R.id.editText3);
        etRegisterPwd = (EditText)findViewById(R.id.passwordd);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userPhone = etRegisterPhone.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
//        String rePwd = etRegisterRepwd.getText().toString().trim();
                if (TextUtils.isEmpty(userPhone)) {
                    Toast.makeText(mActivity, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(pwd) ) {
                    Toast.makeText(mActivity, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
//        if (!pwd.equals(rePwd)) {
//            Toast.makeText(mActivity, "两次密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show();
//            etRegisterRepwd.setText("");
//            etRegisterRepwd.setFocusable(true);
//        }
                regist(userPhone, pwd);
            }
        });
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
