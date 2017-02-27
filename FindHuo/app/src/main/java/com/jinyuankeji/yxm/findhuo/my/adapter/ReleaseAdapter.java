package com.jinyuankeji.yxm.findhuo.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.bean.MoneyBean;
import com.jinyuankeji.yxm.findhuo.bean.ReleaseBean;
import com.jinyuankeji.yxm.findhuo.tools.Contants;

import java.util.List;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.ViewHolder> implements View.OnClickListener{
    private ReleaseBean mAllBeen;
    private Context mContext;


    public ReleaseAdapter(Context context) {
        mContext = context;
    }
    public void setAllBeen(ReleaseBean allBeen) {
        mAllBeen = allBeen;
        notifyDataSetChanged();
    }

    @Override
    public ReleaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReleaseAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_release, null, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text1.setText(mAllBeen.getData().get(position).getCreate_time());
       // holder.text2.setText(mAllBeen.getData().get(position).getType());
        String introduce = mAllBeen.getData().get(position).getId_introduce();
        Contants.id_introduce = introduce;

        String e = mAllBeen.getData().get(position).getType();
        int i = Integer.valueOf(e).intValue();
        if(i == 1){
            holder.text2.setText("我要找零工");

        }if(i == 2){
            holder.text2.setText("我要干零工");

        }if( i == 3){
            holder.text2.setText("找车");
        }




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
        private ImageView imageView2;


        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.textView1);
            text2 = (TextView) itemView.findViewById(R.id.textView2);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);


        }
    }

    public void removeData(int position) {
        if(mAllBeen.getData().size()<2&&mAllBeen.getData().size()!=0){
            mAllBeen.getData().remove(0);
            notifyDataSetChanged();
        }else if(mAllBeen.getData().size()==0){
        }else{
            mAllBeen.getData().remove(position);
            notifyDataSetChanged();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,mAllBeen.getData().size());
        }

    }


}
