package com.jinyuankeji.yxm.findhuo.tools.search;

import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/20 0020.
 */

public class Bean {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id_psychologist":"1","id_user":"1","name":"李慧珍","sex":"0","headimg":"http://zhaohuo.jinyuankeji.net/Uploads/avatar/20170109/58734dbc9a12d.png","birthday":"1990-01-01","province":"0","city":"0","company":"永恒","work_time":"1年","good_at":"发送到发送到发送到发送到发送到发送到\r\n发送到发送到发送到发送到发送到发送到\r\n发送到发送到发送到发送到发送到发送到\r\n发送到发送到发送到发送到发送到发送到\r\n发送到发送到发送到发送到发送到发送到","certifica":"/Uploads/avatar/20170109/58734dbc9a12d.png","weixin":"13212312ggg","tel":"183040959559","qq":"4324324234","create_time":"2010-10-20 10:20:10","status":"1"},{"id_psychologist":"9","id_user":"1","name":"李慧珍","sex":"0","headimg":"http://zhaohuo.jinyuankeji.net/Uploads/avatar/20170109/58734dbc9a12d.png","birthday":"1990-01-01","province":"0","city":"0","company":"永恒","work_time":"1年","good_at":"发送到发送到发送到发送到发送到发送到\r\n发送到发送到发送到发送到发送到发送到\r\n发送到发送到发送到发送到发送到发送到\r\n发送到发送到发送到发送到发送到发送到\r\n发送到发送到发送到发送到发送到发送到","certifica":"/Uploads/avatar/20170109/58734dbc9a12d.png","weixin":"13212312ggg","tel":"183040959559","qq":"4324324234","create_time":"2010-10-20 10:20:10","status":"1"},{"id_psychologist":"2","id_user":"1","name":"李慧珍1","sex":"0","headimg":"http://zhaohuo.jinyuankeji.net","birthday":"1990-01-01","province":"0","city":"0","company":"永恒","work_time":"","good_at":"","certifica":"","weixin":"","tel":"","qq":"","create_time":"0000-00-00 00:00:00","status":"1"},{"id_psychologist":"3","id_user":"1","name":"李慧珍2","sex":"0","headimg":"http://zhaohuo.jinyuankeji.net","birthday":"1990-01-01","province":"0","city":"0","company":"永恒","work_time":"","good_at":"","certifica":"","weixin":"","tel":"","qq":"","create_time":"0000-00-00 00:00:00","status":"1"},{"id_psychologist":"4","id_user":"1","name":"李慧珍3","sex":"0","headimg":"http://zhaohuo.jinyuankeji.net","birthday":"1990-01-01","province":"0","city":"0","company":"永恒","work_time":"","good_at":"","certifica":"","weixin":"","tel":"","qq":"","create_time":"0000-00-00 00:00:00","status":"1"},{"id_psychologist":"10","id_user":"1","name":"李慧珍1","sex":"0","headimg":"http://zhaohuo.jinyuankeji.net","birthday":"1990-01-01","province":"0","city":"0","company":"永恒","work_time":"","good_at":"","certifica":"","weixin":"","tel":"","qq":"","create_time":"0000-00-00 00:00:00","status":"1"},{"id_psychologist":"11","id_user":"1","name":"李慧珍3","sex":"0","headimg":"http://zhaohuo.jinyuankeji.net","birthday":"1990-01-01","province":"0","city":"0","company":"永恒","work_time":"","good_at":"","certifica":"","weixin":"","tel":"","qq":"","create_time":"0000-00-00 00:00:00","status":"1"}]
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
         * id_psychologist : 1
         * id_user : 1
         * name : 李慧珍
         * sex : 0
         * headimg : http://zhaohuo.jinyuankeji.net/Uploads/avatar/20170109/58734dbc9a12d.png
         * birthday : 1990-01-01
         * province : 0
         * city : 0
         * company : 永恒
         * work_time : 1年
         * good_at : 发送到发送到发送到发送到发送到发送到
         发送到发送到发送到发送到发送到发送到
         发送到发送到发送到发送到发送到发送到
         发送到发送到发送到发送到发送到发送到
         发送到发送到发送到发送到发送到发送到
         * certifica : /Uploads/avatar/20170109/58734dbc9a12d.png
         * weixin : 13212312ggg
         * tel : 183040959559
         * qq : 4324324234
         * create_time : 2010-10-20 10:20:10
         * status : 1
         */

        private String id_psychologist;
        private String id_user;
        private String name;
        private String sex;
        private String headimg;
        private String birthday;
        private String province;
        private String city;
        private String company;
        private String work_time;
        private String good_at;
        private String certifica;
        private String weixin;
        private String tel;
        private String qq;
        private String create_time;
        private String status;



        private String id_introduce;
        //        private String id_user;
//        private String name;
//        private String sex;
//        private String headimg;
//        private String tel;
        private String title;
        private String age;
        //        private String province;
//        private String city;
        private String address;
        private String id_icon;
        private String salary;
        private String content;
        private String type;
        private String service_area;
        private String work_year;
        //        private String create_time;
        private String province1;
        private String city1;
        private String address1;
        private String appointment_time;
        //        private String status;
        private String appointment;


        public String getId_introduce() {
            return id_introduce;
        }

        public void setId_introduce(String id_introduce) {
            this.id_introduce = id_introduce;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId_icon() {
            return id_icon;
        }

        public void setId_icon(String id_icon) {
            this.id_icon = id_icon;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getService_area() {
            return service_area;
        }

        public void setService_area(String service_area) {
            this.service_area = service_area;
        }

        public String getWork_year() {
            return work_year;
        }

        public void setWork_year(String work_year) {
            this.work_year = work_year;
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

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }

        public String getAppointment() {
            return appointment;
        }

        public void setAppointment(String appointment) {
            this.appointment = appointment;
        }

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
    }
}
