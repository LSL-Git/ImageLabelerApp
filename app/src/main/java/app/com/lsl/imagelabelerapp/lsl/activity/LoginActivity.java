package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.com.lsl.imagelabelerapp.MainActivity;
import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.task.LoginTask;

/** 用户登录页面
 * Created by M1308_000 on 2017/4/25.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_login;
    private EditText et_user_psw;
    private EditText et_user_name;
    private CheckBox cb_is_remeber_psw;
    private Button but_to_register;
    private TextView tv_show_login_msg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 加载控件
        initLayout();

    }

    private void initLayout() {
        but_login = (Button) findViewById(R.id.but_login);
        but_to_register = (Button) findViewById(R.id.but_to_register);
        cb_is_remeber_psw = (CheckBox) findViewById(R.id.cb_login_remember_psw);
        et_user_name = (EditText) findViewById(R.id.et_login_user_name);
        et_user_psw = (EditText) findViewById(R.id.et_login_user_psw);
        tv_show_login_msg = (TextView) findViewById(R.id.tv_show_msg_login);

        but_login.setOnClickListener(this);
        but_to_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_login:
                final String user_name = et_user_name.getText().toString().trim();
                final String user_psw = et_user_psw.getText().toString().trim();

                if (user_name.isEmpty() || user_psw.isEmpty()) {
                    Toast.makeText(this,"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                } else {
                    tv_show_login_msg.setText("登录中...");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = LoginTask.Login(user_name, user_psw);
                            Log.e("LoginTask",result);
                            Message msg = new Message();
                            if (result.equals("Login_Success")) {
                                msg.arg1 = 1;
                            } else if (result.equals("Psw_Err")){
                                msg.arg1 = 2;
                            } else if (result.equals("User_Not_Exist")) {
                                msg.arg1 = 3;
                            }
                            handler.sendMessage(msg);
                        }
                    }).start();

                }
                break;

            case R.id.but_to_register:
                Intent intent2 = new Intent(this,RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 信息为1则登录成功 否则 登录失败
            switch (msg.arg1) {
                case 1:
                    // 登录成功，跳转到主页
                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
                case 2:
                    tv_show_login_msg.setText("密码错误！");
                    break;
                case 3:
                    tv_show_login_msg.setText("用户不存在！");
                    break;
            }

        }
    };

}
