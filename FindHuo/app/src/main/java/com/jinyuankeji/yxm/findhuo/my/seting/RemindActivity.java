package com.jinyuankeji.yxm.findhuo.my.seting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.UserInfoBean;
import com.jinyuankeji.yxm.findhuo.bean.UserModifyBean;
import com.jinyuankeji.yxm.findhuo.image_util.ImageLoaderUtil;
import com.jinyuankeji.yxm.findhuo.image_util.Instance;
import com.jinyuankeji.yxm.findhuo.my.PersonalActivity;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.JsonUtils;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import static com.jinyuankeji.yxm.findhuo.tools.URLValue.userInfo;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.userModify;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class RemindActivity extends Activity{

    private UserInfoBean bean1;
    private UserModifyBean bean2;
    private String Phone;
    private ImageView img,back;
   // int flag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_remind);

        request2();


        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user",Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");


        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img = (ImageView) findViewById(R.id.img);
        request();
       // int i = Contants.ticket_push;
        if( Contants.ticket_push == 1){
            img.setImageResource(R.mipmap.selected_messege_up);
        }else {
            img.setImageResource(R.mipmap.normal_messege_down1);
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Contants.ticket_push == 0){
                    img.setImageResource(R.mipmap.selected_messege_up);
                    img.setVisibility(View.VISIBLE);
                    Contants.ticket_push = 1;
                    request2();

            }else {
                    img.setImageResource(R.mipmap.normal_messege_down1);
                    //img.setVisibility(View.GONE);
                    Contants.ticket_push = 0;
                    request2();
                }
            }
        });


    }

    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",Phone);
        // params.addBodyParameter("actionid","1");

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
                                    "222222222222222222 onSuccess"
                                            + arg0.result.toString());

                            //  getList(arg0.result.toString());// 请求返回的数据，json解析
                            String json = arg0.result.toString();
                            bean1 = JsonUtils.getJtoC(json, UserInfoBean.class);
                            String ticket_push_bean = bean1.getData().getTicket_push();
                            //name.setText(name_bean);
                            Contants.ticket_push = Integer.valueOf(ticket_push_bean).intValue();
                          //  Contants.ticket_push = flag;
                            Log.e("wwwwwwwwwwwwwwwwww",Contants.ticket_push + " ");


                        }

                    }

                });


    }
    //我的界面——》修改个人信息和修改彩票提醒
    private void request2(){

        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",Phone);
        params.addBodyParameter("ticket_push",Contants.ticket_push + "");

        Log.e("哈哈哈哈哈哈哈哈哈哈哈",Contants.ticket_push + " ");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + userModify, params,
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

                            Log.e("修改请求成功",
                                    "66666666666666666666 onSuccess"
                                            + arg0.result.toString());

                            //  getList(arg0.result.toString());// 请求返回的数据，json解析
                            String json = arg0.result.toString();
                            bean2 = JsonUtils.getJtoC(json, UserModifyBean.class);
                            //   Contants.account = etMainUserName.getText().toString().trim();

                        }

                    }

                });


    }

}
