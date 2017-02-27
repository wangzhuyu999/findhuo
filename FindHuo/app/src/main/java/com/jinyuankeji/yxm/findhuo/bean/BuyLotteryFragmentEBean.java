package com.jinyuankeji.yxm.findhuo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public class BuyLotteryFragmentEBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"url":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170117/587d7728b0404.png"}]
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
         * url : http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170117/587d7728b0404.png
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
