package com.jinyuankeji.yxm.findhuo.lottery;

import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/10 0010.
 */

public class LotteryViewPagerBean {

    /**
     * res : 10001
     * cache_update : 10000
     * slider : [{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170112/587715358b301.png"},{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170109/58733361ec8c4.png"},{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170113/5878485853275.png"}]
     * lottery : [{"id_lottery":"15","lotteryname":"彩票站1","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773136a60ae.png","province":"18","city":"244","address":"辽宁沈阳和平大街别斯路"},{"id_lottery":"14","lotteryname":"彩票站2","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/587731cd8377d.png","province":"18","city":"244","address":"辽宁沈阳芭蕉区和平南路"},{"id_lottery":"13","lotteryname":"彩票站3","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/5877395ea23df.png","province":"18","city":"244","address":"辽宁沈阳南大路45号"},{"id_lottery":"1","lotteryname":"彩票站4","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/587739b771f6e.png","province":"18","city":"244","address":"辽宁沈阳和平大街别斯路65号3单元"},{"id_lottery":"2","lotteryname":"彩票站5","imgsmall":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773bf898198.png","province":"18","city":"244","address":"辽宁沈阳北京路56号。"}]
     */

    private int res;
    private int cache_update;
    private List<SliderBean> slider;
    private List<LotteryBean> lottery;

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

    public List<SliderBean> getSlider() {
        return slider;
    }

    public void setSlider(List<SliderBean> slider) {
        this.slider = slider;
    }

    public List<LotteryBean> getLottery() {
        return lottery;
    }

    public void setLottery(List<LotteryBean> lottery) {
        this.lottery = lottery;
    }

    public static class SliderBean {
        /**
         * imgurl : http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170112/587715358b301.png
         */

        private String imgurl;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }

    public static class LotteryBean {
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
