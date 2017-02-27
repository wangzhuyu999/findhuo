package com.jinyuankeji.yxm.findhuo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public class ReleaseBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_introduce":"1","title":"家电维修","create_time":"2017-01-11 00:00:00","type":"1"},{"id_introduce":"2","title":"家电维修","create_time":"2017-01-11 00:00:00","type":"1"},{"id_introduce":"3","title":"家电维修","create_time":"2017-01-11 00:00:00","type":"2"},{"id_introduce":"4","title":"家电维修","create_time":"2017-01-11 00:00:00","type":"2"},{"id_introduce":"5","title":"家电维修","create_time":"2017-01-11 00:00:00","type":"3"}]
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
         * id_introduce : 1
         * title : 家电维修
         * create_time : 2017-01-11 00:00:00
         * type : 1
         */

        private String id_introduce;
        private String title;
        private String create_time;
        private String type;

        public String getId_introduce() {
            return id_introduce;
        }

        public void setId_introduce(String id_introduce) {
            this.id_introduce = id_introduce;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
