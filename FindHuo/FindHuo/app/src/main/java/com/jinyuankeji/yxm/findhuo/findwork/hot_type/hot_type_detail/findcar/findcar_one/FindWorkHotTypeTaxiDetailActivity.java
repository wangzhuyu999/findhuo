package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.declare.FindCarActivity;
import com.jinyuankeji.yxm.findhuo.declare.PsychologicalDivisionActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.RefreshView;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_detail_two.FindWorkHotTypeFindCarDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.hot_type_detail_one.FindWorkHotTypeDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.psychological.FindWorkHotTypePsychologicalDetailActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.SVL;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;


/**
 * Created by  yxiaomin on 2017/1/4 0004.
 */
public class FindWorkHotTypeTaxiDetailActivity extends BaseActivity {
    private ViewPager viewPagerBanner;

    private LinearLayout llTip;
    private FindWorkHotTypeTaxiViewPagerAdapter myAdapter;
    private ArrayList<Integer> images;
    private Handler handler;
    private boolean flag = true;
    private boolean mm = true;
    private ImageView[] tips;

    private ListView lv;
    private FindWorkHotTypeTaxiDetailLVAdapter mAdapter;
    private FindWorkHotTypeTaxiDetailLVBean mBean;
    private FindWorkHotTypeTaxiDetailLVBean mBeanLoade;
    private TaxiViewPagerBean mViewPagerBean;


    private ImageView back;
    private TextView tvTitle;

