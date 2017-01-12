package com.jinyuankeji.yxm.findhuo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/7 0007.
 */

public class ZLoginActivity extends Activity{

    private Button login;
    private TextView register,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zlogin_activity);

        initView();
    }

    private void initView() {
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ZLoginActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
        register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ZLoginActivity.this,ZRegisterActivity.class);
                startActivity(it);
            }
        });
        pwd = (TextView)findViewById(R.id.pwd);
        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ZLoginActivity.this,PasswordActivity.class);
                startActivity(it);
            }
        });
    }

}
