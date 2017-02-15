package com.jinyuankeji.yxm.findhuo.lottery;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jinyuankeji.yxm.findhuo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class LotteryViewPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener  {

    private ArrayList<LotteryViewPagerBean> images;
    private Context context;
    private ViewPager viewPager;
    private ImageView[] tips;

    public void setTips(ImageView[] tips) {
        this.tips = tips;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public LotteryViewPagerAdapter(Context context) {
        this.context = context;
    }

    public void setImages(ArrayList<LotteryViewPagerBean> images) {
        this.images = images;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return images == null? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_lottery_banner_item,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
//        imageView.setImageResource(images.get(position % images.size()));
        Log.d("LotteryViewPagerAdapter", images.get(position % images.size()).getSlider().get(position % images.size()).getImgurl());
        Picasso.with(context).load(images.get(position % images.size()).getSlider().get(position % images.size()).getImgurl()).into(imageView);

        container.addView(view);
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < tips.length; i++) {
            if(i == position % images.size()){
                tips[i].setImageResource(R.mipmap.fri3x);
            }else {
                tips[i].setImageResource(R.mipmap.sec3x);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
