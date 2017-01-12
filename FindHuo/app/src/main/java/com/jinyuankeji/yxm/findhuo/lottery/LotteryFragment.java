package com.jinyuankeji.yxm.findhuo.lottery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;

import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryDetailActivity;
import com.jinyuankeji.yxm.findhuo.lottery.more.MoreActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.JsonUtils;
import com.jinyuankeji.yxm.findhuo.tools.MyRVOnClickListener;
import com.jinyuankeji.yxm.findhuo.tools.SVR;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class LotteryFragment extends BaseFragment implements LocationSource, AMapLocationListener {
    //    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    private ViewPager viewPagerBanner;
    private SVR svAndRV;
    private TextView tvMore;
    private LinearLayout llTip;
    private LotteryViewPagerAdapter myAdapter;
    private ArrayList<LotteryViewPagerBean> images;
    private Handler handler;
    private boolean flag = true;
    private boolean mm = true;
    private ImageView[] tips;

    private LotteryStationRVAdapter rvAdapter;
    private LotteryStationBean stationBean;
    private ArrayList<LotteryStationBean> beanArrayList;
    private ScrollView scrollView;

    private MapView mMapView = null;
    private AMap aMap;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private LocationSource.OnLocationChangedListener mListener;
    private boolean isFirstLoc = true;

    private TextView tv;
    private String location = "";
    private TextView tvLocation;

    private LotteryViewPagerBean mBean;


    @Override
    protected int initLayout() {
        return R.layout.fragment_lottery;
    }

    @Override
    protected void initView() {
//        spinner = (Spinner) getView().findViewById(R.id.spinner_lottery);
        viewPagerBanner = (ViewPager) getView().findViewById(R.id.view_pager_lottery);
        svAndRV = (SVR) getView().findViewById(R.id.rv_lottery);
        tvMore = (TextView) getView().findViewById(R.id.tv_lottery_tv_more);
        llTip = (LinearLayout) getView().findViewById(R.id.ll_lottery);
        scrollView = (ScrollView) getView().findViewById(R.id.sv_lottery);
        tvLocation = (TextView) getView().findViewById(R.id.tv_lottery_location);

    }

    @Override
    protected void initData() {
        scrollView.scrollTo(0, 0);

        myAdapter = new LotteryViewPagerAdapter(getActivity());
        images = new ArrayList<>();

        request();

        initViewPager();

        rvAdapter = new LotteryStationRVAdapter(getActivity());
        beanArrayList = new ArrayList<>();
        svAndRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        LotteryStationBean bean = new LotteryStationBean();
        for (int i = 0; i < 50; i++) {
            bean.setAddr("大东区");
            bean.setNamr("张三");
            bean.setImg(R.mipmap.btn_pay_selected3x);
            beanArrayList.add(bean);
        }
        rvAdapter.setStationBeanList(beanArrayList);
        svAndRV.setAdapter(rvAdapter);
        rvAdapter.setMyRvListener(new MyRVOnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), LotteryDetailActivity.class);
                startActivity(intent);
            }
        });


        Log.d("卡卡卡卡卡", "tvMore:" + tvMore);
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "dianji", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MoreActivity.class);
                startActivity(intent);
            }
        });

        //获取地图控件引用
        mMapView = (MapView) getView().findViewById(R.id.map_view);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
//       mMapView.onCreate(savedInstanceState);


        if (aMap == null) {
            aMap = mMapView.getMap();
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);
        }

        mLocationClient = new AMapLocationClient(getActivity());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(false);
        mLocationOption.setWifiActiveScan(true);
        mLocationOption.setMockEnable(false);
        mLocationOption.setInterval(2000);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();


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


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                aMapLocation.getLocationType();
                aMapLocation.getLatitude();
                aMapLocation.getLongitude();
                aMapLocation.getAccuracy();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);
                aMapLocation.getAddress();
                aMapLocation.getCountry();
                aMapLocation.getProvince();
                aMapLocation.getCity();
                aMapLocation.getDistrict();
                aMapLocation.getStreet();
                aMapLocation.getStreetNum();
                aMapLocation.getCityCode();
                aMapLocation.getAdCode();

                if (isFirstLoc) {
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    mListener.onLocationChanged(aMapLocation);

                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
//                    Toast.makeText(getActivity(), buffer.toString(), Toast.LENGTH_LONG).show();

                    String location = aMapLocation.getCity().toString();
                    String tv = location.replace("市", "");
                    tvLocation.setText(tv);
//                    DataValue.LOCATION = aMapLocation.getCity();
                    DataValue.LOCATION = tv;
                    isFirstLoc = false;
                }
            } else {
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getActivity(), "获取失败。", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();

        mLocationClient.stopLocation();
        mLocationClient.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }


    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        // 发送请求数据
        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_LOTTERY_BANNER);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_LOTTERY_BANNER, params,
                new RequestCallBack<String>() {
                    // 请求接口失败 arg1 为后台返回的错误信息
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    // 请求接口成功 arg0.tostring 为后台返回的信息
                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {

//                        if (arg0.result.toString().equals("0")) {
//                        } else {
                        Log.e("ahhhh请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        //  getList(arg0.result.toString());// 请求返回的数据，json解析
                        String json = arg0.result.toString();
//                           mBean = JsonUtils.getJtoC(json, LotteryViewPagerBean.class);

                        Gson gson = new Gson();

                        images = gson.fromJson(json, new TypeToken<List<LotteryViewPagerBean>>() {
                        }.getType());
                        Log.d("LotteryFragment", "images:" + images);
                        if (images == null) {
                            Log.d("LotteryFragment", "实体类为null");
                        } else {
//                            images.add(mBean);
                            Log.d("DeclareActivity", images.get(0).getImgurl());

                            myAdapter.setImages(images);
                            myAdapter.setViewPager(viewPagerBanner);
                            viewPagerBanner.setAdapter(myAdapter);

                        }

                    }
//                    }
                });

    }
}
