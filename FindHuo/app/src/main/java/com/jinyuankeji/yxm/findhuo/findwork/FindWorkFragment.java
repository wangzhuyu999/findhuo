package com.jinyuankeji.yxm.findhuo.findwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewAdapter;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_main_gv.FindWorkHotAdapter;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_main_gv.FindWorkHotBean;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.hot_type_detail_one.FindWorkHotTypeDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one.FindWorkHotTypeTaxiDetailActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.SVG;
import com.jinyuankeji.yxm.findhuo.tools.SVL;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class FindWorkFragment extends BaseFragment {
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

    private SVL mLv;
    private FindWorkHotAdapter mHotAdapter;
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
        mNewBean = new FindWorkDeclareNewBean();
        myAdapter = new FindWorkeViewPagerAdapter(getActivity());
        images = new ArrayList<>();
        requestFind();
        initViewPager();
        mNewAdapter = new FindWorkDeclareNewAdapter(getActivity());
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataValue.FINDHUO_DETAIL_NOR = mNewBean.getIntroduce().get(position).getName();
                DataValue.FINDWORK_OR_FINDCAR_ID = mNewBean.getIntroduce().get(position).getId_introduce();
                Intent intent = new Intent(getActivity(), FindWorkNewDetailActivity.class);
                startActivity(intent);
            }
        });

        mHotAdapter = new FindWorkHotAdapter(getActivity());
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
                DataValue.FINDWORK_TYPE_ID = mNewBean.getIcon().get(position).getId_icon();
                DataValue.FINDWORK_TYPE_TV = map.get("title") + "";
                if (DataValue.FINDWORK_TYPE_TV.equals("心理咨询")) {
                    Intent intent = new Intent(getActivity(), FindWorkHotTypeTaxiDetailActivity.class);
                    DataValue.FINDWORK_TYPE_TV_OR = "心理咨询";
                    startActivity(intent);
                } else {
                    DataValue.FINDWORK_TYPE_TV_OR = DataValue.FINDWORK_TYPE_TV;
                    Intent intent = new Intent(getActivity(), FindWorkHotTypeDetailActivity.class);
                    startActivity(intent);
                }
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
                    requestDo();
                    initViewPager();
                    mNewAdapter.notifyDataSetChanged();
                    mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DataValue.FINDHUO_DETAIL_NOR = mNewBean.getIntroduce().get(position).getName();
                            DataValue.FINDWORK_OR_FINDCAR_ID = mNewBean.getIntroduce().get(position).getId_introduce();
                            Intent intent = new Intent(getActivity(), FindWorkNewDetailActivity.class);
                            startActivity(intent);
