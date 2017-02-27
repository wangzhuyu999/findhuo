package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.psychological;

/**
 * Created by  yxiaomin on 2017/1/17 0017.
 */

public class PsychologistDetailBean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : {"id_psychologist":"1","id_user":"1","name":"李慧珍","sex":"女","headimg":"http://zhaohuo.jinyuankeji.net","birthday":"1990-01-01","company":"永恒","work_time":"","good_at":"","certifica":"","weixin":"","tel":"","qq":"","create_time":"0000-00-00 00:00:00","status":"1","age":27}
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
         * id_psychologist : 1
         * id_user : 1
         * name : 李慧珍
         * sex : 女
         * headimg : http://zhaohuo.jinyuankeji.net
         * birthday : 1990-01-01
         * company : 永恒
         * work_time :
         * good_at :
         * certifica :
         * weixin :
         * tel :
         * qq :
         * create_time : 0000-00-00 00:00:00
         * status : 1
         * age : 27
         */

        private String id_psychologist;
        private String id_user;
        private String name;
        private String sex;
        private String headimg;
        private String birthday;
        private String company;
        private String work_time;
        private String good_at;
        private String certifica;
        private String weixin;
        private String tel;
        private String qq;
        private String create_time;
        private String status;
        private int age;

        public String getId_psychologist() {
            return id_psychologist;
        }

        public void setId_psychologist(String id_psychologist) {
            this.id_psychologist = id_psychologist;
        }

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getWork_time() {
            return work_time;
        }

        public void setWork_time(String work_time) {
            this.work_time = work_time;
        }

        public String getGood_at() {
            return good_at;
        }

        public void setGood_at(String good_at) {
            this.good_at = good_at;
        }

        public String getCertifica() {
            return certifica;
        }

        public void setCertifica(String certifica) {
            this.certifica = certifica;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
