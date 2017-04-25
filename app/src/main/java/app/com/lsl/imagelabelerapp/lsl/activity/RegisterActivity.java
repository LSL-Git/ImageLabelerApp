package app.com.lsl.imagelabelerapp.lsl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.com.lsl.imagelabelerapp.R;

/** 用户注册页面
 * Created by M1308_000 on 2017/4/25.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_register;
    private Button but_cancel;
    private EditText et_new_user_name;
    private EditText et_new_user_psw;
    private EditText et_new_user_rpsw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 加载控件
        initLayout();

    }

    private void initLayout() {
        but_register = (Button) findViewById(R.id.but_register);
        but_cancel = (Button) findViewById(R.id.but_cancel);
        et_new_user_name = (EditText) findViewById(R.id.et_register_user_name);
        et_new_user_psw = (EditText) findViewById(R.id.et_register_user_psw);
        et_new_user_rpsw = (EditText) findViewById(R.id.et_register_user_rpsw);

        but_register.setOnClickListener(this);
        but_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_register:

                break;
            case R.id.but_cancel:
                finish();
                break;
        }
    }
}
