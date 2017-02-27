package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_main_gv;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */

public class FindWorkHotBean {
    private String name;
    private int img;
    private int pos;

    public int getImg() {
        return img;
    }

    public void setImg(int img,int pos) {
        this.img = img;
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name,int pos) {
        this.name = name;
        this.pos = pos;
    }
}
