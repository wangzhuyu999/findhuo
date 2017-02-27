package com.jinyuankeji.yxm.findhuo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public class BuyLotteryFragmentSBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_order":"2","goodsname":"彩票","create_time":"2016-01-11 11:38:54","payment_price":"24.00","img":"/Uploads/Lottery/20170117/587d7728b0404.png"},{"id_order":"3","goodsname":"彩票","create_time":"2016-01-11 11:38:54","payment_price":"24.00","img":"/Uploads/Lottery/20170117/587d78414fd7a.png"}]
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
         * id_order : 2
         * goodsname : 彩票
         * create_time : 2016-01-11 11:38:54
         * payment_price : 24.00
         * img : /Uploads/Lottery/20170117/587d7728b0404.png
         */

        private String id_order;
        private String goodsname;
        private String create_time;
        private String payment_price;
        private String img;

        public String getId_order() {
            return id_order;
        }

        public void setId_order(String id_order) {
            this.id_order = id_order;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPayment_price() {
            return payment_price;
        }

        public void setPayment_price(String payment_price) {
            this.payment_price = payment_price;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
