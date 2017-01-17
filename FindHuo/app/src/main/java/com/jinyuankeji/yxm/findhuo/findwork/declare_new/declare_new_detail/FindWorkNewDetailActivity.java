package com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.findwork.FindWorkDeclareNewBean;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewAdapter;
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
 * Created by  yxiaomin on 2016/12/21 0021.
 */

public class FindWorkNewDetailActivity extends BaseActivity {
    private ImageView back;
    private ImageView ivTel, ivHead;
    private TextView tvTitleTop;
    private FindWorkNewDetailBean mBean;
    private TextView tvName, tvSex, tvPrice, tvTitle, tvAddr, tvContent;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_declare_new_detail;
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.findwork_declare_new_detail_back);
        ivTel = (ImageView) findViewById(R.id.iv_findwork_new_detail_tel_call);
        tvTitleTop = (TextView) findViewById(R.id.tv_findwork_declare_new_detail_title);

        tvName = (TextView) findViewById(R.id.tv_findwork_new_detail_name);
        tvSex = (TextView) findViewById(R.id.tv_findwork_new_detail_sex);
        tvPrice = (TextView) findViewById(R.id.tv_findwork_new_detail_se);
        tvTitle = (TextView) findViewById(R.id.tv_findwork_new_detail_content);
        tvAddr = (TextView) findViewById(R.id.tv_findwork_declare_new_detail_range);
        tvContent = (TextView) findViewById(R.id.tv_findwork_new_detail_work_content);
        ivHead = (ImageView) findViewById(R.id.iv_findwork_detail_new_detail_head);
    }

    @Override
    protected void initData() {
        mBean = new FindWorkNewDetailBean();
        tvTitleTop.setText(DataValue.FINDHUO_DETAIL_NOR);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + DataValue.JIGONG_DETAIL_TEL));
                startActivity(phoneCall);
//                number="";
            }
        });
        request();

    }


    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        final RequestParams params = new RequestParams();
        params.addBodyParameter("id_introduce", DataValue.FINDWORK_OR_FINDCAR_ID);
        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_LINGGONG_AND_FINDCAR_DETAIL);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_LINGGONG_AND_FINDCAR_DETAIL, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("请求成功xiangqing", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mBean = gson.fromJson(json, FindWorkNewDetailBean.class);
                            if (mBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mBean.getRes() == 10001) {
                                DataValue.FINDCAR_OR_BEAN = mBean;
                                tvName.setText(mBean.getData().getName());
                                tvSex.setText(mBean.getData().getSex());
                                tvPrice.setText(mBean.getData().getSalary());
                                tvTitle.setText(mBean.getData().getTitle());
                                tvAddr.setText(mBean.getData().getService_area());
                                tvContent.setText(mBean.getData().getContent());
                              DataValue.JIGONG_DETAIL_TEL = mBean.getData().getTel();

                                Picasso.with(FindWorkNewDetailActivity.this).load(mBean.getData().getHeadimg()).into(ivHead);
                            } else if (mBean.getRes() == 10002) {
                                Toast.makeText(FindWorkNewDetailActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mBean.getRes() == 10000) {
                                Toast.makeText(FindWorkNewDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }

}
