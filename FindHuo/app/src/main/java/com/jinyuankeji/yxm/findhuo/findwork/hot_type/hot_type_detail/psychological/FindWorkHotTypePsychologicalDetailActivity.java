package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.psychological;

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
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one.FindWorkHotTypeTaxiDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one.FindWorkHotTypeTaxiDetailLVBean;
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
public class FindWorkHotTypePsychologicalDetailActivity extends BaseActivity {
    private ImageView ivBack;
    private TextView tvTitle,tvName,tvSex,tvQQ,tvWeChat,tvDo,tvQuestion;
    private ImageView ivTel,ivHead;
    private PsychologistDetailBean mPsychologistDetailBean;



    @Override
    protected int initLayout() {
        return R.layout.activity_psychological_detail;
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_psychological_detail_back);
        tvTitle = (TextView) findViewById(R.id.tv_psychological_detail_title);
        ivTel = (ImageView) findViewById(R.id.tv_psychological_detail_tel);
        tvName = (TextView) findViewById(R.id.tv_psychological_detail_name);
        tvSex = (TextView) findViewById(R.id.tv_psychological_detail_sex);
        tvQQ = (TextView) findViewById(R.id.tv_psychological_detail_qq);
        tvWeChat = (TextView) findViewById(R.id.tv_psychological_detail_wechat);
        tvDo = (TextView) findViewById(R.id.tv_psychological_content);
        tvQuestion = (TextView) findViewById(R.id.tv_psychological_range);
        ivHead = (ImageView) findViewById(R.id.iv_psychological_head);

    }

    @Override
    protected void initData() {
        mPsychologistDetailBean = new PsychologistDetailBean();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText(DataValue.FINDHUO_DETAIL_NOR);

request();
        ivTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "15642838708"));
                startActivity(phoneCall);
//                number="";
            }
        });


    }

    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("id_psychologist","1");
        httpUtils.send(HttpRequest.HttpMethod.POST,URLValue.URL_NOR + URLValue.URL_PSYCHOLOGIST_DETAIL , params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("心理咨询详情请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mPsychologistDetailBean = gson.fromJson(json, PsychologistDetailBean.class);
                            if (mPsychologistDetailBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mPsychologistDetailBean.getRes() == 10001) {
                                tvName.setText(mPsychologistDetailBean.getData().getName());
                                tvSex.setText(mPsychologistDetailBean.getData().getSex());
                                tvQQ.setText(mPsychologistDetailBean.getData().getQq());
                                tvWeChat.setText(mPsychologistDetailBean.getData().getWeixin());
                                tvDo.setText(mPsychologistDetailBean.getData().getCompany());
                                tvQuestion.setText(mPsychologistDetailBean.getData().getGood_at());
                                Picasso.with(FindWorkHotTypePsychologicalDetailActivity.this).load(mPsychologistDetailBean.getData().getHeadimg()).into(ivHead);
                            } else if (mPsychologistDetailBean.getRes() == 10002) {
                                Toast.makeText(FindWorkHotTypePsychologicalDetailActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mPsychologistDetailBean.getRes() == 10000) {
                                Toast.makeText(FindWorkHotTypePsychologicalDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }
}
