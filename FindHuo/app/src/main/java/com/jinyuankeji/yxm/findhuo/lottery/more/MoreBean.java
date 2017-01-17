package com.jinyuankeji.yxm.findhuo.lottery.more;

import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/14 0014.
 */

public class MoreBean {


    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_lottery":"15","lotteryname":"彩票站1","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773136a60ae.png","province":"18","city":"244","address":"辽宁沈阳和平大街别斯路"},{"id_lottery":"14","lotteryname":"彩票站2","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/587731cd8377d.png","province":"18","city":"244","address":"辽宁沈阳芭蕉区和平南路"},{"id_lottery":"13","lotteryname":"彩票站3","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/5877395ea23df.png","province":"18","city":"244","address":"辽宁沈阳南大路45号"},{"id_lottery":"1","lotteryname":"彩票站4","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/587739b771f6e.png","province":"18","city":"244","address":"辽宁沈阳和平大街别斯路65号3单元"},{"id_lottery":"2","lotteryname":"彩票站5","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773bf898198.png","province":"18","city":"244","address":"辽宁沈阳北京路56号。"},{"id_lottery":"3","lotteryname":"彩票站6","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773c40c05dc.png","province":"18","city":"244","address":"辽宁沈阳贵和路34号"},{"id_lottery":"12","lotteryname":"彩票站8","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773d372f2a4.png","province":"18","city":"244","address":"辽宁沈阳兰州拉面市"},{"id_lottery":"16","lotteryname":"彩票站9","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773d8dea4d7.png","province":"18","city":"244","address":"辽宁沈阳安徽路787号"},{"id_lottery":"18","lotteryname":"彩票站10","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/587740e251af3.png","province":"18","city":"244","address":"辽宁沈阳信息路56号"},{"id_lottery":"19","lotteryname":"彩票站11","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/5877413b8eaa9.png","province":"18","city":"244","address":"辽宁沈阳海口路青年大街34号"},{"id_lottery":"20","lotteryname":"彩票站12","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/587741978bee8.png","province":"18","city":"244","address":"辽宁沈阳哈尔滨路2号"},{"id_lottery":"22","lotteryname":"彩票站14","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58774269b5f1b.png","province":"18","city":"244","address":"辽宁沈阳南宁街道33号"},{"id_lottery":"23","lotteryname":"彩票站15","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/587742c4836ff.png","province":"18","city":"244","address":"辽宁沈阳长沙街道45号"}]
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
         * id_lottery : 15
         * lotteryname : 彩票站1
         * imgsmall : http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773136a60ae.png
         * province : 18
         * city : 244
         * address : 辽宁沈阳和平大街别斯路
         */

        private String id_lottery;
        private String lotteryname;
        private String imgsmall;
        private String province;
        private String city;
        private String address;

        public String getId_lottery() {
            return id_lottery;
        }

        public void setId_lottery(String id_lottery) {
            this.id_lottery = id_lottery;
        }

        public String getLotteryname() {
            return lotteryname;
        }

        public void setLotteryname(String lotteryname) {
            this.lotteryname = lotteryname;
        }

        public String getImgsmall() {
            return imgsmall;
        }

        public void setImgsmall(String imgsmall) {
            this.imgsmall = imgsmall;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
