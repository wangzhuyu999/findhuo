package com.jinyuankeji.yxm.findhuo.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.BuyLotteryFragmentSBean;
import com.jinyuankeji.yxm.findhuo.bean.NewsRecordBean;
import com.jinyuankeji.yxm.findhuo.tools.Contants;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public class BuyLotteryFragmentSAdapter extends RecyclerView.Adapter<BuyLotteryFragmentSAdapter.ViewHolder> implements View.OnClickListener{
    private BuyLotteryFragmentSBean mAllBeen;
    private Context mContext;




    public BuyLotteryFragmentSAdapter(Context context) {
        mContext = context;
    }
    public void setAllBeen(BuyLotteryFragmentSBean allBeen) {
        mAllBeen = allBeen;
        notifyDataSetChanged();
    }

    @Override
    public BuyLotteryFragmentSAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BuyLotteryFragmentSAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_buylotteryfragment_s, null, false));
    }

    @Override
    public void onBindViewHolder(BuyLotteryFragmentSAdapter.ViewHolder holder, int position) {
        holder.text1.setText(mAllBeen.getData().get(position).getCreate_time());
        holder.text2.setText(mAllBeen.getData().get(position).getGoodsname());
        holder.text3.setText(mAllBeen.getData().get(position).getPayment_price());
//        String IdOrder = mAllBeen.getData().get(position).getId_order();
//        Contants.id_order = IdOrder;
    }



    @Override
    public int getItemCount() {
        return mAllBeen.getData().size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text1,text2,text3;


        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.textView4);
            text2 = (TextView) itemView.findViewById(R.id.textView5);
            text3 = (TextView) itemView.findViewById(R.id.textView);


        }
    }


}
