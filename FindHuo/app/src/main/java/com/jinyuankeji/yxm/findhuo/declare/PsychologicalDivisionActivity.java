package com.jinyuankeji.yxm.findhuo.declare;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.Util.AndroidUtil;
import com.jinyuankeji.yxm.findhuo.Util.ImageTools;
import com.jinyuankeji.yxm.findhuo.bean.FindWorkBean;
import com.jinyuankeji.yxm.findhuo.bean.PersonalBean;
import com.jinyuankeji.yxm.findhuo.bean.PsychologistBean;
import com.jinyuankeji.yxm.findhuo.bean.UploadPictureBean;
import com.jinyuankeji.yxm.findhuo.my.PersonalActivity;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.JsonUtils;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.jinyuankeji.yxm.findhuo.R.id.showdate;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.psychologist;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.workerFind;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class PsychologicalDivisionActivity extends Activity{
    private ImageView back;
    private UploadPictureBean bean1,bean2;
    private Spinner province_spinner;
    private Spinner city_spinner;
    private Integer provinceId, cityId;
    private String strProvince, strCity, strCounty;

    private int[] city = {R.array.beijin_province_item, R.array.tianjin_province_item, R.array.heibei_province_item, R.array.shanxi1_province_item, R.array.neimenggu_province_item, R.array.liaoning_province_item, R.array.jilin_province_item, R.array.heilongjiang_province_item, R.array.shanghai_province_item, R.array.jiangsu_province_item, R.array.zhejiang_province_item, R.array.anhui_province_item, R.array.fujian_province_item, R.array.jiangxi_province_item, R.array.shandong_province_item, R.array.henan_province_item, R.array.hubei_province_item, R.array.hunan_province_item, R.array.guangdong_province_item,  R.array.guangxi_province_item, R.array.hainan_province_item, R.array.chongqing_province_item, R.array.sichuan_province_item, R.array.guizhou_province_item, R.array.yunnan_province_item, R.array.xizang_province_item, R.array.shanxi2_province_item, R.array.gansu_province_item, R.array.qinghai_province_item, R.array.linxia_province_item, R.array.xinjiang_province_item, R.array.hongkong_province_item, R.array.aomen_province_item, R.array.taiwan_province_item};
    private int[] countyOfBeiJing = {R.array.beijin_city_item};
    private int[] countyOfTianJing = {R.array.tianjin_city_item};
    private int[] countyOfHeBei = {R.array.shijiazhuang_city_item, R.array.tangshan_city_item, R.array.qinghuangdao_city_item, R.array.handan_city_item, R.array.xingtai_city_item, R.array.baoding_city_item, R.array.zhangjiakou_city_item, R.array.chengde_city_item, R.array.cangzhou_city_item, R.array.langfang_city_item, R.array.hengshui_city_item};
    private int[] countyOfShanXi1 = {R.array.taiyuan_city_item, R.array.datong_city_item, R.array.yangquan_city_item, R.array.changzhi_city_item, R.array.jincheng_city_item, R.array.shuozhou_city_item, R.array.jinzhong_city_item, R.array.yuncheng_city_item, R.array.xinzhou_city_item, R.array.linfen_city_item, R.array.lvliang_city_item};
    private int[] countyOfNeiMengGu = {R.array.huhehaote_city_item, R.array.baotou_city_item, R.array.wuhai_city_item, R.array.chifeng_city_item, R.array.tongliao_city_item, R.array.eerduosi_city_item, R.array.hulunbeier_city_item, R.array.bayannaoer_city_item, R.array.wulanchabu_city_item, R.array.xinganmeng_city_item, R.array.xilinguolemeng_city_item, R.array.alashanmeng_city_item};
    private int[] countyOfLiaoNing = {R.array.shenyang_city_item, R.array.dalian_city_item, R.array.anshan_city_item, R.array.wushun_city_item, R.array.benxi_city_item, R.array.dandong_city_item, R.array.liaoning_jinzhou_city_item, R.array.yingkou_city_item, R.array.fuxin_city_item, R.array.liaoyang_city_item, R.array.panjin_city_item, R.array.tieling_city_item, R.array.zhaoyang_city_item, R.array.huludao_city_item};
    private int[] countyOfJiLin = {R.array.changchun_city_item, R.array.jilin_city_item, R.array.siping_city_item, R.array.liaoyuan_city_item, R.array.tonghua_city_item, R.array.baishan_city_item, R.array.songyuan_city_item, R.array.baicheng_city_item, R.array.yanbian_city_item};
    private int[] countyOfHeiLongJiang = {R.array.haerbing_city_item, R.array.qiqihaer_city_item, R.array.jixi_city_item, R.array.hegang_city_item, R.array.shuangyashan_city_item, R.array.daqing_city_item, R.array.heilongjiang_yichun_city_item, R.array.jiamusi_city_item, R.array.qitaihe_city_item, R.array.mudanjiang_city_item, R.array.heihe_city_item, R.array.suihua_city_item, R.array.daxinganling_city_item};
    private int[] countyOfShangHai = {R.array.shanghai_city_item};

    private int[] countyOfJiangSu = {R.array.nanjing_city_item, R.array.wuxi_city_item, R.array.xuzhou_city_item, R.array.changzhou_city_item, R.array.nanjing_suzhou_city_item, R.array.nantong_city_item, R.array.lianyungang_city_item, R.array.huaian_city_item, R.array.yancheng_city_item, R.array.yangzhou_city_item, R.array.zhenjiang_city_item, R.array.jiangsu_taizhou_city_item, R.array.suqian_city_item};
    private int[] countyOfZheJiang = {R.array.hangzhou_city_item, R.array.ningbo_city_item, R.array.wenzhou_city_item, R.array.jiaxing_city_item, R.array.huzhou_city_item, R.array.shaoxing_city_item, R.array.jinhua_city_item, R.array.quzhou_city_item, R.array.zhoushan_city_item, R.array.zejiang_huzhou_city_item, R.array.lishui_city_item};
    private int[] countyOfAnHui = {R.array.hefei_city_item, R.array.wuhu_city_item, R.array.bengbu_city_item, R.array.huainan_city_item, R.array.maanshan_city_item, R.array.huaibei_city_item, R.array.tongling_city_item, R.array.anqing_city_item, R.array.huangshan_city_item, R.array.chuzhou_city_item, R.array.fuyang_city_item, R.array.anhui_suzhou_city_item, R.array.chaohu_city_item, R.array.luan_city_item, R.array.haozhou_city_item, R.array.chizhou_city_item, R.array.xuancheng_city_item};
    private int[] countyOfFuJian = {R.array.huzhou_city_item, R.array.xiamen_city_item, R.array.putian_city_item, R.array.sanming_city_item, R.array.quanzhou_city_item, R.array.zhangzhou_city_item, R.array.nanp_city_item, R.array.longyan_city_item, R.array.ningde_city_item};
    private int[] countyOfJiangXi = {R.array.nanchang_city_item, R.array.jingdezhen_city_item, R.array.pingxiang_city_item, R.array.jiujiang_city_item, R.array.xinyu_city_item, R.array.yingtan_city_item, R.array.ganzhou_city_item, R.array.jian_city_item, R.array.jiangxi_yichun_city_item, R.array.jiangxi_wuzhou_city_item, R.array.shangrao_city_item};
    private int[] countyOfShanDong = {R.array.jinan_city_item, R.array.qingdao_city_item, R.array.zaobo_city_item, R.array.zaozhuang_city_item, R.array.dongying_city_item, R.array.yantai_city_item, R.array.weifang_city_item, R.array.jining_city_item, R.array.taian_city_item, R.array.weihai_city_item, R.array.rizhao_city_item, R.array.laiwu_city_item, R.array.linxi_city_item, R.array.dezhou_city_item, R.array.liaocheng_city_item, R.array.shandong_bingzhou_city_item, R.array.heze_city_item};
    private int[] countyOfHeNan = {R.array.zhenshou_city_item, R.array.kaifang_city_item, R.array.luoyang_city_item, R.array.kaipingshan_city_item, R.array.anyang_city_item, R.array.hebi_city_item, R.array.xinxiang_city_item, R.array.jiaozuo_city_item, R.array.buyang_city_item, R.array.xuchang_city_item, R.array.leihe_city_item, R.array.sanmenxia_city_item, R.array.nanyang_city_item, R.array.shangqiu_city_item, R.array.xinyang_city_item, R.array.zhoukou_city_item, R.array.zhumadian_city_item};
    private int[] countyOfHuBei = {R.array.wuhan_city_item, R.array.huangshi_city_item, R.array.shiyan_city_item, R.array.yichang_city_item, R.array.xiangpan_city_item, R.array.erzhou_city_item, R.array.jinmen_city_item, R.array.xiaogan_city_item, R.array.hubei_jinzhou_city_item, R.array.huanggang_city_item, R.array.xianning_city_item, R.array.suizhou_city_item, R.array.enshi_city_item, R.array.shenglongjia_city_item};

    private int[] countyOfHuNan = {R.array.changsha_city_item, R.array.zhuzhou_city_item, R.array.xiangtan_city_item, R.array.hengyang_city_item, R.array.shaoyang_city_item, R.array.yueyang_city_item, R.array.changde_city_item, R.array.zhangjiajie_city_item, R.array.yiyang_city_item, R.array.hunan_bingzhou_city_item, R.array.yongzhou_city_item, R.array.huaihua_city_item, R.array.loudi_city_item, R.array.xiangxi_city_item};
    private int[] countyOfGuangDong = {R.array.guangzhou_city_item, R.array.shaoguan_city_item, R.array.shenzhen_city_item, R.array.zhuhai_city_item, R.array.shantou_city_item, R.array.foshan_city_item, R.array.jiangmen_city_item, R.array.zhangjiang_city_item, R.array.maoming_city_item, R.array.zhaoqing_city_item, R.array.huizhou_city_item, R.array.meizhou_city_item, R.array.shanwei_city_item, R.array.heyuan_city_item, R.array.yangjiang_city_item, R.array.qingyuan_city_item, R.array.dongguan_city_item, R.array.zhongshan_city_item, R.array.chaozhou_city_item, R.array.jiyang_city_item, R.array.yunfu_city_item};
    private int[] countyOfGuangXi = {R.array.nanning_city_item, R.array.liuzhou_city_item, R.array.guilin_city_item, R.array.guangxi_wuzhou_city_item, R.array.beihai_city_item, R.array.fangchenggang_city_item, R.array.qinzhou_city_item, R.array.guigang_city_item, R.array.yuelin_city_item, R.array.baise_city_item, R.array.hezhou_city_item, R.array.hechi_city_item, R.array.laibing_city_item, R.array.chuangzuo_city_item};
    private int[] countyOfHaiNan = {R.array.haikou_city_item, R.array.sanya_city_item};
    private int[] countyOfChongQing = {R.array.chongqing_city_item};
    private int[] countyOfSiChuan = {R.array.chengdu_city_item, R.array.zigong_city_item, R.array.panzhihua_city_item, R.array.luzhou_city_item, R.array.deyang_city_item, R.array.mianyang_city_item, R.array.guangyuan_city_item, R.array.suining_city_item, R.array.neijiang_city_item, R.array.leshan_city_item, R.array.nanchong_city_item, R.array.meishan_city_item, R.array.yibing_city_item, R.array.guangan_city_item, R.array.dazhou_city_item, R.array.yaan_city_item, R.array.bazhong_city_item, R.array.ziyang_city_item, R.array.abei_city_item, R.array.ganmu_city_item, R.array.liangshan_city_item};
    private int[] countyOfGuiZhou = {R.array.guiyang_city_item, R.array.lupanshui_city_item, R.array.zhunyi_city_item, R.array.anshun_city_item, R.array.tongren_city_item, R.array.qingxinan_city_item, R.array.biji_city_item, R.array.qingdongnan_city_item, R.array.qingnan_city_item};
    private int[] countyOfYunNan = {R.array.kunming_city_item, R.array.qujing_city_item, R.array.yuexi_city_item, R.array.baoshan_city_item, R.array.zhaotong_city_item, R.array.lijiang_city_item, R.array.simao_city_item, R.array.lingcang_city_item, R.array.chuxiong_city_item, R.array.honghe_city_item, R.array.wenshan_city_item, R.array.xishuangbanna_city_item, R.array.dali_city_item, R.array.dehuang_city_item, R.array.nujiang_city_item, R.array.diqing_city_item};
    private int[] countyOfXiZang = {R.array.lasa_city_item, R.array.changdu_city_item, R.array.shannan_city_item, R.array.rgeze_city_item, R.array.naqu_city_item, R.array.ali_city_item, R.array.linzhi_city_item};

    private int[] countyOfShanXi2 = {R.array.xian_city_item, R.array.tongchuan_city_item, R.array.baoji_city_item, R.array.xianyang_city_item, R.array.weinan_city_item, R.array.yanan_city_item, R.array.hanzhong_city_item, R.array.yulin_city_item, R.array.ankang_city_item, R.array.shangluo_city_item};
    private int[] countyOfGanSu = {R.array.lanzhou_city_item, R.array.jiayuguan_city_item, R.array.jinchang_city_item, R.array.baiyin_city_item, R.array.tianshui_city_item, R.array.wuwei_city_item, R.array.zhangyue_city_item, R.array.pingliang_city_item, R.array.jiuquan_city_item, R.array.qingyang_city_item, R.array.dingxi_city_item, R.array.longnan_city_item, R.array.linxia_city_item, R.array.gannan_city_item};
    private int[] countyOfQingHai = {R.array.xining_city_item, R.array.haidong_city_item, R.array.haibai_city_item, R.array.huangnan_city_item, R.array.hainan_city_item, R.array.guluo_city_item, R.array.yushu_city_item, R.array.haixi_city_item};
    private int[] countyOfNingXia = {R.array.yinchuan_city_item, R.array.shizuishan_city_item, R.array.wuzhong_city_item, R.array.guyuan_city_item, R.array.zhongwei_city_item};
    private int[] countyOfXinJiang = {R.array.wulumuqi_city_item, R.array.kelamayi_city_item, R.array.tulyfan_city_item, R.array.hami_city_item, R.array.changji_city_item, R.array.boertala_city_item, R.array.bayinguolen_city_item, R.array.akesu_city_item, R.array.kemuleisu_city_item, R.array.geshen_city_item, R.array.hetian_city_item, R.array.yili_city_item, R.array.tacheng_city_item, R.array.aleitai_city_item, R.array.shihezi_city_item, R.array.alaer_city_item, R.array.tumushihe_city_item, R.array.wujiaqu_city_item};
    private int[] countyOfHongKong = {};
    private int[] countyOfAoMen = {};
    private int[] countyOfTaiWan = {};

    private ArrayAdapter<CharSequence> province_adapter;
    private ArrayAdapter<CharSequence> city_adapter;
    private ArrayAdapter<CharSequence> county_adapter;
    private RadioButton rb,rb2;
    private String rbb,rbb2;
    private String Phone;
    private EditText name,company, work_time,weixin,tel,qq,good_at;
    private PsychologistBean psychologistBean;

    private EditText showDate = null;
    private LinearLayout pickDate = null;

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_TIMEPICK = 2;
    private static final int TIME_DIALOG_ID = 3;

    private int mYear;
    private int mMonth;
    private int mDay;
    private  int la;

    private int ir;

    /***
     *
     * @param/photo
     */
    private ImageView img,img2;
    private Button btnUpload;
    private HttpUtils httpUtils;
    private String URL="http://zhaohuo.jinyuankeji.net/api.php/App/uploadPicture/Psychologist/";
    private String URL2="http://zhaohuo.jinyuankeji.net/api.php/App/uploadPicture/Pcard/";

    private String[] items = { "拍照", "相册" };
    private String title = "选择照片";

    private static final int PHOTO_CARMERA = 1;
    private static final int PHOTO_PICK = 2;
    private static final int PHOTO_CUT = 3;
    // 创建一个以当前系统时间为名称的文件，防止重复
    private File tempFile = new File(Environment.getExternalStorageDirectory(),
            getPhotoFileName());
    private File tempFile2 = new File(Environment.getExternalStorageDirectory(),
            getPhotoFileName2());

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("'PNG'_yyyyMMdd_HHmmss");
        return sdf.format(date) + ".png";
    }
    private String getPhotoFileName2() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf2 = new SimpleDateFormat("'PNG'_yyyyMMdd_HHmmss");
        return sdf2.format(date) + ".png";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psychological_division);

        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user",Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");

        initializeViews();

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        setDateTime();
        initView();


        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(clickListener);
        img2 = (ImageView) findViewById(R.id.img2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog2 = AndroidUtil.getListDialogBuilder(
                        PsychologicalDivisionActivity.this, items, title, dialogListener2);

                dialog2.show();
                la = 2;

            }
        });
        btnUpload = (Button) findViewById(R.id.button5);
        btnUpload.setOnClickListener(clickListener);
        httpUtils=new HttpUtils(10000);

        name = (EditText)findViewById(R.id.editText_name);
        company = (EditText)findViewById(R.id.company);
        work_time = (EditText)findViewById(R.id. work_time);
        weixin = (EditText)findViewById(R.id.weixin);
        tel = (EditText)findViewById(R.id.tel);
        qq = (EditText)findViewById(R.id.qq);
        good_at = (EditText)findViewById(R.id.good_at);


        loadSpinner();
        //根据ID找到RadioGroup实例
        RadioGroup group = (RadioGroup)this.findViewById(R.id.radioGroup);
        //绑定一个匿名监听器
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                // TODO Auto-generated method stub
                //获取变更后的选中项的ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                rb = (RadioButton)PsychologicalDivisionActivity.this.findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                rbb =  rb.getText().toString();
                if(rbb == "男"){
                    ir = 1;
                }else {
                    ir = 2;
                }

            }
        });


    }
    private void loadSpinner()
    {
        province_spinner = (Spinner) findViewById(R.id.province1);
        province_spinner.setPrompt("请选择省份");
        province_adapter = ArrayAdapter.createFromResource(this, R.array.province_item, android.R.layout.simple_spinner_item);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        province_spinner.setAdapter(province_adapter);
        //select(province_spinner, province_adapter, R.array.province_item);
        province_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3)
            {
                provinceId = province_spinner.getSelectedItemPosition();
                strProvince = province_spinner.getSelectedItem().toString();
                city_spinner = (Spinner) findViewById(R.id.city1);
                if(true)
                {
                    Log.v("test", "province: "+province_spinner.getSelectedItem().toString()+provinceId.toString());
                    city_spinner = (Spinner) findViewById(R.id.city1);
                    city_spinner.setPrompt("请选择城市");
                    select(city_spinner, city_adapter, city[provinceId]);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }

    private void select(Spinner spin, ArrayAdapter<CharSequence> adapter, int arry)
    {
        adapter = ArrayAdapter.createFromResource(this, arry, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        //spin.setSelection(0,true);
    }
    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        // params.addBodyParameter("account","15998386324");
        params.addBodyParameter("account",Phone);
        params.addBodyParameter("name", name.getText().toString().trim());
        params.addBodyParameter("sex", ir + " ");
        params.addBodyParameter("headimg", Contants.img);
        params.addBodyParameter("birthday",showDate.getText().toString().trim() );

        params.addBodyParameter("province", strProvince);
        params.addBodyParameter("city", city_spinner.getSelectedItem().toString());

        params.addBodyParameter("company", company.getText().toString());
        params.addBodyParameter("work_time", work_time.getText().toString().trim());

        params.addBodyParameter("tel", tel.getText().toString().trim());
        params.addBodyParameter("certifica", Contants.img2);
        params.addBodyParameter("weixin", weixin.getText().toString().trim());
        params.addBodyParameter("qq", qq.getText().toString().trim());
        params.addBodyParameter("good_at", good_at.getText().toString().trim());


//
//        Log.e("account"," " + Phone);
//        Log.e("name"," " + name.getText().toString());
//        Log.e("sex"," " + rbb);
          Log.e("headimg"," " + Contants.img);
          Log.e("certifica"," " + Contants.img2);
//        Log.e("title"," " + title_edittext.getText().toString());
//        Log.e("province"," " + strProvince);
//        Log.e("city"," " + city_spinner.getSelectedItem().toString());
//        Log.e("address"," " + address.getText().toString().trim());
//        Log.e("id_icon"," " + industry.getSelectedItem().toString());
//        Log.e("tel"," " + tel.getText().toString().trim());
//        Log.e("salary"," " + salary.getText().toString());
//        Log.e("content"," " + content.getText().toString());


        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + psychologist, params,
                new RequestCallBack<String>() {
                    // 请求接口失败 arg1 为后台返回的错误信息
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败",
                                "111111111111111111111 error: "
                                        + arg1.toString());
                    }

                    // 请求接口成功 arg0.tostring 为后台返回的信息
                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {

                        if (arg0.result.toString().equals("0")) {

                        } else {

                            Log.e("发布找活请求成功",
                                    "999999999999999999999999999 onSuccess"
                                            + arg0.result.toString());

                            //getList(arg0.result.toString());// 请求返回的数据，json解析

                            String json = arg0.result.toString();

                            Gson gson = new Gson();
                            psychologistBean = gson.fromJson(json, PsychologistBean.class);

                            int res = psychologistBean.getRes();

                            if(res == 10001){
                                Toast.makeText(PsychologicalDivisionActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                            }if(res == 10005){
                                Toast.makeText(PsychologicalDivisionActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                            }if(res == 10002) {
                                Toast.makeText(PsychologicalDivisionActivity.this, "心理师申请提交失败 ", Toast.LENGTH_SHORT).show();
                            }if(res == 10003) {
                                Toast.makeText(PsychologicalDivisionActivity.this, "心理师提交的数据必须全部填写", Toast.LENGTH_SHORT).show();
                            }if(res == 10004) {
                                Toast.makeText(PsychologicalDivisionActivity.this, "该手机号的账号不存在", Toast.LENGTH_SHORT).show();
                            }if(res == 10000) {
                                Toast.makeText(PsychologicalDivisionActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });


    }



    /***
     *photo
     */

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img:
                    AlertDialog.Builder dialog = AndroidUtil.getListDialogBuilder(
                            PsychologicalDivisionActivity.this, items, title, dialogListener);

                    dialog.show();
                    la = 1;
                    break;

                case R.id.button5:

                    upload();
                    upload2();

                    request();
                    break;

                default:

                    break;
            }

        }
    };


    // 上传文件到服务器
    protected void upload() {
        RequestParams params=new RequestParams();
        //params.addBodyParameter(tempFile.getPath().replace("/", ""), tempFile);
        params.addBodyParameter("savePath", tempFile);
        httpUtils.send(HttpRequest.HttpMethod.POST,URL, params,new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException e, String msg) {
                Toast.makeText(PsychologicalDivisionActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", e.getExceptionCode() + "====="
                        + msg);
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //Toast.makeText(PersonalActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "====upload_error====="
                        + responseInfo.result);

                String json = responseInfo.result.toString();

                bean1 = JsonUtils.getJtoC(json, UploadPictureBean.class);
                String image = bean1.getData().getHeadimg().toString();
                Contants.img = image;

            }
        });
    }

    // 上传文件到服务器2
    protected void upload2() {
        RequestParams params=new RequestParams();
        //params.addBodyParameter(tempFile.getPath().replace("/", ""), tempFile);
        params.addBodyParameter("savePath", tempFile2);
        httpUtils.send(HttpRequest.HttpMethod.POST,URL2, params,new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException e, String msg) {
                Toast.makeText(PsychologicalDivisionActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", e.getExceptionCode() + "====="
                        + msg);
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //Toast.makeText(PersonalActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                Log.e("h", "====upload_error====="
                        + responseInfo.result);

                String json = responseInfo.result.toString();

                bean2 = JsonUtils.getJtoC(json, UploadPictureBean.class);
                String image2 = bean2.getData().getHeadimg().toString();
                Contants.img2 = image2;


            }
        });
    }

    private android.content.DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case 0:
                    // 调用拍照
                    startCamera(dialog);
                    break;
                case 1:
                    // 调用相册
                    startPick(dialog);
                    break;

                default:
                    break;
            }
        }
    };
    private android.content.DialogInterface.OnClickListener dialogListener2 = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog2, int which) {
            switch (which) {
                case 0:
                    // 调用拍照
                    startCamera(dialog2);
                    break;
                case 1:
                    // 调用相册
                    startPick(dialog2);
                    break;

                default:
                    break;
            }
        }
    };



    // 调用系统相机
    protected void startCamera(DialogInterface dialog) {
        dialog.dismiss();
        // 调用系统的拍照功能
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("camerasensortype", 2); // 调用前置摄像头
        intent.putExtra("autofocus", true); // 自动对焦
        intent.putExtra("fullScreen", false); // 全屏
        intent.putExtra("showActionIcons", false);
        // 指定调用相机拍照后照片的存储路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile2));


        startActivityForResult(intent, PHOTO_CARMERA);
    }

    // 调用系统相册
    protected void startPick(DialogInterface dialog) {
        dialog.dismiss();
        Intent intent = new Intent(Intent.ACTION_PICK, null);

            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*");

        startActivityForResult(intent, PHOTO_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_CARMERA:
                startPhotoZoom(Uri.fromFile(tempFile), 300);
                startPhotoZoom(Uri.fromFile(tempFile2), 300);
                break;
            case PHOTO_PICK:

                if (null != data) {
                    startPhotoZoom(data.getData(), 300);
                }
                break;
            case PHOTO_CUT:
                if (null != data) {
                    setPicToView(data);
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // 调用系统裁剪
    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以裁剪
        intent.putExtra("crop", true);
        // aspectX,aspectY是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY是裁剪图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        // 设置是否返回数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CUT);
    }

    // 将裁剪后的图片显示在ImageView上
    private void setPicToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (null != bundle) {
            final Bitmap bmp = bundle.getParcelable("data");
            if (la == 1){
                img.setImageBitmap(bmp);
                Log.e("lalala",img+"");
            }else if (la == 2){
                img2.setImageBitmap(bmp);
                Log.e("lalala",img2+"");

            }

            saveCropPic(bmp);
            Log.i("MainActivity", tempFile.getAbsolutePath());
        }
    }

    // 把裁剪后的图片保存到sdcard上
    private void saveCropPic(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileOutputStream fis = null;
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        try {
            fis = new FileOutputStream(tempFile);
            fis = new FileOutputStream(tempFile2);
            fis.write(baos.toByteArray());
            fis.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != baos) {
                    baos.close();
                }
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //截取图片
//    public void cropImage(Uri uri, int outputX, int outputY, int requestCode){
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", outputX);
//        intent.putExtra("outputY", outputY);
//        intent.putExtra("outputFormat", "JPEG");
//        intent.putExtra("noFaceDetection", true);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, requestCode);
//    }


    /***
     * 时间
     */
    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initializeViews() {
        showDate = (EditText) findViewById(showdate);
        pickDate = (LinearLayout) findViewById(R.id.pickdate);

        pickDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Message msg = new Message();
                if (pickDate.equals((LinearLayout) v)) {
                    msg.what = PsychologicalDivisionActivity.SHOW_DATAPICK;
                }
                PsychologicalDivisionActivity.this.dateandtimeHandler.sendMessage(msg);
            }
        });
    }

    private void setDateTime() {
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDateDisplay();
    }

    private void updateDateDisplay() {
        showDate.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            updateDateDisplay();
        }
    };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);

        }

        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;

        }
    }

    Handler dateandtimeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PsychologicalDivisionActivity.SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;
                case PsychologicalDivisionActivity.SHOW_TIMEPICK:
                    showDialog(TIME_DIALOG_ID);
                    break;
            }
        }

    };



    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
