package com.jinyuankeji.yxm.findhuo.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */

public class SVG extends GridView {


    public SVG(Context context) {
        super(context);
    }

    public SVG(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SVG(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
