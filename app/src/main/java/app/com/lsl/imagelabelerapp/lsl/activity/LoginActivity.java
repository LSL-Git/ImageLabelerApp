package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.view.UserView;
import app.com.lsl.imagelabelerapp.lsl.presenter.UserPresenter;
import app.com.lsl.imagelabelerapp.lsl.task.LoginTask;
import app.com.lsl.imagelabelerapp.lsl.utils.JsonUtils;


/** 用户登录页面
 * Created by M1308_000 on 2017/4/25.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, UserView{

    private Button but_login;
    private EditText et_user_psw;
    private EditText et_user_name;
    private CheckBox cb_is_remeber_psw;
    private Button but_to_register;
    private TextView tv_show_login_msg;
    private CheckBox cb_auto_login;
    private SharedPreferences spf;
    private String user_name;
    private String user_psw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 加载控件
        initLayout();

        spf = getSharedPreferences("UserInfo", Context.MODE_WORLD_READABLE);

        // 判断记住密码多选框的状态
        if (spf.getBoolean("IS_REMEMBER",false)) {
            // 设置默认是记住密码状态
            cb_is_remeber_psw.setChecked(true);
            et_user_name.setText(spf.getString("USER_NAME",""));
            et_user_psw.setText(spf.getString("PASSWORD",""));
            // 判断自动登录状态
            if (spf.getBoolean("AUTO_LOGIN", false)) {
                // 设置默认是自动登录
                cb_auto_login.setChecked(true);

                user_name = et_user_name.getText().toString().toString();
                user_psw = et_user_psw.getText().toString().trim();

                 // 启动登录线程
                new UserPresenter(this,LoginTask.Login(spf.getString("USER_NAME",""),spf.getString("PASSWORD","")), LoginTask.getTYPE()).fetch();
            }
        }

    }

    private void initLayout() {
        but_login = (Button) findViewById(R.id.but_login);
        but_to_register = (Button) findViewById(R.id.but_to_register);
        cb_is_remeber_psw = (CheckBox) findViewById(R.id.cb_login_remember_psw);
        cb_auto_login = (CheckBox) findViewById(R.id.cb_auto_login);
        et_user_name = (EditText) findViewById(R.id.et_login_user_name);
        et_user_psw = (EditText) findViewById(R.id.et_login_user_psw);
        tv_show_login_msg = (TextView) findViewById(R.id.tv_show_msg_login);

        but_login.setOnClickListener(this);
        but_to_register.setOnClickListener(this);

        cb_is_remeber_psw.setOnCheckedChangeListener(this);
        cb_auto_login.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_login:
                user_name = et_user_name.getText().toString().trim();
                user_psw = et_user_psw.getText().toString().trim();

                if (user_name.isEmpty() || user_psw.isEmpty()) {
                    Toast.makeText(this,"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                } else {
                    // 启动登录业务
                    new UserPresenter(this,LoginTask.Login(user_name,user_psw), LoginTask.getTYPE()).fetch();

                }
                break;

            case R.id.but_to_register:
                Intent intent2 = new Intent(this,RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_login_remember_psw:
                if (cb_is_remeber_psw.isChecked()) {
                    Log.e("LoginActivity","记住密码已选中");
                    spf.edit().putBoolean("IS_REMEMBER", true).commit();
                } else {
                    Log.e("LoginActivity","记住密码没选中");
                    spf.edit().putBoolean("IS_REMEMBER", false).commit();
                }
                break;

            case R.id.cb_auto_login:
                if (cb_auto_login.isChecked()) {
                    Log.e("LoginActivity","自动登录已选中");
                    spf.edit().putBoolean("AUTO_LOGIN", true).commit();
                } else {
                    Log.e("LoginActivity","自动登录没选中");
                    spf.edit().putBoolean("AUTO_LOGIN", false).commit();
                }
                break;
        }

    }


    @Override
    public void ShowLoading() {
        tv_show_login_msg.setText("登录中...");
    }

    @Override
    public void ShowBackMsg(Object obj) {
        String RESULT = "";
        try {
            RESULT = JsonUtils.LoginAndRegisterJson(obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (RESULT.equals("Login_Success")) { // 登录成功
            // 记住用户名和密码
            if (cb_is_remeber_psw.isChecked()) {
                SharedPreferences.Editor editor = spf.edit();
                editor.putString("USER_NAME", user_name);
                editor.putString("PASSWORD", user_psw);
                editor.commit();
            }

            // 登录成功，跳转到主页面
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user_name",user_name);
            this.startActivity(intent);
            finish();

        } else if (RESULT.equals("Psw_Err")){// 密码错误
            tv_show_login_msg.setText("密码错误");
        } else if (RESULT.equals("User_Not_Exist")) {// 用户不存在
            tv_show_login_msg.setText("用户名不存在.");
        } else {    // 登录失败
            tv_show_login_msg.setText("登录失败");
        }
    }

}