//                            }
                        }
                    });

                    mHotAdapter = new FindWorkHotAdapter(getActivity());
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
                            DataValue.FINDWORK_TYPE_ID = mNewBean.getIcon().get(position).getId_icon();
                            Map<String, Object> map = mHotBeanList.get(position);
                            DataValue.FINDWORK_TYPE_TV = map.get("title") + "";
                            if (DataValue.FINDWORK_TYPE_TV.equals("找车")) {
                                Intent intent = new Intent(getActivity(), FindWorkHotTypeTaxiDetailActivity.class);
                                DataValue.FINDWORK_TYPE_TV_OR = "找车";
                                startActivity(intent);
                            } else {
                                DataValue.FINDWORK_TYPE_TV_OR = DataValue.FINDWORK_TYPE_TV;
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
                    mNewAdapter.setDatas(DataValue.FINDWORK_FINDHUO_MAINPAGE);
                    mLv.setAdapter(mNewAdapter);
                    mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DataValue.FINDHUO_DETAIL_NOR = mNewBean.getIntroduce().get(position).getName();
                            DataValue.FINDWORK_OR_FINDCAR_ID = mNewBean.getIntroduce().get(position).getId_introduce();
                            Intent intent = new Intent(getActivity(), FindWorkNewDetailActivity.class);
                            startActivity(intent);
                        }
                    });

                    mHotAdapter = new FindWorkHotAdapter(getActivity());
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
                            DataValue.FINDWORK_TYPE_ID = mNewBean.getIcon().get(position).getId_icon();
                            DataValue.FINDWORK_TYPE_TV = map.get("title") + "";
                            if (DataValue.FINDWORK_TYPE_TV.equals("心理咨询")) {
                                Intent intent = new Intent(getActivity(), FindWorkHotTypeTaxiDetailActivity.class);
                                DataValue.FINDWORK_TYPE_TV_OR = "心理咨询";
                                startActivity(intent);
                            } else {
                                DataValue.FINDWORK_TYPE_TV_OR = DataValue.FINDWORK_TYPE_TV;
                                Intent intent = new Intent(getActivity(), FindWorkHotTypeDetailActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        tvLocationFind.setText(DataValue.LOCATION);
    }


    private void initViewPager() {
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

    //我要找零工
    private void requestFind() {
        HttpUtils httpUtils = new HttpUtils();

        RequestParams params = new RequestParams();
        params.addBodyParameter("section", "3");
        params.addBodyParameter("type", "1");
        params.addBodyParameter("city", DataValue.LOCATION);
        params.addBodyParameter("size", "2");
        params.addBodyParameter("icontype", "3");

        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_FINDWORK + params);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_FINDWORK, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("请求成功找零工", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mNewBean = gson.fromJson(json, FindWorkDeclareNewBean.class);
                            if (mNewBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mNewBean.getRes() == 10001) {

                                Log.d("DeclareActivity", mNewBean.getSlider().get(0).getImgurl());
                                DataValue.FINDWORK_FINDHUO_MAINPAGE = mNewBean;
                                myAdapter.setImages(mNewBean);
                                myAdapter.setViewPager(viewPagerBanner);
                                viewPagerBanner.setAdapter(myAdapter);
                                mNewAdapter.setDatas(mNewBean);
                                mLv.setAdapter(mNewAdapter);

                            } else if (mNewBean.getRes() == 10002) {
                                Toast.makeText(getActivity(), "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mNewBean.getRes() == 10000) {
                                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                            } else if (mNewBean.getRes() == 10003) {
                                Toast.makeText(getActivity(), "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mNewBean.getRes() == 10004) {
                                Toast.makeText(getActivity(), "行业分类暂时无数据", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }


    //我要干零活
    private void requestDo() {
        HttpUtils httpUtils = new HttpUtils();

        final RequestParams params = new RequestParams();
        params.addBodyParameter("section", "2");
        params.addBodyParameter("type", "2");
        params.addBodyParameter("city", DataValue.LOCATION);
        params.addBodyParameter("size", "2");
        params.addBodyParameter("icontype", "2");

        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_FINDWORK + params);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_FINDWORK, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("请求成功找零工", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mNewBean = gson.fromJson(json, FindWorkDeclareNewBean.class);
                            if (mNewBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mNewBean.getRes() == 10001) {
                                Log.d("DeclareActivity", mNewBean.getSlider().get(0).getImgurl());
                                DataValue.FINDWORK_FINDHUO_MAINPAGE = mNewBean;

                                myAdapter.setImages(mNewBean);
                                myAdapter.setViewPager(viewPagerBanner);
                                viewPagerBanner.setAdapter(myAdapter);
                                mNewAdapter.setDatas(mNewBean);
                                mLv.setAdapter(mNewAdapter);
                            } else if (mNewBean.getRes() == 10002) {
                                Toast.makeText(getActivity(), "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mNewBean.getRes() == 10000) {
                                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                            } else if (mNewBean.getRes() == 10003) {
                                Toast.makeText(getActivity(), "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mNewBean.getRes() == 10004) {
                                Toast.makeText(getActivity(), "行业分类暂时无数据", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
