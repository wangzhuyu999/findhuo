package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.psychological;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;

/**
 * Created by  yxiaomin on 2017/1/4 0004.
 */
public class FindWorkHotTypePsychologicalDetailActivity extends BaseActivity {
    private ImageView ivBack;
    private TextView tvTitle;

    @Override
    protected int initLayout() {
        return R.layout.activity_psychological_detail;
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_psychological_detail_back);
        tvTitle = (TextView) findViewById(R.id.tv_psychological_detail_title);

    }

    @Override
    protected void initData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText(DataValue.FINDHUO_DETAIL_NOR);

    }
}
