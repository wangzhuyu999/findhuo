package com.jinyuankeji.yxm.findhuo.findwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewAdapter;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewBean;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_main_gv.FindWorkHotAdapter;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_main_gv.FindWorkHotBean;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.hot_type_detail_one.FindWorkHotTypeDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one.FindWorkHotTypeTaxiDetailActivity;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerAdapter;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.SVG;
import com.jinyuankeji.yxm.findhuo.tools.SVL;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class FindWorkFragment extends BaseFragment {
    //    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    private ViewPager viewPagerBanner;

    private LinearLayout llTip;
    private FindWorkeViewPagerAdapter myAdapter;
    private ArrayList<Integer> images;
    private Handler handler;
    private boolean flag = true;
    private boolean mm = true;
    private ImageView[] tips;

    private ScrollView scrollView;

    private FindWorkDeclareNewAdapter mNewAdapter;
    private FindWorkDeclareNewBean mNewBean;
    private List<FindWorkDeclareNewBean> mNewBeanList;
    private SVL mLv;

    private FindWorkHotAdapter mHotAdapter;
    private List<FindWorkHotBean> mHotBeanList;
    private SVG mGv;

    private int img[] = {R.mipmap.mechanic3x, R.mipmap.salesman3x, R.mipmap.employee3x, R.mipmap.teacher3x, R.mipmap.translator3x,
            R.mipmap.deliveryman3x, R.mipmap.taxi3x, R.mipmap.others3x,};
    private String str[] = {"技工", "促销导购", "钟点工", "家教", "翻译", "送货", "找车", "其他"};
    private String strFind[] = {"技工", "促销导购", "钟点工", "家教", "翻译", "送货", "心理咨询", "其他"};
    private int imgFind[] = {R.mipmap.mechanic3x, R.mipmap.salesman3x, R.mipmap.employee3x, R.mipmap.teacher3x, R.mipmap.translator3x,
            R.mipmap.deliveryman3x, R.mipmap.psychological3x, R.mipmap.others3x,};

    private TextView tvFindWork, tvDoWork;
    private TextView tvLocationFind;


    @Override
    protected int initLayout() {
        return R.layout.fragment_findwork;
    }

    @Override
    protected void initView() {
//        spinner = (Spinner) getView().findViewById(R.id.spinner_findwork);
        viewPagerBanner = (ViewPager) getView().findViewById(R.id.view_pager_findworke);
        llTip = (LinearLayout) getView().findViewById(R.id.ll_findworke);
        scrollView = (ScrollView) getView().findViewById(R.id.sv_findworke);
        mLv = (SVL) getView().findViewById(R.id.lv_findwrke_decalre_new);
        mGv = (SVG) getView().findViewById(R.id.gv_findworke_hot);
        tvFindWork = (TextView) getView().findViewById(R.id.tv_findwork_find);
        tvDoWork = (TextView) getView().findViewById(R.id.tv_findwork_do);
        tvLocationFind = (TextView) getView().findViewById(R.id.tv_findwork_location);

    }

    @Override
    protected void initData() {
        scrollView.scrollTo(0, 0);

        myAdapter = new FindWorkeViewPagerAdapter(getActivity());
        images = new ArrayList<>();
        images.add(0, R.mipmap.ic_launcher);
        images.add(1, R.mipmap.btn_pay_selected3x);
        images.add(2, R.mipmap.btn_pay_selected3x);
        initViewPager();


        mNewAdapter = new FindWorkDeclareNewAdapter(getActivity());
        mNewBeanList = new ArrayList<>();
        final FindWorkDeclareNewBean newBean = new FindWorkDeclareNewBean();
        for (int i = 0; i < 3; i++) {
            newBean.setRange("大东区");
            newBean.setName("张三");
            newBean.setJob("保洁");
            newBean.setPrice("1000");
            newBean.setImg(R.mipmap.btn_pay_selected3x);
            mNewBeanList.add(newBean);
        }
        mNewAdapter.setDatas(mNewBeanList);
        mLv.setAdapter(mNewAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataValue.FINDHUO_DETAIL_NOR = mNewBeanList.get(position).getName();
                Intent intent = new Intent(getActivity(), FindWorkNewDetailActivity.class);
                startActivity(intent);
            }
        });

        mHotAdapter = new FindWorkHotAdapter(getActivity());
        mHotBeanList = new ArrayList<>();
        FindWorkHotBean hotBean = new FindWorkHotBean();

