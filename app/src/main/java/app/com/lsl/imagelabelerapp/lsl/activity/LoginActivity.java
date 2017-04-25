package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import app.com.lsl.imagelabelerapp.MainActivity;
import app.com.lsl.imagelabelerapp.R;

/** 用户登录页面
 * Created by M1308_000 on 2017/4/25.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_login;
    private EditText et_user_psw;
    private EditText et_user_name;
    private CheckBox cb_is_remeber_psw;
    private Button but_to_register;

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

        but_login.setOnClickListener(this);
        but_to_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_login:
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.but_to_register:
                Intent intent2 = new Intent(this,RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
