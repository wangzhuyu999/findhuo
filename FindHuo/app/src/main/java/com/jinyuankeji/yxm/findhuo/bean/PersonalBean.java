package com.jinyuankeji.yxm.findhuo.bean;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class PersonalBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : {"id_user":"1","id_salesman":"1","account":"15998386324","pwd":"70873e8580c9900986939611618d7b1e","paypwd":"","name":"用户1","nickname":"哈哈11","headimg":"/Uploads/avatar/20170109/58734dbc9a12d.png","birthday":"2011-01-20","sex":"1","amount":"1232.00","ticket_push":"0","order_push":"0","status":"1","create_time":"2017-01-10 15:52:55","update_time":"2017-01-10 15:58:07"}
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
         * id_salesman : 1
         * account : 15998386324
         * pwd : 70873e8580c9900986939611618d7b1e
         * paypwd :
         * name : 用户1
         * nickname : 哈哈11
         * headimg : /Uploads/avatar/20170109/58734dbc9a12d.png
         * birthday : 2011-01-20
         * sex : 1
         * amount : 1232.00
         * ticket_push : 0
         * order_push : 0
         * status : 1
         * create_time : 2017-01-10 15:52:55
         * update_time : 2017-01-10 15:58:07
         */

        private String id_user;
        private String id_salesman;
        private String account;
        private String pwd;
        private String paypwd;
        private String name;
        private String nickname;
        private String headimg;
        private String birthday;
        private String sex;
        private String amount;
        private String ticket_push;
        private String order_push;
        private String status;
        private String create_time;
        private String update_time;

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getId_salesman() {
            return id_salesman;
        }

        public void setId_salesman(String id_salesman) {
            this.id_salesman = id_salesman;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getPaypwd() {
            return paypwd;
        }

        public void setPaypwd(String paypwd) {
            this.paypwd = paypwd;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getOrder_push() {
            return order_push;
        }

        public void setOrder_push(String order_push) {
            this.order_push = order_push;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
