package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.view.UserView;
import app.com.lsl.imagelabelerapp.lsl.presenter.UserPresenter;

import static app.com.lsl.imagelabelerapp.lsl.activity.LoginActivity.PASSWORD;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.IS_MANAGER;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.SPF_USERALLINFO;

/** 修改用户密码页面
 * Created by M1308_000 on 2017/5/22.
 */

public class AlterPswActivity extends AppCompatActivity implements View.OnClickListener, UserView {

    private EditText et_new_rpsw;
    private EditText et_new_psw;
    private EditText et_origin_psw;
    private Button but_alter_psw_save;
    private Button but_alter_psw_cancel;
    private String TYPE = "alter_psw";
    private String user_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_psw);
        initLayout();
    }

    private void initLayout() {
        // 得到用户名
        user_name = getIntent().getStringExtra("user_name");

        et_origin_psw = (EditText) findViewById(R.id.et_origin_psw);
        et_new_psw = (EditText) findViewById(R.id.et_new_psw);
        et_new_rpsw = (EditText) findViewById(R.id.et_new_rpsw);
        but_alter_psw_save = (Button) findViewById(R.id.but_alter_psw_save);
        but_alter_psw_cancel = (Button) findViewById(R.id.but_alter_psw_cancel);

        but_alter_psw_cancel.setOnClickListener(this);
        but_alter_psw_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_alter_psw_save:
                String in_origin_psw = et_origin_psw.getText().toString().trim();
                String in_new_psw = et_new_psw.getText().toString().trim();
                String in_new_rpsw = et_new_rpsw.getText().toString().trim();

                SharedPreferences spf = getSharedPreferences(SPF_USERALLINFO, Context.MODE_WORLD_READABLE);
                if (spf.getBoolean(IS_MANAGER,false)) {
                    String origin_psw = spf.getString(PASSWORD,"");
                    if (in_origin_psw.equals(origin_psw)) {
                        if (in_new_psw.equals(in_new_rpsw)) {
                            // 执行密码修改业务
                            Toast.makeText(AlterPswActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(AlterPswActivity.this,"两次密码输入不同",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AlterPswActivity.this,"原密码错误！",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Map<String,String> map = new HashMap<>();
                    map.put("user_name",user_name);
                    map.put("update_psw",in_origin_psw);
                    // 从服务器检索密码是否一样
                    new UserPresenter(AlterPswActivity.this, map, TYPE).fetch();
                }
                break;
            case R.id.but_alter_psw_cancel:
                finish();
                break;
        }
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void ShowBackMsg(Object obj) {
        Log.e("AlterPswActivity",obj.toString());
    }
}
