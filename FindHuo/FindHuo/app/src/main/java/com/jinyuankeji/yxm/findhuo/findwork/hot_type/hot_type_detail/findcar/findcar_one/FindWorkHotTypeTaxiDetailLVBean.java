package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/4 0004.
 */

public class FindWorkHotTypeTaxiDetailLVBean {


    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_introduce":"5","name":"孙先生","headimg":"http://zhaohuo.jinyuankeji.net","province":"0","city":"244","address":"沈阳小东路","province1":"0","city1":"244","address1":"沈阳大东路","create_time":"2017-01-11 00:00:00","appointment":"发布时间：2017-01-11 00:00:00","appointment_time":"0000-00-00 00:00:00"},{"id_introduce":"6","name":"孙先生","headimg":"http://zhaohuo.jinyuankeji.net","province":"0","city":"244","address":"沈阳小东路","province1":"0","city1":"244","address1":"沈阳大东路","create_time":"2017-01-11 00:00:00","appointment":"预约时间：2017-02-22 00:00:00","appointment_time":"2017-02-22 00:00:00"}]
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
         * id_introduce : 5
         * name : 孙先生
         * headimg : http://zhaohuo.jinyuankeji.net
         * province : 0
         * city : 244
         * address : 沈阳小东路
         * province1 : 0
         * city1 : 244
         * address1 : 沈阳大东路
         * create_time : 2017-01-11 00:00:00
         * appointment : 发布时间：2017-01-11 00:00:00
         * appointment_time : 0000-00-00 00:00:00
         */

        private String id_introduce;
        private String name;
        private String headimg;
        private String province;
        private String city;
        private String address;
        private String province1;
        private String city1;
        private String address1;
        private String create_time;
        private String appointment;
        private String appointment_time;
        private String id_psychologist;
        private String company;

        public String getId_psychologist() {
            return id_psychologist;
        }

        public void setId_psychologist(String id_psychologist) {
            this.id_psychologist = id_psychologist;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getId_introduce() {
            return id_introduce;
        }

        public void setId_introduce(String id_introduce) {
            this.id_introduce = id_introduce;
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

        public String getProvince1() {
            return province1;
        }

        public void setProvince1(String province1) {
            this.province1 = province1;
        }

        public String getCity1() {
            return city1;
        }

        public void setCity1(String city1) {
            this.city1 = city1;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getAppointment() {
            return appointment;
        }

        public void setAppointment(String appointment) {
            this.appointment = appointment;
        }

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }
    }
}
