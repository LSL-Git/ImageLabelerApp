package app.com.lsl.imagelabelerapp.lsl.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.task.RegisterTask;

/** 用户注册页面
 * Created by M1308_000 on 2017/4/25.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_register;
    private Button but_cancel;
    private EditText et_new_user_name;
    private EditText et_new_user_psw;
    private EditText et_new_user_rpsw;
    private EditText et_user_tel;
    private TextView tv_show_msg;

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
        et_user_tel = (EditText) findViewById(R.id.et_register_user_tel);
        tv_show_msg = (TextView) findViewById(R.id.tv_show_msg);

        but_register.setOnClickListener(this);
        but_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_register:
                final String icon_path = "mnt/sdcard/Download/icon.png";
                // 获取输入信息
                final String new_user_name = et_new_user_name.getText().toString().trim();
                String new_user_psw = et_new_user_psw.getText().toString().trim();
                final String user_rpsw = et_new_user_rpsw.getText().toString().trim();
                final String user_tel = et_user_tel.getText().toString().trim();

                if (user_rpsw.isEmpty() || user_tel.isEmpty() || new_user_name.isEmpty() || new_user_psw.isEmpty()) {
                    tv_show_msg.setText("请将信息填写完整！");
                } else if (!new_user_psw.equals(user_rpsw)) {
                    tv_show_msg.setText("两次密码输入不相同!");
                } else {
                    tv_show_msg.setText("注册中...");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = "";
                            result = RegisterTask.Register(new_user_name,user_tel,icon_path,user_rpsw);
                            Log.e("RegisterActivity","执行注册返回结果：" + result);

                            Message msg = new Message();
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
                break;
            case R.id.but_cancel:
                finish();
                break;
        }
    }

    /**
     *  将注册结果显示到UI
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String Re = msg.obj.toString();
            if (Re.equals("User_Exist")) {
                tv_show_msg.setText("用户名已存在！");
            } else if (Re.equals("Register_Success")) {
                tv_show_msg.setText("注册成功！");
                Toast.makeText(RegisterActivity.this, "注册成功!", Toast.LENGTH_LONG).show();
                finish();
            } else {
                tv_show_msg.setText("注册失败！");
            }
        }
    };
}
