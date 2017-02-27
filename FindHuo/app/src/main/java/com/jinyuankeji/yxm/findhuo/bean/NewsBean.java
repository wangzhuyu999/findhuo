package com.jinyuankeji.yxm.findhuo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class NewsBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_notice":"1","content":"您于2017年1月16日购买的彩票已经出票","create_time":"2017-01-16 00:00:00"}]
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
         * id_notice : 1
         * content : 您于2017年1月16日购买的彩票已经出票
         * create_time : 2017-01-16 00:00:00
         */

        private String id_notice;
        private String content;
        private String create_time;

        public String getId_notice() {
            return id_notice;
        }

        public void setId_notice(String id_notice) {
            this.id_notice = id_notice;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
