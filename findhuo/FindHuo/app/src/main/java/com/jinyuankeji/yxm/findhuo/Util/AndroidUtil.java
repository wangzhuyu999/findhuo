package com.jinyuankeji.yxm.findhuo.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class AndroidUtil {


    // 返回一个列表对话框
    public static AlertDialog.Builder getListDialogBuilder(Context context,
                                                           String[] items, String title, DialogInterface.OnClickListener clickListener) {
        return new AlertDialog.Builder(context).setTitle(title).setItems(items, clickListener);

    }

}
