package com.jinyuankeji.yxm.findhuo.my.seting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.ConfigInfoBean;
import com.jinyuankeji.yxm.findhuo.bean.NewsBean;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.configInfo;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.noticeList;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class AboutOurActivtiy extends Activity{
    private ConfigInfoBean bean;
    private TextView our;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_about_our);
        our = (TextView)findViewById(R.id.textView);
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        request();
    }
    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("type", "1");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + configInfo, params,
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

                            Log.e("我们请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                            Gson gson = new Gson();
                            bean = gson.fromJson(json, ConfigInfoBean.class);

                            int res = bean.getRes();

                            if(res == 10001){

                                String our_bean = bean.getData().getContent().toString();
                                our.setText(our_bean);

                            }else {

                            }
                        }
                    }
                });


    }
}
