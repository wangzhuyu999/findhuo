package com.jinyuankeji.yxm.findhuo.findwork.declare_new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.findwork.FindWorkDeclareNewBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class FindWorkDeclareNewAdapter extends BaseAdapter{
    private Context mContext;

    private FindWorkDeclareNewBean datas;


    public FindWorkDeclareNewAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(FindWorkDeclareNewBean datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.getIntroduce().size();
    }

    @Override
    public Object getItem(int position) {
        return datas.getIntroduce() == null ? null : datas.getIntroduce().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderLotteryStation viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_findwork_declare_new_item, parent, false);
            viewHolder = new ViewHolderLotteryStation(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderLotteryStation) convertView.getTag();
        }
        viewHolder.tvName.setText(datas.getIntroduce().get(position).getName());
        viewHolder.tvJob.setText(datas.getIntroduce().get(position).getTitle());
        viewHolder.tvRange.setText(datas.getIntroduce().get(position).getService_area());
        viewHolder.tvPrice.setText(datas.getIntroduce().get(position).getSalary());
        Picasso.with(mContext).load(datas.getIntroduce().get(position).getHeadimg()).into(viewHolder.ivImg);
        return convertView;
    }

    public class ViewHolderLotteryStation {
        private TextView tvName,tvJob,tvRange,tvPrice;
        private ImageView ivImg;
        private RelativeLayout ll;

        public ViewHolderLotteryStation(View itemView) {
            tvJob = (TextView) itemView.findViewById(R.id.tv_findwork_declare_new_job);
            tvName = (TextView) itemView.findViewById(R.id.tv_findwork_declare_new_name);
            tvRange = (TextView) itemView.findViewById(R.id.tv_findwork_declare_new_range);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_findwork_declare_new_price);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_findworke_declare_new_head);
            ll = (RelativeLayout) itemView.findViewById(R.id.rv_findwork_declare_new_item);
        }
    }

}
