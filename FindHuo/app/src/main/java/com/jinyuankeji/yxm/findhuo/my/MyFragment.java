package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;
import com.jinyuankeji.yxm.findhuo.bean.PersonalBean;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.JsonUtils;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.userInfo;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class MyFragment extends BaseFragment {
    private ImageView text,seting;
    private RelativeLayout personal,money,new_record,Psychological,Release,buyLottery;
    private TextView money_text;
    private PersonalBean bean;
    private String Phone;


    private Button button6;

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;

    }

    @Override
    protected void initView() {

        //接单资质审核
        button6 = (Button)getView().findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),DriverActivity.class);
                startActivity(it);
            }
        });



        text = (ImageView)getView().findViewById(R.id.text1);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),NewsActivity.class);
                startActivity(it);
            }
        });
        seting = (ImageView)getView().findViewById(R.id.text3);
        seting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),SetingActivity.class);
                startActivity(it);
            }
        });
        personal = (RelativeLayout)getView().findViewById(R.id.personal);
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),PersonalActivity.class);
                startActivity(it);
            }
        });
        money = (RelativeLayout)getView().findViewById(R.id.money);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),MoneyActivity.class);
                startActivity(it);
            }
        });
        new_record = (RelativeLayout)getView().findViewById(R.id.new_record);
        new_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),NewsRecordActivity.class);
                startActivity(it);
            }
        });

        Release = (RelativeLayout)getView().findViewById(R.id.Release);
        Release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),ReleaseActivity.class);
                startActivity(it);
            }
        });
        buyLottery = (RelativeLayout)getView().findViewById(R.id.buyLottey);
        buyLottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),BuyLotteryActivity.class);
                startActivity(it);
            }
        });
        money_text = (TextView)getView().findViewById(R.id.textView13);




    }


    @Override

    protected void initData() {


        request();

    }


    //我的界面、个人信息读取、消息提醒读取、账户余额
    //请求
    private void request() {

        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");

        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",Phone);

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
                                money_text.setText(amount);
                            }else {

                            }



                        }

                    }

                });


    }


}
