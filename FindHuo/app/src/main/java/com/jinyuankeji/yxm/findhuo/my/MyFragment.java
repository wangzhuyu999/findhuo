package com.jinyuankeji.yxm.findhuo.my;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class MyFragment extends BaseFragment {
    private ImageView text,seting;
    private RelativeLayout personal,money,new_record,Psychological,Release,buyLottery;
    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        text = (ImageView)getView().findViewById(R.id.text1);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),NewsActivity.class);
                startActivity(it);
            }
        });
        seting = (ImageView)getView().findViewById(R.id.text3);
        seting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),SetingActivity.class);
                startActivity(it);
            }
        });
        personal = (RelativeLayout)getView().findViewById(R.id.personal);
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),PersonalActivity.class);
                startActivity(it);
            }
        });
        money = (RelativeLayout)getView().findViewById(R.id.money);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),MoneyActivity.class);
                startActivity(it);
            }
        });
        new_record = (RelativeLayout)getView().findViewById(R.id.new_record);
        new_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),NewsRecordActivity.class);
                startActivity(it);
            }
        });
        Psychological = (RelativeLayout)getView().findViewById(R.id.Psychological);
        Psychological.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),PsychologicalActivity.class);
                startActivity(it);
            }
        });
        Release = (RelativeLayout)getView().findViewById(R.id.Release);
        Release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),ReleaseActivity.class);
                startActivity(it);
            }
        });
        buyLottery = (RelativeLayout)getView().findViewById(R.id.buyLottey);
        buyLottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),BuyLotteryActivity.class);
                startActivity(it);
            }
        });
    }


    @Override

    protected void initData() {

    }
}
