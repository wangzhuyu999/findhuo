package com.jinyuankeji.yxm.findhuo.lottery.more;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryStationBean;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryStationRVAdapter;
import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryDetailActivity;
import com.jinyuankeji.yxm.findhuo.tools.MyRVOnClickListener;

import java.util.ArrayList;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */
public class MoreActivity extends BaseActivity {
    private ListView mListView;
    private LotteryStationBean mBean;
    private ArrayList<LotteryStationBean> mBeanArrayList;
    private LotteryMoreAdapter mMoreAdapter;
    private ImageView back;

    private AutoCompleteTextView autoCompleteTextView;
    private ArrayList<String> aotoString;

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
        mBeanArrayList = new ArrayList<>();
        LotteryStationBean bean = new LotteryStationBean();
        for (int i = 0; i < 50; i++) {
            bean.setAddr("大东区");
            bean.setNamr("张三");
            bean.setImg(R.mipmap.ic_launcher);
            mBeanArrayList.add(bean);
        }
        mMoreAdapter.setStationBeanList(mBeanArrayList);
        mListView.setAdapter(mMoreAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MoreActivity.this, LotteryDetailActivity.class);
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
        String[] str = {"aaa", "aabbbbb", "ssss", "qqqq", "wwww", "rrrrrr", "aa加加加", "鱼鱼鱼", "dddddddd"};
        aotoString = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            String name1 = str[i];
            aotoString.add(name1);
            Log.d("MoreActivity", aotoString.get(i));
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, aotoString);
        autoCompleteTextView.setAdapter(adapter1);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MoreActivity.this, LotteryDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
