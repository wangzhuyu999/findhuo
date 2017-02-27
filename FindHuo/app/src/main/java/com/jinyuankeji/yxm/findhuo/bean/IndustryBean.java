package com.jinyuankeji.yxm.findhuo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class IndustryBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_icon":"17","title":"技工"},{"id_icon":"18","title":"促销导购"},{"id_icon":"19","title":"钟点工"},{"id_icon":"20","title":"家教"},{"id_icon":"21","title":"家教"},{"id_icon":"22","title":"送货"},{"id_icon":"23","title":"找车"},{"id_icon":"24","title":"其他"}]
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
         * id_icon : 17
         * title : 技工
         */

        private String id_icon;
        private String title;

        public String getId_icon() {
            return id_icon;
        }

        public void setId_icon(String id_icon) {
            this.id_icon = id_icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
