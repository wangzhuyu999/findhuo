package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerBean;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/4 0004.
 */

public class FindWorkHotTypeTaxiDetailLVAdapter extends BaseAdapter {
    private Context mContext;
    private FindWorkHotTypeTaxiDetailLVBean datas;
//    private List<FindWorkHotTypeTaxiDetailLVBean> datas;


    public FindWorkHotTypeTaxiDetailLVAdapter(Context context) {
        mContext = context;
    }

//    public void setDatas(List<FindWorkHotTypeTaxiDetailLVBean> datas) {
//        this.datas = datas;
//    }

    public void setStationBean(FindWorkHotTypeTaxiDetailLVBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null && datas.getData().size() > 0 ? datas.getData().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.getData().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderT viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_findwork_findcar_item, parent, false);
            viewHolder = new ViewHolderT(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderT) convertView.getTag();
        }
        if (DataValue.FINDWORK_TYPE_TV_OR.equals("心理咨询")) {
            viewHolder.tvName.setText(datas.getData().get(position).getName());
            viewHolder.tvAddr.setText("");
            viewHolder.tvAddrTo.setText("");
            viewHolder.tvTotv.setVisibility(View.GONE);
            viewHolder.tvTimeTV.setText(datas.getData().get(position).getCompany());
            Picasso.with(mContext).load(datas.getData().get(position).getHeadimg()).into(viewHolder.ivImg);
        } else {
            viewHolder.tvName.setText(datas.getData().get(position).getName());
            viewHolder.tvAddr.setText(datas.getData().get(position).getAddress());
            viewHolder.tvAddrTo.setText(datas.getData().get(position).getAddress1());
            viewHolder.tvTimeTV.setText(datas.getData().get(position).getAppointment());
            Picasso.with(mContext).load(datas.getData().get(position).getHeadimg()).into(viewHolder.ivImg);
        }
        return convertView;

    }

    public class ViewHolderT {
        private TextView tvName, tvAddr, tvTime, tvAddrTo, tvTimeTV, tvTotv;
        private ImageView ivImg;
        private RelativeLayout ll;

        public ViewHolderT(View itemView) {
            tvName = (TextView) itemView.findViewById(R.id.tv_findworke_findcar_item_name);
            tvAddr = (TextView) itemView.findViewById(R.id.tv_findworke_findcar_item_addr);
            tvTime = (TextView) itemView.findViewById(R.id.tv_findworke_findcar_item_time);
            tvAddrTo = (TextView) itemView.findViewById(R.id.tv_findworke_findcar_item_to_addr);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_findworke_findcar_item_head);
            tvTimeTV = (TextView) itemView.findViewById(R.id.tv_findworke_findcar_item_time_tv);
            tvTotv = (TextView) itemView.findViewById(R.id.tv_findworke_fondcar_item_to_tv);
//            ll = (RelativeLayout) itemView.findViewById(R.id.rv_findwork_declare_new_item);


        }
    }

}
