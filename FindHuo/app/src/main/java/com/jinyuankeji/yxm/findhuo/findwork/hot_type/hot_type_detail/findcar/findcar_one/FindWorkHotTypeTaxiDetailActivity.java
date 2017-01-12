package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.findwork.FindWorkeViewPagerAdapter;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_detail_two.FindWorkHotTypeFindCarDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.psychological.FindWorkHotTypePsychologicalDetailActivity;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerAdapter;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.SVL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/4 0004.
 */
public class FindWorkHotTypeTaxiDetailActivity extends BaseActivity {
    private ViewPager viewPagerBanner;

    private LinearLayout llTip;
    private FindWorkeViewPagerAdapter myAdapter;
    private ArrayList<Integer> images;
    private Handler handler;
    private boolean flag = true;
    private boolean mm = true;
    private ImageView[] tips;

    private SVL lv;
    private FindWorkHotTypeTaxiDetailLVAdapter mAdapter;
    private List<FindWorkHotTypeTaxiDetailLVBean> mBean;

    private ImageView back;
    private ScrollView mScrollView;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_hot_type_taxi_detail;
    }

    @Override
    protected void initView() {
        viewPagerBanner = (ViewPager) findViewById(R.id.view_pager_findworke_hot_type_taxi_detail);
        llTip = (LinearLayout) findViewById(R.id.ll_findworke_hot_type_taxi_detail);
        lv = (SVL) findViewById(R.id.lv_findwork_hot_type_taxi_detail);
        back = (ImageView) findViewById(R.id.tv_findwork_hot_type_taxi_detail_back);
        mScrollView = (ScrollView) findViewById(R.id.sv_findcar);
    }

    @Override
    protected void initData() {
        mScrollView.scrollTo(0, 0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myAdapter = new FindWorkeViewPagerAdapter(this);
        images = new ArrayList<>();
        images.add(0, R.mipmap.ic_launcher);
        images.add(1, R.mipmap.btn_pay_selected3x);
        images.add(2, R.mipmap.btn_pay_selected3x);
        initViewPager();

        mAdapter = new FindWorkHotTypeTaxiDetailLVAdapter(this);
        mBean = new ArrayList<>();
        FindWorkHotTypeTaxiDetailLVBean newBean = new FindWorkHotTypeTaxiDetailLVBean();
        for (int i = 0; i < 50; i++) {
            newBean.setAddr("大东区");
            newBean.setName("张三");
            newBean.setTime("9月30日 11:30");
            mBean.add(newBean);
        }
        mAdapter.setDatas(mBean);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                         DataValue.FINDHUO_DETAIL_NOR = mBean.get(position).getName();
                                          Intent intent = new Intent(FindWorkHotTypeTaxiDetailActivity.this, FindWorkHotTypeFindCarDetailActivity.class);
                                          startActivity(intent);

                                      }
                                  }
        );

    }

    private void initViewPager() {
        myAdapter.setImages(images);
        myAdapter.setViewPager(viewPagerBanner);
        viewPagerBanner.setAdapter(myAdapter);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                viewPagerBanner.setCurrentItem(viewPagerBanner.getCurrentItem() + 1);
                return false;
            }
        });
        if (mm) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (flag) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                    }
                }
            }).start();
            mm = false;
        }
        tips = new ImageView[images.size()];
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                imageView.setImageResource(R.mipmap.fri3x);
            } else {
                imageView.setImageResource(R.mipmap.sec3x);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            layoutParams.leftMargin = 7;
            layoutParams.rightMargin = 7;
            llTip.addView(imageView, layoutParams);
        }
        myAdapter.setTips(tips);
    }
}
