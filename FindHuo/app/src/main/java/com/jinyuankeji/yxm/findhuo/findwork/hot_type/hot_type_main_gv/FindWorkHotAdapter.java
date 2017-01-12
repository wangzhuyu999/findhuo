package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_main_gv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryStationBean;

import java.util.List;
import java.util.Map;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */

public class FindWorkHotAdapter extends BaseAdapter {
    private Context mContext;
    private LotteryStationBean stationBean;
    private List<FindWorkHotBean> datass;
    private List<Map<String, Object>> datas;

    public void setListitem(List<Map<String, Object>> listitem) {
        this.datas = listitem;
        notifyDataSetChanged();
    }

    public FindWorkHotAdapter(Context context) {
        mContext = context;
    }



    public void setStationBean(LotteryStationBean stationBean) {
        this.stationBean = stationBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_findwork_hot_type_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Map<String, Object> map = datas.get(position);
        viewHolder.ivIcon.setImageResource((Integer) map.get("image"));

        viewHolder.tvName.setText(map.get("title") + "");

        return convertView;

    }

    public class ViewHolder {
        private TextView tvName;
        private ImageView ivIcon;

        public ViewHolder(View itemView) {
            tvName = (TextView) itemView.findViewById(R.id.tv_findwork_hot_type_name);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_findwork_hot_type_icon);


        }
    }
}
