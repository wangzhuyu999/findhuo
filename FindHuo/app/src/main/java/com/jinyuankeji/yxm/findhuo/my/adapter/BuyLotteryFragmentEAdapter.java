package com.jinyuankeji.yxm.findhuo.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.BuyLotteryFragmentEBean;
import com.jinyuankeji.yxm.findhuo.bean.BuyLotteryFragmentSBean;
import com.jinyuankeji.yxm.findhuo.bean.NewsRecordBean;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public class BuyLotteryFragmentEAdapter extends RecyclerView.Adapter<BuyLotteryFragmentEAdapter.ViewHolder> implements View.OnClickListener{
    private BuyLotteryFragmentSBean mAllBeen;
    private Context mContext;




    public BuyLotteryFragmentEAdapter(Context context) {
        mContext = context;
    }
    public void setAllBeen(BuyLotteryFragmentSBean allBeen) {
        mAllBeen = allBeen;
        notifyDataSetChanged();
    }

    @Override
    public BuyLotteryFragmentEAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BuyLotteryFragmentEAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_buylotteryframgente, null, false));
    }

    @Override
    public void onBindViewHolder(BuyLotteryFragmentEAdapter.ViewHolder holder, int position) {
        Picasso.with(mContext).load(mAllBeen.getData().get(position).getImg()).into(holder.imageView);
        holder.text1.setText(mAllBeen.getData().get(position).getCreate_time());
        holder.text2.setText(mAllBeen.getData().get(position).getGoodsname());
        holder.text3.setText(mAllBeen.getData().get(position).getPayment_price());
      //  String IdOrder = mAllBeen.getData().get(position).getId_order();
      //  Contants.id_order = IdOrder;
        Log.e("99999999999999999999999",Contants.id_order+"");
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
        private TextView text1,text2,text3;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            text1 = (TextView) itemView.findViewById(R.id.textView4);
            text2 = (TextView) itemView.findViewById(R.id.textView5);
            text3 = (TextView) itemView.findViewById(R.id.textView);
        }
    }


}
