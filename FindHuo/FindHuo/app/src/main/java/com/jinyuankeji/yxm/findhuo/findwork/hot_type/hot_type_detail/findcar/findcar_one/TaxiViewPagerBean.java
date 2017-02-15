package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/16 0016.
 */

public class TaxiViewPagerBean {


    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170112/587715df8dcd8.png"},{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170112/587715f3cc2d5.png"},{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170113/587848d6c1aaf.png"}]
     */

    private int res;
    private int cache_update;
    private List<DataBean> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getCache_update() {
        return cache_update;
    }

    public void setCache_update(int cache_update) {
        this.cache_update = cache_update;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgurl : http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170112/587715df8dcd8.png
         */

        private String imgurl;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
