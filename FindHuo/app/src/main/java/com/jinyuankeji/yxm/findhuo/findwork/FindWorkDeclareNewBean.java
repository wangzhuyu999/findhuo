package com.jinyuankeji.yxm.findhuo.findwork;

import java.util.List;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */

public class FindWorkDeclareNewBean {

    /**
     * res : 10001
     * cache_update : 10000
     * slider : [{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170112/587715a1dda38.png"},{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170112/58771022d0bb3.png"},{"imgurl":"http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170113/587848bc23c46.png"}]
     * introduce : [{"id_introduce":"1","name":"孙先生","headimg":"http://zhaohuo.jinyuankeji.net","title":"家电维修","salary":"面议","service_area":"大东区","address":"0"},{"id_introduce":"2","name":"孙先生","headimg":"http://zhaohuo.jinyuankeji.net","title":"家电维修","salary":"面议","service_area":"大东区","address":"0"}]
     * icon : [{"id_icon":"25","title":"技工","iconurl_an":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/5874483174788.png","iconurl_ios":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/587448317432c.png","iconurl_an_onclick":"http://zhaohuo.jinyuankeji.net","iconurl_ios_onclick":"http://zhaohuo.jinyuankeji.net"},{"id_icon":"26","title":"促销导购","iconurl_an":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/5874485170741.png","iconurl_ios":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/58744851702fa.png","iconurl_an_onclick":"http://zhaohuo.jinyuankeji.net","iconurl_ios_onclick":"http://zhaohuo.jinyuankeji.net"},{"id_icon":"27","title":"钟点工","iconurl_an":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/58744869a1f1f.png","iconurl_ios":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/58744869a1b51.png","iconurl_an_onclick":"http://zhaohuo.jinyuankeji.net","iconurl_ios_onclick":"http://zhaohuo.jinyuankeji.net"},{"id_icon":"28","title":"家教","iconurl_an":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/58744885df3ee.png","iconurl_ios":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/58744885dedad.png","iconurl_an_onclick":"http://zhaohuo.jinyuankeji.net","iconurl_ios_onclick":"http://zhaohuo.jinyuankeji.net"},{"id_icon":"29","title":"送货","iconurl_an":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/587448a24676f.png","iconurl_ios":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/587448a2463af.png","iconurl_an_onclick":"http://zhaohuo.jinyuankeji.net","iconurl_ios_onclick":"http://zhaohuo.jinyuankeji.net"},{"id_icon":"30","title":"心理咨询","iconurl_an":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/587448bbc4242.png","iconurl_ios":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/587448bbc3e60.png","iconurl_an_onclick":"http://zhaohuo.jinyuankeji.net","iconurl_ios_onclick":"http://zhaohuo.jinyuankeji.net"},{"id_icon":"31","title":"其他","iconurl_an":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/58744908a0d66.png","iconurl_ios":"http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/58744908a0924.png","iconurl_an_onclick":"http://zhaohuo.jinyuankeji.net","iconurl_ios_onclick":"http://zhaohuo.jinyuankeji.net"}]
     */

    private int res;
    private int cache_update;
    private List<SliderBean> slider;
    private List<IntroduceBean> introduce;
    private List<IconBean> icon;

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

    public List<IntroduceBean> getIntroduce() {
        return introduce;
    }

    public void setIntroduce(List<IntroduceBean> introduce) {
        this.introduce = introduce;
    }

    public List<IconBean> getIcon() {
        return icon;
    }

    public void setIcon(List<IconBean> icon) {
        this.icon = icon;
    }

    public static class SliderBean {
        /**
         * imgurl : http://zhaohuo.jinyuankeji.net/Uploads/Slider/20170112/587715a1dda38.png
         */

        private String imgurl;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }

    public static class IntroduceBean {
        /**
         * id_introduce : 1
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

    public static class IconBean {
        /**
         * id_icon : 25
         * title : 技工
         * iconurl_an : http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/5874483174788.png
         * iconurl_ios : http://zhaohuo.jinyuankeji.net/Uploads/Icon/20170110/587448317432c.png
         * iconurl_an_onclick : http://zhaohuo.jinyuankeji.net
         * iconurl_ios_onclick : http://zhaohuo.jinyuankeji.net
         */

        private String id_icon;
        private String title;
        private String iconurl_an;
        private String iconurl_ios;
        private String iconurl_an_onclick;
        private String iconurl_ios_onclick;

        public String getId_icon() {
            return id_icon;
        }

        public void setId_icon(String id_icon) {
            this.id_icon = id_icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIconurl_an() {
            return iconurl_an;
        }

        public void setIconurl_an(String iconurl_an) {
            this.iconurl_an = iconurl_an;
        }

        public String getIconurl_ios() {
            return iconurl_ios;
        }

        public void setIconurl_ios(String iconurl_ios) {
            this.iconurl_ios = iconurl_ios;
        }

        public String getIconurl_an_onclick() {
            return iconurl_an_onclick;
        }

        public void setIconurl_an_onclick(String iconurl_an_onclick) {
            this.iconurl_an_onclick = iconurl_an_onclick;
        }

        public String getIconurl_ios_onclick() {
            return iconurl_ios_onclick;
        }

        public void setIconurl_ios_onclick(String iconurl_ios_onclick) {
            this.iconurl_ios_onclick = iconurl_ios_onclick;
        }
    }
}
