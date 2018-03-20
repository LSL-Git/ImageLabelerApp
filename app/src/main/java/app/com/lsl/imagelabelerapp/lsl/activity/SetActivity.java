package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.App.MyApplication;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;

import static app.com.lsl.imagelabelerapp.lsl.activity.LoginActivity.AUTO_LOGIN;
import static app.com.lsl.imagelabelerapp.lsl.activity.LoginActivity.SPF_USERINFO;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.IS_MANAGER;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.SPF_USERALLINFO;

/** 设置也页
 * Created by M1308_000 on 2017/5/24.
 */

public class SetActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_exit_acc;
    private TextView tv_set_alter_psw;
    private TextView tv_set_user_info;
    private Intent intent;
    private TextView tv_about;
    private TextView tv_help;
    private TextView tv_feedback;
    private TextView tv_manager;
    private SharedPreferences spf;
    private LinearLayout ll_manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        AppActivities.addActivity(this);
        initMenu();
        initViews();

    }

    private void initViews() {
        tv_set_user_info = (TextView) findViewById(R.id.tv_set_user_info);
        tv_set_alter_psw = (TextView) findViewById(R.id.tv_set_alter_psw);
        but_exit_acc = (Button) findViewById(R.id.but_exit_acc);
        tv_feedback = (TextView) findViewById(R.id.tv_feedback);
        tv_help = (TextView) findViewById(R.id.tv_help);
        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_manager = (TextView) findViewById(R.id.tv_manager);
        ll_manager = (LinearLayout) findViewById(R.id.ll_manager);
        spf = getSharedPreferences(SPF_USERALLINFO, Context.MODE_PRIVATE);

        if (spf.getBoolean(IS_MANAGER, false)) {
            ll_manager.setVisibility(View.VISIBLE);
        }

        but_exit_acc.setOnClickListener(this);
        tv_set_alter_psw.setOnClickListener(this);
        tv_set_user_info.setOnClickListener(this);
        tv_feedback.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        tv_manager.setOnClickListener(this);
    }

    private void initMenu() {
        TopMenuHeader topMenu = new TopMenuHeader(getWindow().getDecorView());
        topMenu.topMenuTitle.setText("设 置");
        topMenu.topMenuTitle.setTextSize(20);
        topMenu.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenu.topMenuLeft.setText("返回");
        topMenu.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenu.topMenuRight.setVisibility(View.GONE);
        topMenu.topMenuLeft.setOnClickListener(this);
        topMenu.ivTopMenuLeft.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_manager:
                intent = new Intent(SetActivity.this, ManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_set_alter_psw:
                intent = new Intent(SetActivity.this, AlterPswActivity.class);
                this.startActivity(intent);
                break;
            case R.id.tv_set_user_info:
                intent = new Intent(SetActivity.this,UserInfoActivity.class);
                this.startActivity(intent);
                break;
            case R.id.but_exit_acc:
                SharedPreferences spf = getSharedPreferences(SPF_USERINFO, Context.MODE_PRIVATE);
                spf.edit().putBoolean(AUTO_LOGIN, false).commit();
                MyApplication.quitApp();    // 移除所有后台活动
                intent = new Intent(SetActivity.this, LoginActivity.class);
                this.startActivity(intent);
                finish();
                break;
            case R.id.top_menu_left:
                finish();
                break;
            case R.id.iv_icon:
                finish();
                break;
            case R.id.tv_feedback:
               intent = new Intent(SetActivity.this, FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_about:
                intent = new Intent(SetActivity.this, AboutAppActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_help:
                intent = new Intent(SetActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
