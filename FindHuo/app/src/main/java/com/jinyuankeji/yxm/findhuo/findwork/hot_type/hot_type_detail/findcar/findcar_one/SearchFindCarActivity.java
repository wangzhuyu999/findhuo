package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryDetailActivity;
import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryDetailBean;
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
 * Created by  yxiaomin on 2017/1/14 0014.
 */
public class SearchFindCarActivity extends BaseActivity{
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaid"};
    private SearchView mSearchView;
    private ListView mListView;
    private FindWorkHotTypeTaxiDetailLVBean mBean;
    private List<FindWorkHotTypeTaxiDetailLVBean> str;
    private FindWorkHotTypeTaxiDetailLVAdapter mAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_findcar_search;
    }

    @Override
    protected void initView() {
        mSearchView = (SearchView) findViewById(R.id.searchView);
        mListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void initData() {
        mBean = new FindWorkHotTypeTaxiDetailLVBean();
        mAdapter = new FindWorkHotTypeTaxiDetailLVAdapter(this);
        str = new ArrayList<>();
requestSearch();
       searchContent();

    }

    private void searchContent() {
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs));

//        mAdapter.setStationBean(mBean);
        mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.fragment_findwork_hot_type_taxi_detail_lv_item, mStrs));
        mListView.setTextFilterEnabled(true);
        // 设置搜索文本
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.setFilterText(newText);
                }else{
                    mListView.clearTextFilter();
                }
                return false;
            }
        });

        if(mSearchView==null){
            return;
        }
        else{

            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            android.widget.LinearLayout.LayoutParams layoutParams = (android.widget.LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.bottomMargin = -5;
            textView.setLayoutParams(layoutParams);
            textView.setTextColor(this.getResources().getColor(R.color.search_txt_color));
            textView.setHintTextColor(this.getResources().getColor(R.color.search_hint_color));
        }
    }

    TextView textView;
    private void requestSearch() {

        int id = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
        textView = (TextView) mSearchView.findViewById(id);


        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();

        Log.d("LotteryDetailActivity", DataValue.LOTTERY_MAIN_ID);
        params.addBodyParameter("address",  textView.getText().toString());
        params.addBodyParameter("city", DataValue.LOCATION);

        Log.d("tttttttt", URLValue.URL_NOR + URLValue.URL_LOTTERY_DEYAIL);
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_SEARCH_FINDCAR, params,
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
//                            mBean = gson.fromJson(json, SearchFindCarBean.class);
//                            if (mBean == null) {
//                                Log.d("LotteryFragment", "实体类为null");
//                            } else if (mBean.getRes() == 10001){
//                                for (int i = 0; i < mBean.getData().size(); i++) {
//                                str.add(mBean);
//                                }
//
//                            }else if (mBean.getRes() == 10002){
//                                Toast.makeText(SearchFindCarActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
//                            }else if (mBean.getRes() == 1000){
//                                Toast.makeText(SearchFindCarActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    }
                });
    }
}
