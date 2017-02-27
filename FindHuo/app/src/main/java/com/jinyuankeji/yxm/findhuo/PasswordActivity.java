package com.jinyuankeji.yxm.findhuo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyuankeji.yxm.findhuo.bean.VercodeBean;
import com.jinyuankeji.yxm.findhuo.tools.JsonUtils;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.checkPhone;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.forgetPwd;

/**
 * Created by Administrator on 2017/1/7 0007.
 */

public class PasswordActivity extends Activity{
    private TextView verification;
    private VercodeBean bean1;
    private EditText etRegisterPhone,editText5,new_pwd;
    private Button sure;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_activity);
        etRegisterPhone = (EditText)findViewById(R.id.editText3);
        verification();
        back();

        forgetPwd();
    }

    private void back() {
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //忘记密码
    private void forgetPwd() {
        editText5 = (EditText)findViewById(R.id.editText5);
        new_pwd= (EditText)findViewById(R.id.new_pwd);
        sure = (Button)findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request2();
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

    //忘记密码接口
    private void request2() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",etRegisterPhone.getText().toString().trim());
        params.addBodyParameter("vercode",editText5.getText().toString().trim());
        params.addBodyParameter("pwd",new_pwd.getText().toString().trim());

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + forgetPwd, params,
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

                            Log.e("忘记密码请求成功",
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
                                Toast.makeText(getApplicationContext(), "用户修改密码失败",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10003){
                                Toast.makeText(getApplicationContext(), "用户新密码不能为空",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10004){
                                Toast.makeText(getApplicationContext(), "用户验证码填写错误",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10005){
                                Toast.makeText(getApplicationContext(), "用户未注册过",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10006){
                                Toast.makeText(getApplicationContext(), "手机号格式不正确",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10000){
                                Toast.makeText(getApplicationContext(), "请求失败",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10007){
                                Toast.makeText(getApplicationContext(), "密码不能为空",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }

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
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + checkPhone, params,
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
}
