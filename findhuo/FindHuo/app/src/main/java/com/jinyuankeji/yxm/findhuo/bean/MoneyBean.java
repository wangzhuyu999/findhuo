package com.jinyuankeji.yxm.findhuo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/21 0021.
 */

public class MoneyBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_recharge":"1","create_time":"2017-01-16 00:00:00","money":"100.00"}]
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
         * id_recharge : 1
         * create_time : 2017-01-16 00:00:00
         * money : 100.00
         */

        private String id_recharge;
        private String create_time;
        private String money;

        public String getId_recharge() {
            return id_recharge;
        }

        public void setId_recharge(String id_recharge) {
            this.id_recharge = id_recharge;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
