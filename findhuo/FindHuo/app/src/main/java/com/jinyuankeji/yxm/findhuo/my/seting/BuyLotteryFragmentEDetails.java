package com.jinyuankeji.yxm.findhuo.my.seting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.BuyLotteryFragmentSBean;
import com.jinyuankeji.yxm.findhuo.bean.OrderInfoBean;
import com.jinyuankeji.yxm.findhuo.my.adapter.BuyLotteryFragmentEAdapter;
import com.jinyuankeji.yxm.findhuo.my.adapter.BuyLotteryFragmentEDetailsAdapter;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.noDraft;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.orderInfo;

/**
 * Created by Administrator on 2017/2/21 0021.
 */

public class BuyLotteryFragmentEDetails extends Activity{
    private BuyLotteryFragmentEDetailsAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private OrderInfoBean bean;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_lottery_e_details);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_lottery);
        myAdapter = new BuyLotteryFragmentEDetailsAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        params.addBodyParameter("id_order", Contants.id_order);
        Log.e("11111111111111",Contants.id_order+"");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + orderInfo, params,
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

                            Log.e("已出票请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                            Gson gson = new Gson();
                            bean = gson.fromJson(json, OrderInfoBean.class);

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
}
