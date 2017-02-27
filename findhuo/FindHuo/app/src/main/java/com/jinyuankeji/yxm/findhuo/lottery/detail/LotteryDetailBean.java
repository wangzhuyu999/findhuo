package com.jinyuankeji.yxm.findhuo.lottery.detail;

/**
 * Created by  yxiaomin on 2017/1/14 0014.
 */

public class LotteryDetailBean {


    /**
     * res : 10001
     * cache_update : 10000
     * data : {"id_lottery":"15","lotteryname":"彩票站1","img":"http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773136a5c92.png","imgsmall":"/Uploads/Lottery/20170112/58773136a60ae.png","tel":"024-2333333","province":"18","city":"244","address":"辽宁沈阳和平大街别斯路","create_time":"0000-00-00 00:00:00","content":"简介1简介1简介1简介1","status":"1","sort":"1","summary1":"简介1简介1简介1简介1","summary2":"简介1简介1简介1简介1","summary3":"简介1简介1简介1简介1"}
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
         * id_lottery : 15
         * lotteryname : 彩票站1
         * img : http://zhaohuo.jinyuankeji.net/Uploads/Lottery/20170112/58773136a5c92.png
         * imgsmall : /Uploads/Lottery/20170112/58773136a60ae.png
         * tel : 024-2333333
         * province : 18
         * city : 244
         * address : 辽宁沈阳和平大街别斯路
         * create_time : 0000-00-00 00:00:00
         * content : 简介1简介1简介1简介1
         * status : 1
         * sort : 1
         * summary1 : 简介1简介1简介1简介1
         * summary2 : 简介1简介1简介1简介1
         * summary3 : 简介1简介1简介1简介1
         */

        private String id_lottery;
        private String lotteryname;
        private String img;
        private String imgsmall;
        private String tel;
        private String province;
        private String city;
        private String address;
        private String create_time;
        private String content;
        private String status;
        private String sort;
        private String summary1;
        private String summary2;
        private String summary3;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImgsmall() {
            return imgsmall;
        }

        public void setImgsmall(String imgsmall) {
            this.imgsmall = imgsmall;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getSummary1() {
            return summary1;
        }

        public void setSummary1(String summary1) {
            this.summary1 = summary1;
        }

        public String getSummary2() {
            return summary2;
        }

        public void setSummary2(String summary2) {
            this.summary2 = summary2;
        }

        public String getSummary3() {
            return summary3;
        }

        public void setSummary3(String summary3) {
            this.summary3 = summary3;
        }
    }
}
