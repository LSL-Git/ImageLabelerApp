package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.App.MyApplication;

import static app.com.lsl.imagelabelerapp.lsl.activity.LoginActivity.AUTO_LOGIN;
import static app.com.lsl.imagelabelerapp.lsl.activity.LoginActivity.SPF_USERINFO;

/** 设置也页
 * Created by M1308_000 on 2017/5/24.
 */

public class SetActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_exit_acc;
    private TextView tv_set_alter_psw;
    private TextView tv_set_user_info;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        AppActivities.addActivity(this);
        tv_set_user_info = (TextView) findViewById(R.id.tv_set_user_info);
        tv_set_alter_psw = (TextView) findViewById(R.id.tv_set_alter_psw);
        but_exit_acc = (Button) findViewById(R.id.but_exit_acc);
        but_exit_acc.setOnClickListener(this);
        tv_set_alter_psw.setOnClickListener(this);
        tv_set_user_info.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_set_alter_psw:
                intent = new Intent(SetActivity.this, AlterPswActivity.class);
                this.startActivity(intent);
                break;
            case R.id.tv_set_user_info:
                intent = new Intent(SetActivity.this,UserInfoActivity.class);
                this.startActivity(intent);
                break;
            case R.id.but_exit_acc:
                SharedPreferences spf = getSharedPreferences(SPF_USERINFO, Context.MODE_WORLD_READABLE);
                spf.edit().putBoolean(AUTO_LOGIN, false).commit();
                MyApplication.quitApp();    // 移除所有后台活动
                intent = new Intent(SetActivity.this, LoginActivity.class);
                this.startActivity(intent);
                finish();
                break;
        }
    }
}
