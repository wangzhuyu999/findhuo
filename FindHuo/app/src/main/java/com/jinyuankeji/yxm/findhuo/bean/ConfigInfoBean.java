package com.jinyuankeji.yxm.findhuo.bean;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class ConfigInfoBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : {"id_config":"1","content":"关于我们\\n\r\n关于我们\\n\r\n关于我们\\n\r\n关于我们\\n\r\n关于我们\\n\r\n关于我们\\n\r\n关于我们\\n\r\n关于我们\\n\t\t\t\t\t\t\t  "}
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
         * id_config : 1
         * content : 关于我们\n
         关于我们\n
         关于我们\n
         关于我们\n
         关于我们\n
         关于我们\n
         关于我们\n
         关于我们\n
         */

        private String id_config;
        private String content;

        public String getId_config() {
            return id_config;
        }

        public void setId_config(String id_config) {
            this.id_config = id_config;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
