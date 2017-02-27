package com.jinyuankeji.yxm.findhuo.declare.city;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class LoadingAssets {
    /**
     * 获取省、市相关数据
     * @param context
     * @return
     */
    public static BankAddress loadingBankAddressInfo(Context context){
        StringBuffer sb = new StringBuffer();
        try{
            InputStream inputStream = getAssetsData(context, "city.json");
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader br = new BufferedReader(reader);

            String buffer = null;
            while ((buffer = br.readLine()) != null) {
                sb.append(buffer);
            }

            inputStream.close();
            br.close();
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return parseBankAddress(sb.toString());
    }

    /**
     * 解析JSON
     * @param json
     * @return
     */
    private static BankAddress parseBankAddress(String json){
        BankAddress bankAddress = new BankAddress();
        List<BankAddress> provinceList = new ArrayList<>();
        ArrayList<String> provinceNameList = new ArrayList<>();
        ArrayList<ArrayList<String>> cityNameList = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                BankAddress provinceBankAddress = new BankAddress();
                ArrayList<String> childrenCityNameList = new ArrayList<>();

                List<BankAddress> childrenBankAddress = new ArrayList<>();
                JSONArray childArray = jsonObject.optJSONArray("city");
                for (int j = 0; j < childArray.length(); j ++){
                    JSONObject childObject = childArray.getJSONObject(j);
                    BankAddress childBankAddress = new BankAddress();
                    String aid = childObject.optString("aid");
                    String code = childObject.optString("code");
                    String name = childObject.optString("name");
                    String pid = childObject.optString("pid");
                    childBankAddress.setAid(aid);
                    childBankAddress.setCode(code);
                    childBankAddress.setName(name);
                    childBankAddress.setPid(pid);
                    childrenBankAddress.add(childBankAddress);
                    childrenCityNameList.add(name);
                }

                String aid = jsonObject.optString("aid");
                String code = jsonObject.optString("code");
                String name = jsonObject.optString("name");
                String pid = jsonObject.optString("pid");
                provinceBankAddress.setAid(aid);
                provinceBankAddress.setCode(code);
                provinceBankAddress.setName(name);
                provinceBankAddress.setPid(pid);
                provinceBankAddress.setCity(childrenBankAddress);
                provinceList.add(provinceBankAddress);
                provinceNameList.add(name);
                cityNameList.add(childrenCityNameList);
            }
            bankAddress.setCity(provinceList);
            bankAddress.setProvinceNameList(provinceNameList);
            bankAddress.setCityNameList(cityNameList);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return bankAddress;
    }

    /**
     * 获取Assets下的文件
     * @param context
     * @param openFile
     * @return
     * @throws IOException
     */
    public static InputStream getAssetsData(Context context, String openFile) throws IOException {
        InputStream inputStream = context.getResources().getAssets().open(openFile);
        return inputStream;
    }
}
