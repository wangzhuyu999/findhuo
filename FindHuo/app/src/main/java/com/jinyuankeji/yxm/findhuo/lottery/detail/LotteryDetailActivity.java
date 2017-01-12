package com.jinyuankeji.yxm.findhuo.lottery.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.lottery.ui.LoginActivity;

import java.util.Map;

import butterknife.ButterKnife;


/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */
public class LotteryDetailActivity extends BaseActivity {
    private RelativeLayout rvPay;
    private ImageView back;
    private TextView tvChat;
    private Button btnExit;


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
}
