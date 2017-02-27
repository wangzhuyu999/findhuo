package com.jinyuankeji.yxm.findhuo.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.BuyLotteryFragmentSBean;
import com.jinyuankeji.yxm.findhuo.bean.OrderInfoBean;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2017/2/21 0021.
 */

public class BuyLotteryFragmentEDetailsAdapter extends RecyclerView.Adapter<BuyLotteryFragmentEDetailsAdapter.ViewHolder> implements View.OnClickListener{
    private OrderInfoBean mAllBeen;
    private Context mContext;




    public BuyLotteryFragmentEDetailsAdapter(Context context) {
        mContext = context;
    }
    public void setAllBeen(OrderInfoBean allBeen) {
        mAllBeen = allBeen;
        notifyDataSetChanged();
    }

    @Override
    public BuyLotteryFragmentEDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BuyLotteryFragmentEDetailsAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_buylottery_e_details, null, false));
    }

    @Override
    public void onBindViewHolder(BuyLotteryFragmentEDetailsAdapter.ViewHolder holder, int position) {
        Picasso.with(mContext).load(mAllBeen.getData().get(position).getUrl()).into(holder.imageView);
    }



    @Override
    public int getItemCount() {
        return mAllBeen.getData().size();
    }

    @Override
    public void onClick(View v) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);

        }
    }


}
