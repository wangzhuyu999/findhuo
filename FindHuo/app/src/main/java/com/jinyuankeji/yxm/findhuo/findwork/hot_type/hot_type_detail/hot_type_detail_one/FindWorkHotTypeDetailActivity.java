package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.hot_type_detail_one;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewBean;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one.FindWorkHotTypeTaxiDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.psychological.FindWorkHotTypePsychologicalDetailActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.SVL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  yxiaomin on 2016/12/21 0021.
 */

public class FindWorkHotTypeDetailActivity extends BaseActivity {
    private FindWorkHotTypeDetailAdapter mNewAdapter;
    private FindWorkDeclareNewBean mNewBean;
    private List<FindWorkDeclareNewBean> mNewBeanList;
    private SVL mLv;
    private ImageView back;

//    private AutoCompleteTextView autoCompleteTextView;
//    private ArrayList<String> aotoString;

    private TextView tvTitle;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_hot_type_detail;
    }

    @Override
    protected void initView() {
        mLv = (SVL) findViewById(R.id.lv_findwork_hot_type_detail);
        back = (ImageView) findViewById(R.id.tv_findwork_hot_type_detail_back);
//        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_search_findwork_hot_type_detail);
        tvTitle = (TextView) findViewById(R.id.tv_findwork_hot_type_detail_title_name);
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
        mNewBeanList = new ArrayList<>();
        FindWorkDeclareNewBean newBean = new FindWorkDeclareNewBean();
        for (int i = 0; i < 50; i++) {
            newBean.setRange("大东区");
            newBean.setName("张三");
            newBean.setJob("保洁");
            newBean.setPrice("1000");
            newBean.setImg(R.mipmap.ic_launcher);
            mNewBeanList.add(newBean);
        }
        mNewAdapter.setDatas(mNewBeanList);
        mLv.setAdapter(mNewAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataValue.FINDHUO_DETAIL_NOR = mNewBeanList.get(position).getName();
                if (DataValue.FINDWORK_TYPE_TV.equals("心理咨询") ){
                    Intent intent = new Intent(FindWorkHotTypeDetailActivity.this, FindWorkHotTypePsychologicalDetailActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(FindWorkHotTypeDetailActivity.this, FindWorkNewDetailActivity.class);
                    startActivity(intent);
                }
            }
        });

//        autoCompleteTextView.setFocusable(false);
//        autoCompleteTextView.setFocusable(true);
//        autoCompleteTextView.setFocusableInTouchMode(true);
//        autoCompleteTextView.requestFocus();
//        search();
    }

//    public void search() {
////        ArrayList<String> sesect1 = {"","","","","","","","",""};
//        String[] str = {"aaa", "aabbbbb", "ssss", "qqqq", "wwww", "rrrrrr", "aa加加加", "鱼鱼鱼", "dddddddd"};
//        aotoString = new ArrayList<>();
//        for (int i = 0; i < str.length; i++) {
//            String name1 = str[i];
//            aotoString.add(name1);
//            Log.d("MoreActivity", aotoString.get(i));
//        }
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, aotoString);
//        autoCompleteTextView.setAdapter(adapter1);
//        autoCompleteTextView.setThreshold(1);
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(FindWorkHotTypeDetailActivity.this, LotteryDetailActivity.class);
////                datas = new ArrayList<>();
////                datas = tools.queryAllLinkman();
////                intent.putExtra("linkmanNum",datas.get(position).getNum().toString());
//                startActivity(intent);
//            }
//        });
//    }


}
