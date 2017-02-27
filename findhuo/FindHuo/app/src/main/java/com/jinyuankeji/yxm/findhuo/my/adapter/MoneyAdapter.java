package com.jinyuankeji.yxm.findhuo.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.MoneyBean;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryStationRVAdapter;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerBean;
import com.jinyuankeji.yxm.findhuo.tools.MyRVOnClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/21 0021.
 */

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> implements View.OnClickListener{
    private MoneyBean mAllBeen;
    private Context mContext;




    public MoneyAdapter(Context context) {
        mContext = context;
    }
    public void setAllBeen(MoneyBean allBeen) {
        mAllBeen = allBeen;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_money, null, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.text1.setText(mAllBeen.getData().get(position).getMoney());
        holder.text2.setText(mAllBeen.getData().get(position).getCreate_time());

        /***
         * 解析图片
         */
      //  Picasso.with(mContext).load(mAllBeen.getData().get(position).getStoreIcon()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mAllBeen.getData().size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text1,text2;


        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.textView4);
            text2 = (TextView) itemView.findViewById(R.id.textView5);


        }
    }


}
