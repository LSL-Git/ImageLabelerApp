package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.activity.view.UserView;
import app.com.lsl.imagelabelerapp.lsl.presenter.UserPresenter;
import app.com.lsl.imagelabelerapp.lsl.utils.DialogUtil;
import app.com.lsl.imagelabelerapp.lsl.utils.JsonUtils;

import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.SPF_USERALLINFO;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_EMAIL;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_ICON;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_INTEGRAL;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_NAME;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_TASK_COMPLETION;
import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.USER_TEL;

/** 查看用户信息和修改用户信息页面
 * Created by M1308_000 on 2017/4/26.
 */

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener, UserView {

    private SharedPreferences spf;
    private ImageView iv_info_icon;
    private TextView tv_info_name;
    private TextView tv_info_tel;
    private TextView tv_info_email;
    private TextView tv_info_integral;
    private TextView tv_info_task;

    private String types = "";
    private TextView tv_info_alter_psw;

    private TopMenuHeader topMenuHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initLayout();
        initMenu();
    }

    private void initMenu() {
        topMenuHeader = new TopMenuHeader(getWindow().getDecorView());
        topMenuHeader.topMenuTitle.setText("我的信息");
        topMenuHeader.topMenuTitle.setTextSize(20);
        topMenuHeader.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuLeft.setText("返回");
        topMenuHeader.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuRight.setVisibility(View.GONE);
        topMenuHeader.topMenuRight.setOnClickListener(this);
        topMenuHeader.topMenuLeft.setOnClickListener(this);
        topMenuHeader.ivTopMenuLeft.setOnClickListener(this);
    }

    private void initLayout() {
        AppActivities.addActivity(this);
        iv_info_icon = (ImageView) findViewById(R.id.iv_info_icon);
        tv_info_name = (TextView) findViewById(R.id.tv_userinfo_name);
        tv_info_tel = (TextView) findViewById(R.id.tv_userinfo_tel);
        tv_info_email = (TextView) findViewById(R.id.tv_userinfo_email);
        tv_info_integral = (TextView) findViewById(R.id.tv_userinfo_integral);
        tv_info_task = (TextView) findViewById(R.id.tv_userinfo_task);
        tv_info_alter_psw = (TextView) findViewById(R.id.tv_userinfo_alter_psw);


        // 从spf文件中读取用户信息并显示出来
        spf = getSharedPreferences(SPF_USERALLINFO, Context.MODE_WORLD_READABLE);
        if (spf != null) {
            tv_info_name.setText("昵称   " + spf.getString(USER_NAME, ""));
            tv_info_tel.setText(spf.getString(USER_TEL, ""));
            tv_info_email.setText(spf.getString(USER_EMAIL, ""));
            tv_info_integral.setText("积分   " + spf.getString(USER_INTEGRAL, ""));
            tv_info_task.setText("今日进度   " + spf.getString(USER_TASK_COMPLETION, ""));
            Bitmap bitmap = BitmapFactory.decodeFile(spf.getString(USER_ICON,""));
            iv_info_icon.setImageBitmap(bitmap);
        }

        tv_info_tel.setOnClickListener(this);
        tv_info_email.setOnClickListener(this);
        tv_info_alter_psw.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_menu_right:
                // 执行修改用户信息的业务
                String origin_tel = spf.getString(USER_TEL, "");
                String origin_email = spf.getString(USER_EMAIL,"");
                String new_tel = tv_info_tel.getText().toString();
                String new_email = tv_info_email.getText().toString();
                if (!origin_tel.equals(new_tel) || !origin_email.equals(new_email)) {
                    Map<String,String> map = new HashMap<>();
                    map.put("user_name", spf.getString(USER_NAME,""));
                    map.put("update_tel",new_tel);
                    map.put("update_email",new_email);
                    map.put("type","update");
                    new UserPresenter(UserInfoActivity.this, map, "userinfo").fetch();
                } else {
                    Toast.makeText(UserInfoActivity.this,"信息尚未改变",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.top_menu_left:
            case R.id.iv_icon:
                finish();
                break;
            case R.id.tv_userinfo_tel:
                inputDialog(tv_info_tel,1); // 1 tel
                break;
            case R.id.tv_userinfo_email:
                inputDialog(tv_info_email,2);   // 2 email
                break;
            case R.id.tv_userinfo_alter_psw:
                Intent intent = new Intent(UserInfoActivity.this, AlterPswActivity.class);
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
            inText.setInputType(InputType.TYPE_CLASS_PHONE); // 设置输入类型为phone
        } else if (type == 2){
            inText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("原:" + tv.getText().toString()).setView(inText)
                .setNegativeButton("取消",null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("UserInfoActivity",inText.getText().toString());
                if (TextUtils.isEmpty(inText.getText().toString())) {
                } else {
                    tv.setText(inText.getText().toString());
                    tv.setTextColor(Color.BLUE);
                    topMenuHeader.topMenuRight.setVisibility(View.VISIBLE);
                }
            }
        });
        builder.show();
    }

    @Override
    public void ShowLoading() {
        DialogUtil.showLoadingDialog(UserInfoActivity.this, "保存中...", true);
    }

    @Override
    public void ShowBackMsg(Object obj) {
        DialogUtil.closeLoadingDialog();
        try {
            if (JsonUtils.LoginAndRegisterJson(obj.toString()).equals("Update_Success")) {
                SharedPreferences.Editor editor = spf.edit();
                editor.putString(USER_TEL, tv_info_tel.getText().toString().trim());
                editor.putString(USER_EMAIL, tv_info_email.getText().toString().trim());
                editor.commit();
                Toast.makeText(UserInfoActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(UserInfoActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
