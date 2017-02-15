package com.jinyuankeji.yxm.findhuo.lottery.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.jinyuankeji.yxm.findhuo.LoginActivity;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.base_chat.GlobalField;
import com.jinyuankeji.yxm.findhuo.base.base_chat.MyConnectionListener;


import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PersonFragment extends Fragment {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_person_username)
    TextView tvPersonUsername;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.inject(this, view);

        tvTitle.setText("个人");
        String userName = getActivity().getSharedPreferences(GlobalField.USERINFO_FILENAME, Context.MODE_PRIVATE).getString("username", "hdl");
        tvPersonUsername.setText(userName);
        EMClient.getInstance().addConnectionListener(new MyConnectionListener(getActivity()));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_person_add, R.id.btn_exit, R.id.btn_remove_friend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_person_add:

                break;
            case R.id.btn_remove_friend:
                removeFriend();
                break;
            case R.id.btn_exit:
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Log.e("main", "下线");
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.e("main", "下线失败！" + s);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });//下线
                break;
        }
    }


    private void removeFriend() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("删除");
        final EditText newFirendName = new EditText(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newFirendName.setLayoutParams(layoutParams);
        newFirendName.setHint("要删除");
        builder.setView(newFirendName);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("移除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread() {
                    @Override
                    public void run() {
                        String firendName = newFirendName.getText().toString().trim();
                        try {
                            EMClient.getInstance().contactManager().deleteContact(firendName);
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




}
