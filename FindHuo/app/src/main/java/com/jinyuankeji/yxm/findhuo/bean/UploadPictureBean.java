package com.jinyuankeji.yxm.findhuo.bean;

/**
 * Created by Administrator on 2017/2/6 0006.
 */

public class UploadPictureBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : {"headimg":"/Uploads/Dcard/20170120/588175e96b7b7.png","id_img":"15"}
     */

    private int res;
    private int cache_update;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * headimg : /Uploads/Dcard/20170120/588175e96b7b7.png
         * id_img : 15
         */

        private String headimg;
        private String id_img;

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getId_img() {
            return id_img;
        }

        public void setId_img(String id_img) {
            this.id_img = id_img;
        }
    }
}