//        for (int i = 0; i < str.length; i++) {
//            hotBean.setName("技工",0);
//            Log.d("FindWorkFragment", hotBean.getName());
//            hotBean.setImg(R.mipmap.mechanic3x,0);
        final List<Map<String, Object>> mHotBeanList = new ArrayList<Map<String, Object>>();


        for (int i = 0; i < str.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imgFind[i]);
            map.put("title", strFind[i]);

            mHotBeanList.add(map);
        }

        mHotAdapter.setListitem(mHotBeanList);
        mGv.setAdapter(mHotAdapter);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = mHotBeanList.get(position);
                DataValue.FINDWORK_TYPE_TV = map.get("title") + "";
                Intent intent = new Intent(getActivity(), FindWorkHotTypeDetailActivity.class);
                startActivity(intent);
            }
        });


        tvDoWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDoWork.setBackgroundColor(Color.WHITE);
                tvFindWork.setBackgroundResource(R.drawable.shap_findwork_select);
                tvDoWork.setTextColor(0xff58bbb8);
                tvFindWork.setTextColor(Color.WHITE);
                DataValue.FINDWORK_SELECT_TAG = "干零活";

                if (DataValue.FINDWORK_SELECT_TAG.equals("干零活")) {
                    mNewAdapter = new FindWorkDeclareNewAdapter(getActivity());
                    mNewBeanList = new ArrayList<>();
                    FindWorkDeclareNewBean newBean = new FindWorkDeclareNewBean();
                    for (int i = 0; i < 3; i++) {
                        newBean.setRange("大东区");
                        newBean.setName("张张六");
                        newBean.setJob("清洁工");
                        newBean.setPrice("1000");
                        newBean.setImg(R.mipmap.btn_pay_selected3x);
                        mNewBeanList.add(newBean);
                    }
                    mNewAdapter.setDatas(mNewBeanList);
                    mLv.setAdapter(mNewAdapter);
                    mNewAdapter.notifyDataSetChanged();
                    mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            DataValue.FINDHUO_DETAIL_NOR = mNewBeanList.get(position).getName();
                            Intent intent = new Intent(getActivity(), FindWorkNewDetailActivity.class);
                            startActivity(intent);
//                            }
                        }
                    });

                    mHotAdapter = new FindWorkHotAdapter(getActivity());
//                    mHotBeanList = new ArrayList<>();
                    FindWorkHotBean hotBean = new FindWorkHotBean();
                    final List<Map<String, Object>> mHotBeanList = new ArrayList<Map<String, Object>>();
                    // 将上述资源转化为list集合
                    for (int i = 0; i < str.length; i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("image", img[i]);
                        map.put("title", str[i]);

                        mHotBeanList.add(map);
                    }

                    mHotAdapter.setListitem(mHotBeanList);
                    mGv.setAdapter(mHotAdapter);
                    mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Map<String, Object> map = mHotBeanList.get(position);
                            DataValue.FINDWORK_TYPE_TV = map.get("title") + "";
                            if (DataValue.FINDWORK_TYPE_TV.equals("找车")) {
                                Intent intent = new Intent(getActivity(), FindWorkHotTypeTaxiDetailActivity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getActivity(), FindWorkHotTypeDetailActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        tvFindWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvFindWork.setBackgroundColor(Color.WHITE);
                tvDoWork.setBackgroundResource(R.drawable.shap_findwork_select);
                tvDoWork.setTextColor(Color.WHITE);
                tvFindWork.setTextColor(0xff58bbb8);
                DataValue.FINDWORK_SELECT_TAG = "找零工";

                if (DataValue.FINDWORK_SELECT_TAG.equals("找零工")) {
                    mNewAdapter = new FindWorkDeclareNewAdapter(getActivity());
                    mNewBeanList = new ArrayList<>();
                    FindWorkDeclareNewBean newBean = new FindWorkDeclareNewBean();
                    for (int i = 0; i < 3; i++) {
                        newBean.setRange("大东区");
                        newBean.setName("张三");
                        newBean.setJob("保洁");
                        newBean.setPrice("1000");
                        newBean.setImg(R.mipmap.btn_pay_selected3x);
                        mNewBeanList.add(newBean);
                    }
                    mNewAdapter.setDatas(mNewBeanList);
                    mLv.setAdapter(mNewAdapter);
                    mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            DataValue.FINDHUO_DETAIL_NOR = mNewBeanList.get(position).getName();
                            Intent intent = new Intent(getActivity(), FindWorkNewDetailActivity.class);
                            startActivity(intent);
                        }
                    });

                    mHotAdapter = new FindWorkHotAdapter(getActivity());
//                    mHotBeanList = new ArrayList<>();
                    FindWorkHotBean hotBean = new FindWorkHotBean();


                    final List<Map<String, Object>> mHotBeanList = new ArrayList<Map<String, Object>>();


                    for (int i = 0; i < str.length; i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("image", imgFind[i]);
                        map.put("title", strFind[i]);

                        mHotBeanList.add(map);
                    }

                    mHotAdapter.setListitem(mHotBeanList);
                    mGv.setAdapter(mHotAdapter);
                    mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Map<String, Object> map = mHotBeanList.get(position);
                            DataValue.FINDWORK_TYPE_TV = map.get("title") + "";
                            Intent intent = new Intent(getActivity(), FindWorkHotTypeDetailActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        tvLocationFind.setText(DataValue.LOCATION);
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

            ImageView imageView = new ImageView(getActivity());
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
