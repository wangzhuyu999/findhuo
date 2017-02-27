package com.jinyuankeji.yxm.findhuo.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.NewsBean;
import com.jinyuankeji.yxm.findhuo.bean.NewsRecordBean;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements View.OnClickListener{
    private NewsBean mAllBeen;
    private Context mContext;




    public NewsAdapter(Context context) {
        mContext = context;
    }
    public void setAllBeen(NewsBean allBeen) {
        mAllBeen = allBeen;
        notifyDataSetChanged();
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news2, null, false));
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        holder.text1.setText(mAllBeen.getData().get(position).getContent());
        holder.text2.setText(mAllBeen.getData().get(position).getCreate_time());

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
            text1 = (TextView) itemView.findViewById(R.id.text5);
            text2 = (TextView) itemView.findViewById(R.id.text2);


        }
    }


}
