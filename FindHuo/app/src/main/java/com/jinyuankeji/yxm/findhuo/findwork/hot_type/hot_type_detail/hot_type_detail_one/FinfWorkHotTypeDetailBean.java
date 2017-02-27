package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.hot_type_detail_one;

import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/16 0016.
 */

public class FinfWorkHotTypeDetailBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_introduce":"3","name":"孙先生","headimg":"http://zhaohuo.jinyuankeji.net","title":"家电维修","salary":"面议","service_area":"大东区","address":"0"},{"id_introduce":"4","name":"孙先生","headimg":"http://zhaohuo.jinyuankeji.net","title":"家电维修","salary":"面议","service_area":"大东区","address":"0"},{"id_introduce":"5","name":"孙先生","headimg":"http://zhaohuo.jinyuankeji.net","title":"家电维修","salary":"面议","service_area":"大东区","address":"小东路"},{"id_introduce":"6","name":"孙先生","headimg":"http://zhaohuo.jinyuankeji.net","title":"家电维修","salary":"面议","service_area":"大东区","address":"小东路"}]
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
         * id_introduce : 3
         * name : 孙先生
         * headimg : http://zhaohuo.jinyuankeji.net
         * title : 家电维修
         * salary : 面议
         * service_area : 大东区
         * address : 0
         */

        private String id_introduce;
        private String name;
        private String headimg;
        private String title;
        private String salary;
        private String service_area;
        private String address;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getService_area() {
            return service_area;
        }

        public void setService_area(String service_area) {
            this.service_area = service_area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
