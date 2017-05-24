package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.lsl.imagelabelerapp.R;

import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.*;

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

    private String types = "";
    private TextView tv_info_alter_psw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initLayout();
    }

    private void initLayout() {
        iv_info_icon = (ImageView) findViewById(R.id.iv_info_icon);
        tv_info_name = (TextView) findViewById(R.id.tv_userinfo_name);
        tv_info_tel = (TextView) findViewById(R.id.tv_userinfo_tel);
        tv_info_email = (TextView) findViewById(R.id.tv_userinfo_email);
        tv_info_integral = (TextView) findViewById(R.id.tv_userinfo_integral);
        tv_info_task = (TextView) findViewById(R.id.tv_userinfo_task);
        tv_info_alter_psw = (TextView) findViewById(R.id.tv_userinfo_alter_psw);
        but_cancel = (Button) findViewById(R.id.but_info_save);
        but_info_save = (Button) findViewById(R.id.but_info_cancel);

        // 从spf文件中读取用户信息并显示出来
        spf = getSharedPreferences(SPF_USERALLINFO, Context.MODE_WORLD_READABLE);
        if (spf != null) {
            tv_info_name.setText("昵称   " + spf.getString(USER_NAME, ""));
            tv_info_tel.setText("电话   " + spf.getString(USER_TEL, ""));
            tv_info_email.setText("邮箱   " + spf.getString(USER_EMAIL, ""));
            tv_info_integral.setText("积分   " + spf.getString(USER_INTEGRAL, ""));
            tv_info_task.setText("今日进度   " + spf.getString(USER_TASK_COMPLETION, ""));
            Bitmap bitmap = BitmapFactory.decodeFile(spf.getString(USER_ICON,""));
            iv_info_icon.setImageBitmap(bitmap);
        }

        but_cancel.setOnClickListener(this);
        but_info_save.setOnClickListener(this);
        tv_info_tel.setOnClickListener(this);
        tv_info_email.setOnClickListener(this);
        tv_info_alter_psw.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_info_save:
                // 执行修改用户信息的业务

                break;
            case R.id.but_info_cancel:
                finish();
                break;
            case R.id.tv_userinfo_tel:
                inputDialog(tv_info_tel,1);
                break;
            case R.id.tv_userinfo_email:
                inputDialog(tv_info_email,2);
                break;
            case R.id.tv_userinfo_alter_psw:
                Intent intent = new Intent(UserInfoActivity.this, AlterPswActivity.class);
                intent.putExtra("user_name",tv_info_name.getText().toString().trim());
                this.startActivity(intent);
                break;
        }
    }


    /**
     * 弹出输入框
     */
    private void inputDialog(final TextView tv,int type) {
        final EditText inText = new EditText(this);
        inText.setFocusable(true);
        if (type == 1) {
            types = "*电话  ";
            inText.setInputType(InputType.TYPE_CLASS_NUMBER); // 设置输入类型为number
        } else if (type == 2){
            types = "*邮箱  ";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("原:" + tv.getText().toString()).setView(inText)
                .setNegativeButton("取消",null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("UserInfoActivity",inText.getText().toString());
                if (TextUtils.isEmpty(inText.getText().toString())) {
                } else {
                    tv.setText(types + inText.getText().toString());
                }
            }
        });
        builder.show();
    }


}
