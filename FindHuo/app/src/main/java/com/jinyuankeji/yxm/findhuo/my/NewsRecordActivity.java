package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.MoneyBean;
import com.jinyuankeji.yxm.findhuo.bean.NewsRecordBean;
import com.jinyuankeji.yxm.findhuo.my.adapter.MoneyAdapter;
import com.jinyuankeji.yxm.findhuo.my.adapter.NewsRecordAdapter;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.orderList;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.rechargeList;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class NewsRecordActivity extends Activity{
    private ImageView back;
    private String Phone;
    private NewsRecordBean bean;
    private NewsRecordAdapter myAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_new_record);
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user",Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_lottery);
        myAdapter = new NewsRecordAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        request();
        initView();
    }

    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account", Phone);
        params.addBodyParameter("page", "1");
        params.addBodyParameter("size", "100");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + orderList, params,
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

                            Log.e("消费记录请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                            Gson gson = new Gson();
                            bean = gson.fromJson(json, NewsRecordBean.class);

                            int res = bean.getRes();

                            if(res == 10001){
                                myAdapter.setAllBeen(bean);

                                mRecyclerView.setAdapter(myAdapter);

                            }else {

                            }
                        }
                    }
                });


    }
    private void initView() {
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
