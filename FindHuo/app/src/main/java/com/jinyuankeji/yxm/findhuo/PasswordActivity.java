package com.jinyuankeji.yxm.findhuo;

import android.app.Activity;
<<<<<<< HEAD
import android.os.Bundle;
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jinyuankeji.yxm.findhuo.lottery.ui.LoginActivity;
>>>>>>> 2

/**
 * Created by Administrator on 2017/1/7 0007.
 */

public class PasswordActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_activity);
    }
<<<<<<< HEAD
=======
    public void back (View view){
        Intent it = new Intent(PasswordActivity.this, LoginActivity.class);
        startActivity(it);
    }
>>>>>>> 2
}
