package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.plus.model.people.Person;
import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.LoginBean;
import com.jinyuankeji.yxm.findhuo.bean.MoneyBean;
import com.jinyuankeji.yxm.findhuo.bean.PersonalBean;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerAdapter;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerBean;
import com.jinyuankeji.yxm.findhuo.my.adapter.MoneyAdapter;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.JsonUtils;
import com.jinyuankeji.yxm.findhuo.tools.SVR;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.rechargeList;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.userInfo;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class MoneyActivity extends Activity{
    private ImageView back;
    private TextView money;
    private MoneyBean bean2;
    private PersonalBean bean;

    private MoneyAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private String Phone;
    private Button Recharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_money);
        initView();
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user",Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");

        Recharge = (Button)findViewById(R.id.Recharge);
        Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MoneyActivity.this,RechargeActivity.class);
                startActivity(it);
            }
        });

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_lottery);
        myAdapter = new MoneyAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        request();
        request2();
    }

    //充值记录
    private void request2() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account", Phone);
        params.addBodyParameter("page", "1");
        params.addBodyParameter("size", "100");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + rechargeList, params,
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

                            Log.e("充值记录请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                            Gson gson = new Gson();
                            bean2 = gson.fromJson(json, MoneyBean.class);

                            int res = bean2.getRes();

                            if(res == 10001){
                                myAdapter.setAllBeen(bean2);

                                mRecyclerView.setAdapter(myAdapter);

                            }else {

                            }
                        }
                    }
                });


    }

    private void initView() {
        back = (ImageView)findViewById(R.id.back);
        money = (TextView)findViewById(R.id.textView2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //我的界面、个人信息读取、消息提醒读取、账户余额
    //请求
    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account", Phone);

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + userInfo, params,
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

                            Log.e("请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();
                            bean = JsonUtils.getJtoC(json, PersonalBean.class);
                            int res = bean.getRes();

                            if(res == 10001){
                                String  amount = bean.getData().getAmount();
                                Contants.amount = amount;
                                money.setText(Contants.amount);

                            }else {

                            }



                        }




                    }

                });


    }

}
