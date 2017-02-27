package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.Util.RecyclerItemClickListener;
import com.jinyuankeji.yxm.findhuo.bean.ReleaseBean;
import com.jinyuankeji.yxm.findhuo.my.adapter.ReleaseAdapter;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.introduceDel;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.myintroduceList;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class ReleaseActivity extends Activity{
    private ImageView back;
    private ReleaseAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private String Phone;
    private ReleaseBean bean;
    private Button ok,cancel;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_release);

        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user",Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_lottery);
        myAdapter = new ReleaseAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), "手机号格式不正确",
//                        Toast.LENGTH_SHORT).show();

                showExitGameAlert();

            }
        }));

        initView();
        request();
    }

    private void showExitGameAlert() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.exit_dialog);
        // 为确认按钮添加事件,执行退出应用操作
        ok = (Button) window.findViewById(R.id.exitBtn0);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//		Intent it = new Intent(SettingActivity.this,LoginActivity.class);
//		startActivity(it);
//		finish();
//                Intent intent = new Intent();
//                intent.setClass(SettingActivity.this, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置
//                startActivity(intent);
              //  finish();
                myAdapter.removeData(1);
                dlg.cancel();
                request2();
                request();

            }
        });

        // 关闭alert对话框架
        cancel = (Button) window.findViewById(R.id.exitBtn1);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlg.cancel();
            }
        });
    }

    private void request2() {

        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("id_introduce", Contants.id_introduce);

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + introduceDel, params,
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

                            Log.e("我的发布删除请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                        }
                    }
                });


    }



    private void request() {

        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account", Phone);
        params.addBodyParameter("page", "1");
        params.addBodyParameter("size", "100");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + myintroduceList, params,
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

                            Log.e("我的发布请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                            Gson gson = new Gson();
                            bean = gson.fromJson(json, ReleaseBean.class);

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
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
