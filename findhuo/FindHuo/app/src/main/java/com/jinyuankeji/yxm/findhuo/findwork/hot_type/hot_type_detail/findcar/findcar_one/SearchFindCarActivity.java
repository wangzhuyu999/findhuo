package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.MainActivity;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.declare.FindCarActivity;
import com.jinyuankeji.yxm.findhuo.declare.PsychologicalDivisionActivity;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_detail_two.FindWorkHotTypeFindCarDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.hot_type_detail_one.FindWorkHotTypeDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.psychological.FindWorkHotTypePsychologicalDetailActivity;
import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryDetailActivity;
import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryDetailBean;
import com.jinyuankeji.yxm.findhuo.my.PsychologicalActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.SVG;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.jinyuankeji.yxm.findhuo.tools.search.Bean;
import com.jinyuankeji.yxm.findhuo.tools.search.SearchAdapter;
import com.jinyuankeji.yxm.findhuo.tools.search.SearchView;
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
public class SearchFindCarActivity extends AppCompatActivity implements SearchView.SearchViewListener {

    private ListView lvResults;
    private SearchView searchView;
    private ArrayAdapter<String> hintAdapter;
    private ArrayAdapter<String> autoCompleteAdapter;
    /**
     * 搜索结果列表adapter
     */
    private SearchAdapter resultAdapter;
    /**
     * 总数据
     */
    private List<Bean.DataBean> dbData;
    private Bean mBean;
    /**
     * 热搜版数据
     */
    private List<String> hintData;
    /**
     * 搜索过程中自动补全数据
     */
    private List<String> autoCompleteData;
    /**
     * 搜索结果的数据
     */
    private List<Bean.DataBean> resultData;
    /**
     * 默认提示框显示项的个数
     */
    private static int DEFAULT_HINT_SIZE = 4;
    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;

    /**
     * 设置提示框显示项的个数
     *
     * @param hintSize 提示框显示个数
     */
    public static void setHintSize(int hintSize) {
        SearchFindCarActivity.hintSize = hintSize;
    }

    private String tv = "";

