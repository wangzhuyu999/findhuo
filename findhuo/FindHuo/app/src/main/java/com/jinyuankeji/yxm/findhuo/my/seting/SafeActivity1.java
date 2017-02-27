package com.jinyuankeji.yxm.findhuo.my.seting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.FindWorkBean;
import com.jinyuankeji.yxm.findhuo.bean.Safe1Bean;
import com.jinyuankeji.yxm.findhuo.declare.FindWorkActivity;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.pwdModify;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.workerFind;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class SafeActivity1 extends Activity{
    private String Phone;
    private EditText editText1,editText2,new_pwd;
    private Safe1Bean bean;
    private Button sure;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_safety1);

        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user",Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        new_pwd = (EditText)findViewById(R.id.new_pwd);
        sure = (Button)findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        // params.addBodyParameter("account","15998386324");
        params.addBodyParameter("account",Phone);
        params.addBodyParameter("pwd", editText1.getText().toString().trim());
        params.addBodyParameter("newpwd", editText2.getText().toString().trim());
        params.addBodyParameter("confirmpwd", new_pwd.getText().toString().trim());

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + pwdModify, params,
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

                            Log.e("修改密码请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                            Gson gson = new Gson();
                            bean = gson.fromJson(json, Safe1Bean.class);

                            int res = bean.getRes();

                            if(res == 10001){
                                Toast.makeText(SafeActivity1.this, "成功", Toast.LENGTH_SHORT).show();
                            }if(res == 10005){
                                Toast.makeText(SafeActivity1.this, "原密码和新密码不能相同", Toast.LENGTH_SHORT).show();
                            }if(res == 10002) {
                                Toast.makeText(SafeActivity1.this, "修改失败", Toast.LENGTH_SHORT).show();
                            }if(res == 10003) {
                                Toast.makeText(SafeActivity1.this, "用户不存在", Toast.LENGTH_SHORT).show();
                            }if(res == 10004) {
                                Toast.makeText(SafeActivity1.this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                            }if(res == 10000) {
                                Toast.makeText(SafeActivity1.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }if(res == 10006) {
                                Toast.makeText(SafeActivity1.this, "新密码和确认密码不一致", Toast.LENGTH_SHORT).show();
                            }if(res == 10007) {
                                Toast.makeText(SafeActivity1.this, "原密码错误", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });


    }
}
