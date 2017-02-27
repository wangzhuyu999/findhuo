package com.jinyuankeji.yxm.findhuo.declare.city;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class BankAddress {
    private String aid;
    private List<BankAddress> city;
    private String code;
    private String name;
    private String pid;

    private ArrayList<String> provinceNameList;
    private ArrayList<ArrayList<String>> cityNameList;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public List<BankAddress> getCity() {
        return city;
    }

    public void setCity(List<BankAddress> city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public ArrayList<String> getProvinceNameList() {
        return provinceNameList;
    }

    public void setProvinceNameList(ArrayList<String> provinceNameList) {
        this.provinceNameList = provinceNameList;
    }

    public ArrayList<ArrayList<String>> getCityNameList() {
        return cityNameList;
    }

    public void setCityNameList(ArrayList<ArrayList<String>> cityNameList) {
        this.cityNameList = cityNameList;
    }
}
