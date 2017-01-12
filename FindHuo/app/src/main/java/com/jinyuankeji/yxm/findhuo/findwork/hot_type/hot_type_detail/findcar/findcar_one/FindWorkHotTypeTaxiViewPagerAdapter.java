package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jinyuankeji.yxm.findhuo.R;

import java.util.ArrayList;

/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class FindWorkHotTypeTaxiViewPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener  {

    private ArrayList<Integer> images;
    private Context context;
    private ViewPager viewPager;
    private ImageView[] tips;

    public void setTips(ImageView[] tips) {
        this.tips = tips;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public FindWorkHotTypeTaxiViewPagerAdapter(Context context) {
        this.context = context;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
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

        imageView.setImageResource(images.get(position % images.size()));

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

    // state = 0 state =1 state = 2
    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
