package com.jinyuankeji.yxm.findhuo.base.base_chat;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;
<<<<<<< HEAD
import com.jinyuankeji.yxm.findhuo.LoginActivity;
=======
import com.jinyuankeji.yxm.findhuo.lottery.ui.LoginActivity;
>>>>>>> 2


public class MyConnectionListener implements EMConnectionListener {
    Activity context;

    @Override
    public void onConnected() {
    }

    public MyConnectionListener(Activity context) {
        this.context = context;
    }

    @Override
    public void onDisconnected(final int error) {
        context.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (error == EMError.USER_REMOVED) {
                    Toast.makeText(context, "帐号已经被移除", Toast.LENGTH_SHORT).show();
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    Toast.makeText(context, "帐号在其他设备登录,被迫下线", Toast.LENGTH_SHORT).show();
                    EMClient.getInstance().logout(true, new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            Log.e("main", "下线成功了");
                        }

                        @Override
                        public void onError(int i, String s) {
                            Log.e("main", "下线失败了！" + s);
                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });
                    context.startActivity(new Intent(context, LoginActivity.class));
                    context.finish();
                } else {
                    if (NetUtils.hasNetwork(context)) {

                    } else {
                        Toast.makeText(context, "当前网络不可用，请检查网络设置", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}