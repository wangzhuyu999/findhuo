package com.jinyuankeji.yxm.findhuo.declare;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class DeclareFragment extends BaseFragment {
    private Button findCar;
    private Button findWork;
    private Button Working;
    private Button PsychologicalDivision;


    @Override
    protected int initLayout() {
        return R.layout.fragment_declare;
    }

    @Override
    protected void initView() {
        findCar = (Button) getView().findViewById(R.id.button1);
        findCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),FindCarActivity.class);
                startActivity(it);
            }
        });
        findWork = (Button)getView().findViewById(R.id.button2);
        findWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),FindWorkActivity.class);
                startActivity(it);
            }
        });
        Working = (Button)getView().findViewById(R.id.button3);
        Working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),WorkingActivity.class);
                startActivity(it);
            }
        });
        PsychologicalDivision = (Button)getView().findViewById(R.id.button4);
        PsychologicalDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),PsychologicalDivisionActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
