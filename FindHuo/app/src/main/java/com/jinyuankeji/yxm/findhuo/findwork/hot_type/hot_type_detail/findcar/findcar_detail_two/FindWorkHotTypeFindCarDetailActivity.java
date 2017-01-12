package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_detail_two;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;

/**
 * Created by  yxiaomin on 2017/1/4 0004.
 */
public class FindWorkHotTypeFindCarDetailActivity extends BaseActivity {
    private ImageView ivBack;
    private ImageView ivCall;
    private TextView tvTitle;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_hot_type_findcar_detail;
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.tv_findcar_detail_back);
        ivCall = (ImageView) findViewById(R.id.iv_findcar_detail_cal);
        tvTitle = (TextView) findViewById(R.id.tv_findcar_detail_title_name);
    }

    @Override
    protected void initData() {
        tvTitle.setText(DataValue.FINDHUO_DETAIL_NOR + "找车");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "34435353"));
                if (ActivityCompat.checkSelfPermission(FindWorkHotTypeFindCarDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(phoneCall);
//                number="";
            }
        });
    }
}
