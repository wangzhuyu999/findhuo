package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.Util.RecyclerItemClickListener;
import com.jinyuankeji.yxm.findhuo.bean.BuyLotteryFragmentSBean;
import com.jinyuankeji.yxm.findhuo.bean.NewsRecordBean;
import com.jinyuankeji.yxm.findhuo.my.adapter.BuyLotteryFragmentEAdapter;
import com.jinyuankeji.yxm.findhuo.my.adapter.NewsRecordAdapter;
import com.jinyuankeji.yxm.findhuo.my.seting.BuyLotteryFragmentEDetails;
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
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.orderList;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public class BuyLotteryFragmentE extends android.support.v4.app.Fragment {
    private BuyLotteryFragmentSBean bean;
    private BuyLotteryFragmentEAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private String Phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_buy_lottery_fragmnet_e, null);


        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_lottery);
        myAdapter = new BuyLotteryFragmentEAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                Intent it = new Intent(getActivity(), BuyLotteryFragmentEDetails.class);
                String order = bean.getData().get(position).getId_order();
                Contants.id_order = order;
                startActivity(it);
            }
        }));

        request();
        return view;

    }

    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account", Phone);
        params.addBodyParameter("status", "5");
        params.addBodyParameter("page", "1");
        params.addBodyParameter("size", "100");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + noDraft, params,
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
                            bean = gson.fromJson(json, BuyLotteryFragmentSBean.class);

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