    //    private Runnable runnable = new Runnable() {
//
//        @Override
//        public void run() {
//            mSwipePull.scrollTo(0, 50);
//        }
//    };
//    private Handler mHandlerh;
    private ImageView ivSearch;
    private RefreshView mRefreshView;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_hot_type_taxi_detail;
    }

    @Override
    protected void initView() {
        llTip = (LinearLayout) findViewById(R.id.ll_findworke_hot_type_taxi_detail);
        lv = (ListView) findViewById(R.id.lv_findwork_hot_type_taxi_detail);
        back = (ImageView) findViewById(R.id.tv_findwork_hot_type_taxi_detail_back);
        tvTitle = (TextView) findViewById(R.id.tv_findwork_hot_type_taxi_detail_title_name);
        ivSearch = (ImageView) findViewById(R.id.search_findwork_hot_type_detail);
        mRefreshView = (RefreshView) findViewById(R.id.swip_findcar);
    }

    @Override
    protected void initData() {
//        mHandlerh = new Handler();
//        mHandlerh.postDelayed(runnable, 50);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (DataValue.FINDWORK_TYPE_TV_OR.equals("找车")) {
            tvTitle.setText("找车");
        }
        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
            tvTitle.setText("心理咨询");
        }

        myAdapter = new FindWorkHotTypeTaxiViewPagerAdapter(this);
        mAdapter = new FindWorkHotTypeTaxiDetailLVAdapter(this);
        mBean = new FindWorkHotTypeTaxiDetailLVBean();
        images = new ArrayList<>();
        View view = getLayoutInflater().inflate(R.layout.findcar_list_viewpager, lv, false);
        lv.addHeaderView(view);
        viewPagerBanner = (ViewPager) view.findViewById(R.id.view_pager_findworke_hot_type_taxi_detail);
        requestBanner();
        initViewPager();

        request();
        requestDriver();
        requestPsychologist();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                          //找车页面详情上面人名
                                          DataValue.FINDHUO_DETAIL_NOR = mBean.getData().get(position - 1).getName();

                                          //找车id
                                          if (DataValue.FINDWORK_TYPE_TV.equals("找车")) {
                                              if (mDriverOrNotBean == null) {
                                                  Log.d("LotteryFragment", "实体类为null");
                                              } else if (mDriverOrNotBean.getRes() == 10001) {

                                              DataValue.FINDCAR_DETAIL_ID = mBean.getData().get(position - 1).getId_introduce();
                                              Intent intent = new Intent(FindWorkHotTypeTaxiDetailActivity.this, FindWorkHotTypeFindCarDetailActivity.class);
                                              startActivity(intent);

                                              } else if (mDriverOrNotBean.getRes() == 10002) {
                                                  Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "司机申请正在审核中", Toast.LENGTH_SHORT).show();
                                              } else if (mDriverOrNotBean.getRes() == 10000) {
                                                  Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                                              } else if (mDriverOrNotBean.getRes() == 10003) {

//                                                  Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "没有跳转还", Toast.LENGTH_SHORT).show();

                                                  Intent intent = new Intent(FindWorkHotTypeTaxiDetailActivity.this, FindCarActivity.class);
                                                  startActivity(intent);

                                              } else if (mDriverOrNotBean.getRes() == 10004) {
                                                  Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "此账号不存在", Toast.LENGTH_SHORT).show();
                                              }



                                          } else if (DataValue.FINDWORK_TYPE_TV.equals("心理咨询")) {
                                              if (mPsychologistOrNotBean == null) {
                                                  Log.d("LotteryFragment", "实体类为null");
                                              } else if (mPsychologistOrNotBean.getRes() == 10001) {

                                                  DataValue.PSYCHOLOGIST_DETAIL_ID = mBean.getData().get(position - 1).getId_psychologist();
                                                  Intent intent = new Intent(FindWorkHotTypeTaxiDetailActivity.this, FindWorkHotTypePsychologicalDetailActivity.class);
                                                  startActivity(intent);

                                              } else if (mPsychologistOrNotBean.getRes() == 10002) {
                                                  Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "心理师申请正在审核中", Toast.LENGTH_SHORT).show();
                                              } else if (mPsychologistOrNotBean.getRes() == 10000) {
                                                  Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                                              } else if (mPsychologistOrNotBean.getRes() == 10003) {
//                                                  Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "没有跳转还", Toast.LENGTH_SHORT).show();
                                                  Intent intent = new Intent(FindWorkHotTypeTaxiDetailActivity.this, PsychologicalDivisionActivity.class);
                                                  startActivity(intent);

                                              } else if (mPsychologistOrNotBean.getRes() == 10004) {
                                                  Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "此账号不存在", Toast.LENGTH_SHORT).show();
                                              }





                                          }

                                      }
                                  }
        );

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindWorkHotTypeTaxiDetailActivity.this, SearchFindCarActivity.class);
                startActivity(intent);
            }
        });

        mRefreshView.setOnLoadListener(new RefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestLode();
//                        if (mBean.getRes() == 10002) {
//                            Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "没有更多了", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "加载了" + mBean.getData().size() + "条数据", Toast.LENGTH_SHORT).show();
//                        }

                        mAdapter.notifyDataSetChanged();
//                        lv.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                lv.smoothScrollToPosition(mInt);
//                            }
//                        });
                        mRefreshView.setLoading(false);
                    }
                }, 1200);
            }
        });

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

    String Url;

    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();

        params.addBodyParameter("page", "1");
        params.addBodyParameter("size", "10");
        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
            Url = URLValue.URL_NOR + URLValue.URL_PSYCHOLOGIST_LIST;
        } else {
            Url = URLValue.URL_NOR + URLValue.URL_FINDCAR_LIST;
        }
        Log.d("tttttttt", Url);
        httpUtils.send(HttpRequest.HttpMethod.POST, Url, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
                            Log.e("心理咨询列表请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        } else {
                            Log.e("a找车列表请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        }
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mBean = gson.fromJson(json, FindWorkHotTypeTaxiDetailLVBean.class);
                            if (mBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mBean.getRes() == 10001) {
                                mAdapter.setStationBean(mBean);
                                lv.setAdapter(mAdapter);

                            } else if (mBean.getRes() == 10002) {
                                Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mBean.getRes() == 10000) {
                                Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    //banner
    private void requestBanner() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
            params.addBodyParameter("section", "4");
        } else {
            params.addBodyParameter("section", "5");
        }
        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_PSYCHOLOGIST_AND_FINGCAR_BANNER);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_PSYCHOLOGIST_AND_FINGCAR_BANNER, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
                            Log.e("心理咨询列表Banner请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        } else {
                            Log.e("a找车列表Banner请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        }
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mViewPagerBean = gson.fromJson(json, TaxiViewPagerBean.class);
                            if (mViewPagerBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mViewPagerBean.getRes() == 10001) {
                                myAdapter.setImages(mViewPagerBean);
                                myAdapter.setViewPager(viewPagerBanner);
                                viewPagerBanner.setAdapter(myAdapter);
                            } else if (mViewPagerBean.getRes() == 10002) {
                                Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "暂时活动无数据", Toast.LENGTH_SHORT).show();
                            } else if (mViewPagerBean.getRes() == 1000) {
                                Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private int mInt;
    private void requestLode() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();

        params.addBodyParameter("page", DataValue.ZHAOCHE_REFSH_I + "");
        params.addBodyParameter("size", "5");

        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
            Url = URLValue.URL_NOR + URLValue.URL_PSYCHOLOGIST_LIST;
        } else {
            Url = URLValue.URL_NOR + URLValue.URL_FINDCAR_LIST;
        }
        Log.d("FindWorkHotTypeDetailAc", DataValue.FINDWORK_TYPE_ID);

        httpUtils.send(HttpRequest.HttpMethod.POST, Url, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("请求成功技工", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mBeanLoade = gson.fromJson(json, FindWorkHotTypeTaxiDetailLVBean.class);

                            if (mBeanLoade == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mBeanLoade.getRes() == 10001) {
                                mAdapter.addData(mBeanLoade);
                                lv.setAdapter(mAdapter);

                                for (int i = 0; i < mBeanLoade.getData().size(); i++) {
                                    Log.d("FindWorkHotTypeTaxiDeta", "mBean:" + mBeanLoade.getData().get(i).getName());
                                }
                                mInt = mBean.getData().size() + mBeanLoade.getData().size();
                            } else if (mBeanLoade.getRes() == 10002) {
                                Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mBeanLoade.getRes() == 10000) {
                                Toast.makeText(FindWorkHotTypeTaxiDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

private DriverOrNotBean mDriverOrNotBean;
    public void requestDriver() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();

        params.addBodyParameter("account", DataValue.DRIVER_YES_OR_NOT_TEL);

        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_DRIVER_OR_NOT, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                            Log.e("a找车司机判断列表请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mDriverOrNotBean = gson.fromJson(json, DriverOrNotBean.class);
                        }
                    }
                });
    }

    private PsychologistOrNotBean mPsychologistOrNotBean;
    public void  requestPsychologist(){
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("account", DataValue.DRIVER_YES_OR_NOT_TEL);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_PSYCHOLOGIST_OR_NOT, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("a找车心理师判断列表请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mPsychologistOrNotBean = gson.fromJson(json, PsychologistOrNotBean.class);
                        }
                    }
                });
    }
}
