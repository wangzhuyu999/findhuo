package com.jinyuankeji.yxm.findhuo.lottery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.tools.MyRVOnClickListener;

import java.util.ArrayList;

/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class LotteryStationRVAdapter extends RecyclerView.Adapter<LotteryStationRVAdapter.ViewHolderLotteryStation>{
    private MyRVOnClickListener mMyRvListener;
    private Context mContext;
    private LotteryStationBean stationBean;
    private ArrayList<LotteryStationBean> stationBeanList;


    public LotteryStationRVAdapter(Context context) {
        mContext = context;
    }

    public void setStationBeanList(ArrayList<LotteryStationBean> stationBeanList) {
        this.stationBeanList = stationBeanList;
        Log.d("LotteryStationRVAdapter", stationBeanList.get(0).getAddr());
        notifyDataSetChanged();
    }

    public void setStationBean(LotteryStationBean stationBean) {
        this.stationBean = stationBean;
        notifyDataSetChanged();
    }

    public void setMyRvListener(MyRVOnClickListener myRvListener) {
        mMyRvListener = myRvListener;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolderLotteryStation onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderLotteryStation(LayoutInflater.from(mContext).inflate(R.layout.fragment_lottery_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolderLotteryStation holder, int position) {
        Log.d("LotteryStationRVAdapter", stationBeanList.get(position).getNamr());
        holder.tvName.setText(stationBeanList.get(position).getNamr());
        holder.tvAddr.setText(stationBeanList.get(position).getAddr());
        holder.ivImg.setImageResource(stationBeanList.get(position).getImg());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position1 = holder.getLayoutPosition();
                mMyRvListener.onClick(position1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolderLotteryStation extends RecyclerView.ViewHolder {
        private TextView tvName,tvAddr;
        private ImageView ivImg;
        private RelativeLayout ll;

        public ViewHolderLotteryStation(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_lottery_station_name);
            tvAddr = (TextView) itemView.findViewById(R.id.tv_lottery_station_addr);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_lottery_rv_item_icon);
            ll = (RelativeLayout) itemView.findViewById(R.id.rv_layout_lottery);

        }
    }

}
