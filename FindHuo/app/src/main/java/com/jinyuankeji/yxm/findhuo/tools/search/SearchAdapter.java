package com.jinyuankeji.yxm.findhuo.tools.search;

import android.content.Context;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;

import java.util.List;

/**
 * Created by yetwish on 2015-05-11
 */

public class SearchAdapter extends CommonAdapter<Bean.DataBean>{

    public SearchAdapter(Context context, List<Bean.DataBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {

        holder.setImageResource(R.id.iv_findworke_findcar_item_head,"http://img3.redocn.com/tupian/20150430/mantenghuawenmodianshiliangbeijing_3924704.jpg",mContext)
                .setText(R.id.tv_findworke_findcar_item_name,mData.get(position).getName())
                .setText(R.id.tv_findworke_fondcar_item_to_tv,"")
                .setText(R.id.tv_findworke_findcar_item_time_tv,mData.get(position).getCompany())
        .setText(R.id.tv_findworke_findcar_item_to_addr,"")
        .setText(R.id.tv_findworke_findcar_item_addr,"");
        } else if (DataValue.FINDWORK_TYPE_TV_OR.equals("找车")) {
            holder.setImageResource(R.id.iv_findworke_findcar_item_head,"http://img3.redocn.com/tupian/20150430/mantenghuawenmodianshiliangbeijing_3924704.jpg",mContext)
                    .setText(R.id.tv_findworke_findcar_item_name,mData.get(position).getName())
                    .setText(R.id.tv_findworke_findcar_item_addr,mData.get(position).getAddress())
                    .setText(R.id.tv_findworke_findcar_item_to_addr,mData.get(position).getAddress1())
                    .setText(R.id.tv_findworke_findcar_item_time_tv,mData.get(position).getAppointment());
        }else {
            holder.setImageResource(R.id.iv_findworke_declare_new_head,"http://img3.redocn.com/tupian/20150430/mantenghuawenmodianshiliangbeijing_3924704.jpg",mContext)
                    .setText(R.id.tv_findwork_declare_new_name,mData.get(position).getName())
                    .setText(R.id.tv_findwork_declare_new_job,mData.get(position).getTitle())
                    .setText(R.id.tv_findwork_declare_new_range,mData.get(position).getService_area())
                    .setText(R.id.tv_findwork_declare_new_price,mData.get(position).getSalary());
        }
    }
}
