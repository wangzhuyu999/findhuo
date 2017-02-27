package com.jinyuankeji.yxm.findhuo.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.jinyuankeji.yxm.findhuo.R;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public class BuyLotteryActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton rbtnFirst, rbtnCollect;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_buy_lottery);

        rbtnFirst = (RadioButton) findViewById(R.id.rbtn_first);
        rbtnCollect = (RadioButton) findViewById(R.id.rbtn_collect);
        image = (ImageView)findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        rbtnFirst.setOnClickListener(this);
        rbtnCollect.setOnClickListener(this);


        replaceFragment(R.id.frame_replaceFr,new BuyLotteryFragmentS());

        rbtnFirst.setTextColor(Color.parseColor("#58bbb8"));
        rbtnCollect.setTextColor(Color.rgb(255,255,255));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbtn_first:
                replaceFragment(R.id.frame_replaceFr,new BuyLotteryFragmentS());

                rbtnFirst.setTextColor(Color.parseColor("#58bbb8"));
                rbtnCollect.setTextColor(Color.rgb(255,255,255));


                break;
            case R.id.rbtn_collect:
                replaceFragment(R.id.frame_replaceFr,new BuyLotteryFragmentE());
                rbtnFirst.setTextColor(Color.rgb(255,255,255));
                rbtnCollect.setTextColor(Color.parseColor("#58bbb8"));


                break;


        }
    }

    public void replaceFragment(int id, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }

}

