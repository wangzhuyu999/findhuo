package com.jinyuankeji.yxm.findhuo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

<<<<<<< HEAD
=======
import com.jinyuankeji.yxm.findhuo.lottery.ui.LoginActivity;

>>>>>>> 2
/**
 * Created by Administrator on 2017/1/7 0007.
 */

public class WelcomeActivity extends Activity{
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        image = (ImageView)findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it  = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
    }
}