    private TextView mTextView;
    private PsychologistOrNotBean mPsychologistOrNotBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_findwork_findcar_search);
        initViews();
        initData();

        Log.d("MainActivity", "Create+++++++++++++");
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        lvResults = (ListView) findViewById(R.id.main_lv_search_results);
        searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);

        requestDriver();
        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DataValue.FINDHUO_DETAIL_NOR = resultData.get(position).getName();
                if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
                    if (mDriverOrNotBean == null) {
                        Log.d("LotteryFragment", "实体类为null");
                    } else if (mDriverOrNotBean.getRes() == 10001) {
                        DataValue.FINDCAR_DETAIL_ID = resultData.get(position).getId_psychologist();
                        startActivity(new Intent(SearchFindCarActivity.this, FindWorkHotTypePsychologicalDetailActivity.class));
                    } else if (mDriverOrNotBean.getRes() == 10002) {
                        Toast.makeText(SearchFindCarActivity.this, "心理师申请正在审核中", Toast.LENGTH_SHORT).show();
                    } else if (mDriverOrNotBean.getRes() == 10000) {
                        Toast.makeText(SearchFindCarActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    } else if (mDriverOrNotBean.getRes() == 10003) {
                        Intent intent = new Intent(SearchFindCarActivity.this, PsychologicalDivisionActivity.class);
                        startActivity(intent);
                    } else if (mDriverOrNotBean.getRes() == 10004) {
                        Toast.makeText(SearchFindCarActivity.this, "此账号不存在", Toast.LENGTH_SHORT).show();
                    }
                } else if (DataValue.FINDWORK_TYPE_TV_OR.equals("找车")) {
                    if (mDriverOrNotBean == null) {
                        Log.d("LotteryFragment", "实体类为null");
                    } else if (mDriverOrNotBean.getRes() == 10001) {
                        DataValue.FINDCAR_DETAIL_ID = resultData.get(position).getId_introduce();
                        startActivity(new Intent(SearchFindCarActivity.this, FindWorkHotTypeFindCarDetailActivity.class));
                    } else if (mDriverOrNotBean.getRes() == 10002) {
                        Toast.makeText(SearchFindCarActivity.this, "司机申请正在审核中", Toast.LENGTH_SHORT).show();
                    } else if (mDriverOrNotBean.getRes() == 10000) {
                        Toast.makeText(SearchFindCarActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    } else if (mDriverOrNotBean.getRes() == 10003) {
                        Intent intent = new Intent(SearchFindCarActivity.this, FindCarActivity.class);
                        startActivity(intent);
                    } else if (mDriverOrNotBean.getRes() == 10004) {
                        Toast.makeText(SearchFindCarActivity.this, "此账号不存在", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DataValue.FINDHUO_DETAIL_NOR = resultData.get(position).getName();
//                if (DataValue.FINDWORK_TYPE_TV.equals("心理咨询") ){
//                    Intent intent = new Intent(FindWorkHotTypeDetailActivity.this, FindWorkHotTypePsychologicalDetailActivity.class);
//                    startActivity(intent);
//                }else {
                    DataValue.FINDWORK_OR_FINDCAR_ID = resultData.get(position).getId_introduce();
                    Intent intent = new Intent(SearchFindCarActivity.this, FindWorkNewDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 初始化数据
     */

    private void initData() {
        mBean = new Bean();
        //从数据库获取数据
        getDbData();
        //初始化热搜版数据
        getHintData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);


    }

    /**
     * 获取db 数据
     */
    private void getDbData() {
        int size = 100;
        dbData = new ArrayList<>();
        requestSearch();
    }

    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData() {
        hintData = new ArrayList<>(hintSize);
        for (int i = 1; i <= hintSize; i++) {
            hintData.add("热搜版" + i + "：Android自定义View");
        }
        hintAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hintData);
    }

    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {
//        tv = text;
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < dbData.size() && count < hintSize; i++) {
                if (dbData.get(i).getName().contains(text.trim())) {
                    autoCompleteData.add(dbData.get(i).getName());
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, autoCompleteData);
        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取搜索结果data和adapter
     */
    private void getResultData(String text) {
        if (resultData == null) {
            // 初始化
            Log.d("MainActivity", "null");
            resultData = new ArrayList<>();
        } else {
            Log.d("MainActivity", "有");
            resultData.clear();
            Log.d("MainActivity", "dbData.size():" + dbData.size());
            for (int i = 0; i < dbData.size(); i++) {
                if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
                    if (dbData.get(i).getName().contains(text.trim())) {
                        resultData.add(dbData.get(i));
                        Log.d("MainActivity", "resultData:" + resultData);
                    } else {
                        Log.d("MainActivity", "你还能");
                    }
                } else if (DataValue.FINDWORK_TYPE_TV_OR.equals("找车")) {
                    if (dbData.get(i).getAddress().contains(text.trim()) || dbData.get(i).getAddress1().contains(text.trim())) {
                        resultData.add(dbData.get(i));
                        Log.d("MainActivity", "resultData:" + resultData);
                    } else {
                        Log.d("MainActivity", "你还能zhaohuo");
                    }
                } else {
                    if (dbData.get(i).getTitle().contains(text.trim())) {
                        resultData.add(dbData.get(i));
                        Log.d("MainActivity", "resultData:" + resultData);
                    } else {
                        Log.d("MainActivity", "你还能jigong");
                    }
                }
            }
        }
        if (resultAdapter == null) {
            Log.d("MainActivity", "adapter");
            if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询") || DataValue.FINDWORK_TYPE_TV_OR.equals("找车")) {
                resultAdapter = new SearchAdapter(this, resultData, R.layout.activity_findwork_findcar_item);
            } else {
                resultAdapter = new SearchAdapter(this, resultData, R.layout.fragment_findwork_declare_new_item);
            }
        } else {
            Log.d("MainActivity", "lalsalsd");
            resultAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 当搜索框 文本改变时 触发的回调 ,更新自动补全数据
     *
     * @param text
     */
    @Override
    public void onRefreshAutoComplete(String text) {
        Log.d("MainActivity", "更新数据+++++++++++++");
        tv = text;
        Log.d("MainActivity", tv + "     ___________");
        //更新数据
        getAutoCompleteData(text);

    }

    /**
     * 点击搜索键时edit text触发的回调
     *
     * @param text
     */
    @Override
    public void onSearch(String text) {
        Log.d("MainActivity", "更新result数据+++++++++++++");
        tv = text;
        Log.d("MainActivity", tv + "     _____))))))______");

        //更新result数据
        getResultData(text);
        lvResults.setVisibility(View.VISIBLE);
        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            lvResults.setAdapter(resultAdapter);
        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();
        }
//        Toast.makeText(this, "完成", Toast.LENGTH_SHORT).show();
    }

    private String Url;

    private void requestSearch() {
//        int id = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
//        textView = (TextView) mSearchView.findViewById(id);

        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        Log.d("MainActivity", tv + "+++++++++++++++++++");
        Log.d("SearchFrActi++++++++++", DataValue.FINDWORK_TYPE_TV_OR + "----");

        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
            params.addBodyParameter("name", tv);
            Url = URLValue.URL_NOR + URLValue.URL_SEARCH_PSYCHOLOGIST;
        } else if (DataValue.FINDWORK_TYPE_TV_OR.equals("找车")) {
            params.addBodyParameter("address", tv);
            params.addBodyParameter("city", DataValue.LOCATION);
            Url = URLValue.URL_NOR + URLValue.URL_SEARCH_FINDCAR;
        } else {
            params.addBodyParameter("title", tv);
            params.addBodyParameter("city", DataValue.LOCATION);
            params.addBodyParameter("id_icon", DataValue.FINDWORK_TYPE_ID);
            Url = URLValue.URL_NOR + URLValue.URL_FINDWORK_SEARCH_JIGONG;
        }

        httpUtils.send(HttpRequest.HttpMethod.POST, Url, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {

                            Log.e("ahhhh请求成功sousuo 心理咨询", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        } else if (DataValue.FINDWORK_TYPE_TV_OR.equals("找车")) {

                            Log.e("ahhhh请求成功sousuo 找车", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        } else {

                            Log.e("ahhhh请求成功sousuo ji", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        }
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mBean = gson.fromJson(json, Bean.class);
                            if (mBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mBean.getRes() == 10001) {
                                for (int i = 0; i < mBean.getData().size(); i++) {
                                    dbData.add(mBean.getData().get(i));
                                }

                            } else if (mBean.getRes() == 10002) {
                                Toast.makeText(SearchFindCarActivity.this, "暂时无数据", Toast.LENGTH_SHORT).show();
                            } else if (mBean.getRes() == 1000) {
                                Toast.makeText(SearchFindCarActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private DriverOrNotBean mDriverOrNotBean;
    private String url;

    public void requestDriver() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        if (DataValue.FINDWORK_TYPE_TV_OR.equals("找车")) {
            url = URLValue.URL_NOR + URLValue.URL_DRIVER_OR_NOT;
            params.addBodyParameter("account", DataValue.DRIVER_YES_OR_NOT_TEL);
        } else {
            url = URLValue.URL_NOR + URLValue.URL_PSYCHOLOGIST_OR_NOT;
            params.addBodyParameter("account", DataValue.DRIVER_YES_OR_NOT_TEL);
        }

        httpUtils.send(HttpRequest.HttpMethod.POST, url, params,
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


}
