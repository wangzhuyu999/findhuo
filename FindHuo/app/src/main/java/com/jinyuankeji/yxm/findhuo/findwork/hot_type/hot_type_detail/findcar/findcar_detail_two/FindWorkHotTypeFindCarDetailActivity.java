package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_detail_two;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailBean;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.squareup.picasso.Picasso;

/**
 * Created by  yxiaomin on 2017/1/4 0004.
 */
public class FindWorkHotTypeFindCarDetailActivity extends BaseActivity {
    private ImageView ivBack;
    private ImageView ivCall, ivHead;
    private TextView tvTitle;
    private FindWorkNewDetailBean mDetailBean;
    private TextView tvName, tvStart, tvEnd, tvTime,tvTimeTV;
    private String tel;


    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_hot_type_findcar_detail;
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.tv_findcar_detail_back);
        ivCall = (ImageView) findViewById(R.id.iv_findcar_detail_cal);
        tvTitle = (TextView) findViewById(R.id.tv_findcar_detail_title_name);
        tvName = (TextView) findViewById(R.id.tv_findcar_name);
        tvTime = (TextView) findViewById(R.id.tv_findcar_detail_declare_time);
        tvEnd = (TextView) findViewById(R.id.tv_findcar_detail_end);
        tvStart = (TextView) findViewById(R.id.tv_findcar_detail_start);
        ivHead = (ImageView) findViewById(R.id.iv_findcar_detail_head);
        tvTimeTV = (TextView) findViewById(R.id.tv_findcar_detail_declare_time_tv);
    }

    @Override
    protected void initData() {
        mDetailBean = new FindWorkNewDetailBean();
        tvTitle.setText(DataValue.FINDHUO_DETAIL_NOR + "找车");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        request();
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + DataValue.FINDWORK_DETAIL_TEL));
                if (ActivityCompat.checkSelfPermission(FindWorkHotTypeFindCarDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(phoneCall);
//                number="";
            }
        });
    }

    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        final RequestParams params = new RequestParams();
        params.addBodyParameter("id_introduce", DataValue.FINDCAR_DETAIL_ID);
        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_LINGGONG_AND_FINDCAR_DETAIL);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_LINGGONG_AND_FINDCAR_DETAIL, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("请求成功zhaoche", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mDetailBean = gson.fromJson(json, FindWorkNewDetailBean.class);
                            if (mDetailBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mDetailBean.getRes() == 10001) {
                                tvName.setText(mDetailBean.getData().getName());
                                if (mDetailBean.getData().getAppointment_time().equals("0000-00-00 00:00:00")) {
                                    tvTime.setText(mDetailBean.getData().getCreate_time());
                                    tvTimeTV.setText("发布时间：");
                                } else {
                                    tvTime.setText( mDetailBean.getData().getAppointment_time());
                                    tvTimeTV.setText("预约时间：" );
                                }
                                tvStart.setText(mDetailBean.getData().getAddress());
                                tvEnd.setText(mDetailBean.getData().getAddress1());
                                DataValue.FINDWORK_DETAIL_TEL = mDetailBean.getData().getTel();
                                Picasso.with(FindWorkHotTypeFindCarDetailActivity.this).load(mDetailBean.getData().getHeadimg()).into(ivHead);
                            } else if (mDetailBean.getRes() == 10002) {
                                Toast.makeText(FindWorkHotTypeFindCarDetailActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mDetailBean.getRes() == 10000) {
                                Toast.makeText(FindWorkHotTypeFindCarDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }
}
