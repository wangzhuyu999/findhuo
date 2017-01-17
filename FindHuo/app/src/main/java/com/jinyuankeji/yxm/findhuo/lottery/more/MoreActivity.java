package com.jinyuankeji.yxm.findhuo.lottery.more;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerBean;
import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryDetailActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */
public class MoreActivity extends BaseActivity {
    private ListView mListView;
    private LotteryMoreAdapter mMoreAdapter;
    private ImageView back;

    private AutoCompleteTextView autoCompleteTextView;
    private ArrayList<String> aotoString;
    private MoreBean mBean;
    private LotterySearchBean mSearchBean;
    String[] str = {};
    private List<String> list;

    @Override
    protected int initLayout() {
        return R.layout.activity_lottery_more;
    }

    @Override
    protected void initView() {
        mListView = (ListView) findViewById(R.id.lv_lottery_more);
        back = (ImageView) findViewById(R.id.lottery_more_back);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_search_lottery_more);
    }

    @Override
    protected void initData() {
        mMoreAdapter = new LotteryMoreAdapter(this);
        mBean = new MoreBean();
        mSearchBean = new LotterySearchBean();
        request();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MoreActivity.this, LotteryDetailActivity.class);
                DataValue.LOTTERY_MAIN_ID = mBean.getData().get(position).getId_lottery();
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        autoCompleteTextView.setFocusable(false);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        autoCompleteTextView.requestFocus();
        search();

    }


    public void search() {
        aotoString = new ArrayList<>();
        list = new ArrayList<>();
        requestSearch();
        Log.d("MoreActivity", "list:" + list);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MoreActivity.this, LotteryDetailActivity.class);
                DataValue.LOTTERY_MAIN_ID = mSearchBean.getData().get(position).getId_lottery();
                startActivity(intent);
            }
        });
    }


    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("city", DataValue.LOCATION);
        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_LOTTERY_MORE);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_LOTTERY_MORE, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("ahhhh请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mBean = gson.fromJson(json, MoreBean.class);
//                            DataValue.LOTTERY_VIEWPAGER_BEAN = mBean;
                            if (mBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mBean.getRes() == 10001){

                                Log.d("DeclareActivity", mBean.getData().get(0).getAddress());
                                mMoreAdapter.setStationBean(mBean);
                                mListView.setAdapter(mMoreAdapter);
                            }else if (mBean.getRes() == 10002) {
                                Toast.makeText(MoreActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mBean.getRes() == 10000) {
                                Toast.makeText(MoreActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    private void requestSearch() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("find",autoCompleteTextView.getText().toString());
        params.addBodyParameter("city", DataValue.LOCATION);
        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_LOTTERY_SEARCH);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_LOTTERY_SEARCH, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("ahhhh请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mSearchBean = gson.fromJson(json, LotterySearchBean.class);
//                            DataValue.LOTTERY_VIEWPAGER_BEAN = mBean;

                            if (mSearchBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mSearchBean.getRes() == 10001){

                                for (int i = 0; i < mSearchBean.getData().size(); i++) {
                                    list.add(mSearchBean.getData().get(i).getLotteryname());
                                }
                                Log.d("MoreActivity ==", "list:" + list);

                                for (int i = 0; i < list.size(); i++) {
                                    String name1 =list.get(i);
                                    aotoString.add(name1);
                                    Log.d("MoreActivity", aotoString.get(i));
                                }
                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MoreActivity.this, R.layout.support_simple_spinner_dropdown_item, aotoString);
                                autoCompleteTextView.setAdapter(adapter1);
                                autoCompleteTextView.setThreshold(1);

                            }else if (mSearchBean.getRes() == 10002){
                                Toast.makeText(MoreActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            }else if (mSearchBean.getRes() == 1000){
                                Toast.makeText(MoreActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
