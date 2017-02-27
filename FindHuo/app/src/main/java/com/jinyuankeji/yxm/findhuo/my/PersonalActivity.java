package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.Util.AndroidUtil;
import com.jinyuankeji.yxm.findhuo.Util.ImageTools;
import com.jinyuankeji.yxm.findhuo.bean.LoginBean;
import com.jinyuankeji.yxm.findhuo.bean.UploadPictureBean;
import com.jinyuankeji.yxm.findhuo.bean.UserInfoBean;
import com.jinyuankeji.yxm.findhuo.bean.UserModifyBean;
import com.jinyuankeji.yxm.findhuo.bean.VercodeBean;
import com.jinyuankeji.yxm.findhuo.image_util.ImageLoaderUtil;
import com.jinyuankeji.yxm.findhuo.image_util.Instance;
import com.jinyuankeji.yxm.findhuo.tools.Contants;
import com.jinyuankeji.yxm.findhuo.tools.JsonUtils;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.jinyuankeji.yxm.findhuo.R.id.showdate;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.checkPhone;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.forgetPwd;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.uploadPicture;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.userInfo;
import static com.jinyuankeji.yxm.findhuo.tools.URLValue.userModify;


/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class PersonalActivity extends Activity {

    private String Phone;

    private UploadPictureBean bean1;
    private UserModifyBean bean2;
    private UserInfoBean bean3;

    private EditText name;
    private ImageView back;
    private Button keep,save;

    private EditText showDate = null;
    private RelativeLayout pickDate = null;

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_TIMEPICK = 2;
    private static final int TIME_DIALOG_ID = 3;

    private int mYear;
    private int mMonth;
    private int mDay;
//    private GoogleApiClient client;

    /***
     *  camera
     */

    private ImageView img;
    private Button btnUpload;
    private HttpUtils httpUtils;
    private String URL="http://zhaohuo.jinyuankeji.net/api.php/App/uploadPicture/avatar/";

    private String[] items = { "拍照", "相册" };
    private String title = "选择照片";

    private static final int PHOTO_CARMERA = 1;
    private static final int PHOTO_PICK = 2;
    private static final int PHOTO_CUT = 3;
    // 创建一个以当前系统时间为名称的文件，防止重复
    private File tempFile = new File(Environment.getExternalStorageDirectory(),
            getPhotoFileName());

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("'PNG'_yyyyMMdd_HHmmss");
        return sdf.format(date) + ".png";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_personal);

        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user",Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        Phone = sharedPreferences.getString("phone","");

        request();

        initializeViews();

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        setDateTime();
        initView();

        //照片上传
        img = (ImageView) findViewById(R.id.img);
        btnUpload = (Button) findViewById(R.id.button5);
        btnUpload.setOnClickListener(clickListener);
        img.setOnClickListener(clickListener);

        httpUtils=new HttpUtils(10000);

        name = (EditText)findViewById(R.id.name);



    }


    //我的界面——》修改个人信息和修改彩票提醒
    private void request2(){
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",Phone);
        params.addBodyParameter("birthday",showDate.getText().toString().trim());
        params.addBodyParameter("name",name.getText().toString().trim());
        params.addBodyParameter("headimg",Contants.img);

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + userModify, params,
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

                            Log.e("修改请求成功",
                                    "66666666666666666666 onSuccess"
                                            + arg0.result.toString());

                            //  getList(arg0.result.toString());// 请求返回的数据，json解析
                            String json = arg0.result.toString();
                            bean2 = JsonUtils.getJtoC(json, UserModifyBean.class);
                            //   Contants.account = etMainUserName.getText().toString().trim();

                            int i = bean2.getRes();


                            if(i == 10001){
                                Toast.makeText(getApplicationContext(), "保存成功",
                                        Toast.LENGTH_SHORT).show();


                            }if(i == 10002){
                                Toast.makeText(getApplicationContext(), "修改失败",
                                        Toast.LENGTH_SHORT).show();
                            }if(i == 10000){
                                Toast.makeText(getApplicationContext(), "请求失败",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                });


    }

    //我的界面、个人信息读取、消息提醒读取、账户余额
    //请求
    private void request() {
        HttpUtils httpUtils = new HttpUtils();
        // 请求参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account",Phone);
       // params.addBodyParameter("actionid","1");

        // 发送请求数据
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + userInfo, params,
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

                            Log.e("请求成功",
                                    "222222222222222222 onSuccess"
                                            + arg0.result.toString());

                            //  getList(arg0.result.toString());// 请求返回的数据，json解析
                            String json = arg0.result.toString();
                            bean3 = JsonUtils.getJtoC(json, UserInfoBean.class);

                            Instance.imageLoader.init(ImageLoaderConfiguration.createDefault(PersonalActivity.this));

                            String heading = bean3.getData().getUrl();
                            ImageLoaderUtil.getInternetImage(heading,img);

                            String name_bean = bean3.getData().getName();
                            name.setText(name_bean);
                            String showDate_bean = bean3.getData().getBirthday();
                            showDate.setText(showDate_bean);

                        }

                    }

                });


    }

    /***
     * 照片上传
     */

    private OnClickListener clickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img:
                    AlertDialog.Builder dialog = AndroidUtil.getListDialogBuilder(
                            PersonalActivity.this, items, title, dialogListener);
                    dialog.show();
                    break;
                case R.id.button5:

                    upload();
                    request2();
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
                Toast.makeText(PersonalActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
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
            img.setImageBitmap(bmp);

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
    public void cropImage(Uri uri, int outputX, int outputY, int requestCode){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, requestCode);
    }



    /***
     * 时间
     */
    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initializeViews() {
        showDate = (EditText) findViewById(showdate);
        pickDate = (RelativeLayout) findViewById(R.id.pickdate);

        pickDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Message msg = new Message();
                if (pickDate.equals((RelativeLayout) v)) {
                    msg.what = PersonalActivity.SHOW_DATAPICK;
                }
                PersonalActivity.this.dateandtimeHandler.sendMessage(msg);
            }
        });
    }

    private void setDateTime() {
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

       // updateDateDisplay();
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
                case PersonalActivity.SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;
                case PersonalActivity.SHOW_TIMEPICK:
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
