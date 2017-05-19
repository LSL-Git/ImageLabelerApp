package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.lsl.imagelabelerapp.R;

import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_EMAIL;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_ICON;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_INTEGRAL;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_NAME;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_TASK_COMPLETION;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_TEL;

/** 查看用户信息和修改用户信息页面
 * Created by M1308_000 on 2017/4/26.
 */

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_info_save;
    private Button but_cancel;
    private SharedPreferences spf;
    private ImageView iv_info_icon;
    private TextView tv_info_name;
    private TextView tv_info_tel;
    private TextView tv_info_email;
    private TextView tv_info_integral;
    private TextView tv_info_task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        iv_info_icon = (ImageView) findViewById(R.id.iv_info_icon);
        tv_info_name = (TextView) findViewById(R.id.tv_userinfo_name);
        tv_info_tel = (TextView) findViewById(R.id.tv_userinfo_tel);
        tv_info_email = (TextView) findViewById(R.id.tv_userinfo_email);
        tv_info_integral = (TextView) findViewById(R.id.tv_userinfo_integral);
        tv_info_task = (TextView) findViewById(R.id.tv_userinfo_task);
        but_cancel = (Button) findViewById(R.id.but_info_save);
        but_info_save = (Button) findViewById(R.id.but_info_cancel);

        but_cancel.setOnClickListener(this);
        but_info_save.setOnClickListener(this);

        Intent intent = getIntent();
        String spf_name = intent.getStringExtra("spf");

        spf = getSharedPreferences(spf_name, Context.MODE_WORLD_READABLE);
        if (spf != null) {
            tv_info_name.setText("昵称   " + spf.getString(USER_NAME, ""));
            tv_info_tel.setText("电话   " + spf.getString(USER_TEL, ""));
            tv_info_email.setText("邮箱   " + spf.getString(USER_EMAIL, ""));
            tv_info_integral.setText("积分   " + spf.getString(USER_INTEGRAL, ""));
            tv_info_task.setText("今日进度   " + spf.getString(USER_TASK_COMPLETION, ""));
            Bitmap bitmap = BitmapFactory.decodeFile(spf.getString(USER_ICON,""));
            iv_info_icon.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_info_save:

                break;
            case R.id.but_info_cancel:
                finish();
                break;
        }
    }
}
