package com.jinyuankeji.yxm.findhuo.lottery.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.jinyuankeji.yxm.findhuo.LoginActivity;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerBean;
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
 * Created by  yxiaomin on 2016/12/20 0020.
 */
public class LotteryDetailActivity extends BaseActivity {
    private RelativeLayout rvPay;
    private ImageView back;
    private TextView tvChat;
    private Button btnExit;
    private LotteryDetailBean mBean;
    private TextView tvName,tvAddr,tvTel,tvHeBug,tvPrice,tvJindu,tvContent;
private ImageView ivImg;

    @Override
    protected int initLayout() {
        return R.layout.activity_lottery_detail;
    }

    @Override
    protected void initView() {
        rvPay = (RelativeLayout) findViewById(R.id.rv_lottery_detail_pay);
        back = (ImageView) findViewById(R.id.lottery_detail_more_back);
        tvChat = (TextView) findViewById(R.id.iv_lottery_detail_chat_icon);
        btnExit = (Button) findViewById(R.id.exit);

        tvName = (TextView) findViewById(R.id.tv_lottery_detail_station_name);
        tvAddr = (TextView) findViewById(R.id.tv_lottery_detail_station_addr);
        tvTel = (TextView) findViewById(R.id.tv_lottery_detail_station_tel);
        tvHeBug = (TextView) findViewById(R.id.tv_lottery_detail_station_priceall);
        tvPrice = (TextView) findViewById(R.id.tv_lottery_detail_station_price_oneperson);
        tvJindu = (TextView) findViewById(R.id.tv_lottery_detail_station_price_step);
        tvContent = (TextView) findViewById(R.id.tv_lottery_detail_station_football);
        ivImg = (ImageView) findViewById(R.id.iv_lottery_detail_head);
    }

   private String firendName;
    @Override
    protected void initData() {
        rvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LotteryDetailActivity.this, PayExtraActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend();

            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().logout (true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Log.e("main", "下线成功了");
                        startActivity(new Intent(LotteryDetailActivity.this, LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.e("main", "下线失败了！" + s);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });//下线
            }
        });

        mBean = new LotteryDetailBean();
        request();

    }


    private void addFriend() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加好友");
        final EditText newFirendName = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newFirendName.setLayoutParams(layoutParams);
        newFirendName.setHint("新好友用户名");
        builder.setView(newFirendName);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread() {
                    @Override
                    public void run() {
                        firendName = newFirendName.getText().toString().trim();
                        try {
                            EMClient.getInstance().contactManager().addContact(firendName, "我是你的朋友");
                            Log.e("","添加好友成功,等待回应:" + firendName);

                            EMClient.getInstance().chatManager().saveMessage(EMMessage.createTxtSendMessage("已添加",firendName));
                            DataValue.FINDHUO_CHAT = "添加";
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(LotteryDetailActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        Log.d("LotteryDetailActivity", DataValue.LOTTERY_MAIN_ID);
        params.addBodyParameter("id_lottery", DataValue.LOTTERY_MAIN_ID);
//        params.addBodyParameter("lottery", DataValue.LOCATION);

        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_LOTTERY_DEYAIL);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_LOTTERY_DEYAIL, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("ahhhh请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mBean = gson.fromJson(json, LotteryDetailBean.class);
                            if (mBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mBean.getRes() == 10001){
                                tvName.setText(mBean.getData().getLotteryname());
                                tvAddr.setText(mBean.getData().getAddress());
                                tvTel.setText(mBean.getData().getTel());
                                tvHeBug.setText(mBean.getData().getSummary1());
                                tvPrice.setText(mBean.getData().getSummary2());
                                tvJindu.setText(mBean.getData().getSummary3());
                                tvContent.setText(mBean.getData().getContent());
                                Picasso.with(LotteryDetailActivity.this).load(mBean.getData().getImg()).into(ivImg);

                            }else if (mBean.getRes() == 10002){
                                Toast.makeText(LotteryDetailActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            }else if (mBean.getRes() == 10000){
                                Toast.makeText(LotteryDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
