package com.jinyuankeji.yxm.findhuo.bean;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class UserInfoBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : {"id_user":"1","name":"Vvvvfui","headimg":"/Uploads/avatar/20170206/58982f0d6bf74.png","birthday":"2017-02-06","amount":"1192.00","ticket_push":"1","url":"http://zhaohuo.jinyuankeji.net/Uploads/avatar/20170206/58982f0d6bf74.png"}
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
         * id_user : 1
         * name : Vvvvfui
         * headimg : /Uploads/avatar/20170206/58982f0d6bf74.png
         * birthday : 2017-02-06
         * amount : 1192.00
         * ticket_push : 1
         * url : http://zhaohuo.jinyuankeji.net/Uploads/avatar/20170206/58982f0d6bf74.png
         */

        private String id_user;
        private String name;
        private String headimg;
        private String birthday;
        private String amount;
        private String ticket_push;
        private String url;

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTicket_push() {
            return ticket_push;
        }

        public void setTicket_push(String ticket_push) {
            this.ticket_push = ticket_push;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
