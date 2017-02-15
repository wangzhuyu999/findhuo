package com.jinyuankeji.yxm.findhuo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.jinyuankeji.yxm.findhuo.declare.DeclareFragment;
import com.jinyuankeji.yxm.findhuo.findwork.FindWorkFragment;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryFragment;
import com.jinyuankeji.yxm.findhuo.my.MyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton rbtnLottery, rbtnFindWork, rbtnDeclare, rbtnMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        rbtnLottery = (RadioButton) findViewById(R.id.rbtn_lottery);
        rbtnFindWork = (RadioButton) findViewById(R.id.rbtn_findwork);
        rbtnDeclare = (RadioButton) findViewById(R.id.rbtn_declare);
        rbtnMy = (RadioButton) findViewById(R.id.rbtn_my);
    }

    private void initData() {
        rbtnLottery.setOnClickListener(this);
        rbtnFindWork.setOnClickListener(this);
        rbtnDeclare.setOnClickListener(this);
        rbtnMy.setOnClickListener(this);

        replaceFragment(new LotteryFragment());

        rbtnLottery.setTextColor(0xff58bbb8);
        rbtnFindWork.setTextColor(0xff58bbb8);
        rbtnDeclare.setTextColor(0xff58bbb8);
        rbtnMy.setTextColor(0xff58bbb8);

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_replace_fragment, fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbtn_lottery:
                replaceFragment(new LotteryFragment());
                rbtnLottery.setTextColor(0xff58bbb8);
                rbtnDeclare.setTextColor(0xff58bbb8);
                rbtnFindWork.setTextColor(0xff58bbb8);
                rbtnMy.setTextColor(0xff58bbb8);
                break;
            case R.id.rbtn_findwork:
                replaceFragment(new FindWorkFragment());
                rbtnLottery.setTextColor(0xff58bbb8);
                rbtnFindWork.setTextColor(0xff58bbb8);
                rbtnDeclare.setTextColor(0xff58bbb8);
                rbtnMy.setTextColor(0xff58bbb8);
                break;
            case R.id.rbtn_declare:
                replaceFragment(new DeclareFragment());
                rbtnLottery.setTextColor(0xff58bbb8);
                rbtnFindWork.setTextColor(0xff58bbb8);
                rbtnDeclare.setTextColor(0xff58bbb8);
                rbtnMy.setTextColor(0xff58bbb8);
                break;
            case R.id.rbtn_my:
                replaceFragment(new MyFragment());
                rbtnLottery.setTextColor(0xff58bbb8);
                rbtnFindWork.setTextColor(0xff58bbb8);
                rbtnDeclare.setTextColor(0xff58bbb8);
                rbtnMy.setTextColor(0xff58bbb8);
                break;

        }
    }
}
