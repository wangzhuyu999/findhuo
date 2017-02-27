package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.hot_type_detail_one;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.findwork.FindWorkDeclareNewBean;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.RefreshView;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one.SearchFindCarActivity;
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
import java.util.List;

/**
 * Created by  yxiaomin on 2016/12/21 0021.
 */

public class FindWorkHotTypeDetailActivity extends BaseActivity {
    private FindWorkHotTypeDetailAdapter mNewAdapter;
    private FinfWorkHotTypeDetailBean mNewBean;
    private FinfWorkHotTypeDetailBean mNewBeanLoade;
    private ListView mLv;
    private ImageView back;
    private RefreshView mSwipePull;
//    private AutoCompleteTextView autoCompleteTextView;
//    private ArrayList<String> aotoString;

    private TextView tvTitle;
    private ImageView ivSearch;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_hot_type_detail;
    }

    @Override
    protected void initView() {
        mLv = (ListView) findViewById(R.id.lv_findwork_hot_type_detail);
        back = (ImageView) findViewById(R.id.tv_findwork_hot_type_detail_back);
//        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_search_findwork_hot_type_detail);
        tvTitle = (TextView) findViewById(R.id.tv_findwork_hot_type_detail_title_name);
        mSwipePull = (RefreshView) findViewById(R.id.swip_jigong);
        ivSearch = (ImageView) findViewById(R.id.search_findwork_hot_type_detail);
    }

    @Override
    protected void initData() {
        tvTitle.setText(DataValue.FINDWORK_TYPE_TV);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNewAdapter = new FindWorkHotTypeDetailAdapter(this);
        request();
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("FindWorkHotTypeTaxiDeta", "mBean.getData().size():" + mNewBean.getData().size());
                Log.d("FindWorkHotTypeTaxiDeta", "position:" + position);
                Log.d("FindWorkHotTypeTaxiDeta", mNewBean.getData().get(position).getName());
                DataValue.FINDHUO_DETAIL_NOR = mNewBean.getData().get(position).getName();
//                if (DataValue.FINDWORK_TYPE_TV.equals("心理咨询") ){
//                    Intent intent = new Intent(FindWorkHotTypeDetailActivity.this, FindWorkHotTypePsychologicalDetailActivity.class);
//                    startActivity(intent);
//                }else {
                DataValue.FINDWORK_OR_FINDCAR_ID = mNewBean.getData().get(position).getId_introduce();
                Intent intent = new Intent(FindWorkHotTypeDetailActivity.this, FindWorkNewDetailActivity.class);
                startActivity(intent);

            }
        });


//        mSwipePull.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
//        mSwipePull.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);


        mSwipePull.setOnLoadListener(new RefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestLode();
                        if (mNewBean.getRes() == 10002) {
                            Toast.makeText(FindWorkHotTypeDetailActivity.this, "暂时没有更多了", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FindWorkHotTypeDetailActivity.this, "加载了" + mNewBean.getData().size() + "条数据", Toast.LENGTH_SHORT).show();
                        }
                        // 加载完数据设置为不加载状态，将加载进度收起来
                        mSwipePull.setLoading(false);
                    }
                }, 1200);
            }
        });
        mSwipePull.setEnabled(false);


        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindWorkHotTypeDetailActivity.this, SearchFindCarActivity.class);
                startActivity(intent);
            }
        });

    }

    //技工等列表
    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("id_icon", DataValue.FINDWORK_TYPE_ID);
        params.addBodyParameter("page", "1");
        params.addBodyParameter("city", DataValue.LOCATION);
        params.addBodyParameter("size", "10");
        Log.d("FindWorkHotTypeDetailAc", DataValue.FINDWORK_TYPE_ID);
        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_INDROCE_SOON_LIST + params);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_INDROCE_SOON_LIST, params,
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
                            mNewBean = gson.fromJson(json, FinfWorkHotTypeDetailBean.class);
                            if (mNewBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mNewBean.getRes() == 10001) {
                                mNewAdapter.setDatas(mNewBean);
                                mLv.setAdapter(mNewAdapter);

                            } else if (mNewBean.getRes() == 10002) {
                                Toast.makeText(FindWorkHotTypeDetailActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mNewBean.getRes() == 10000) {
                                Toast.makeText(FindWorkHotTypeDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }

    private void requestLode() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("id_icon", DataValue.FINDWORK_TYPE_ID);

        params.addBodyParameter("page", DataValue.JIGONG_REFSH_I + "");
        Log.d("FindWorkHotTypeDetailAc", DataValue.JIGONG_REFSH_I + "");
        params.addBodyParameter("city", DataValue.LOCATION);
        params.addBodyParameter("size", "5");
        Log.d("FindWorkHotTypeDetailAc", DataValue.FINDWORK_TYPE_ID);
        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_INDROCE_SOON_LIST + params);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_INDROCE_SOON_LIST, params,
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
                            mNewBeanLoade = gson.fromJson(json, FinfWorkHotTypeDetailBean.class);
                            if (mNewBeanLoade == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mNewBeanLoade.getRes() == 10001) {
                                mNewAdapter.addData(mNewBeanLoade);
                                mLv.setAdapter(mNewAdapter);

                            } else if (mNewBeanLoade.getRes() == 10002) {
//                                Toast.makeText(FindWorkHotTypeDetailActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mNewBeanLoade.getRes() == 10000) {
                                Toast.makeText(FindWorkHotTypeDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }
}
