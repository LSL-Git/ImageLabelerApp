package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.DialogInterface;
import android.content.Intent;
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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.activity.view.UserView;
import app.com.lsl.imagelabelerapp.lsl.presenter.UserPresenter;
import app.com.lsl.imagelabelerapp.lsl.utils.DialogUtil;

/**
 * Created by M1308_000 on 2017/6/11.
 */

public class mUserInfoActivity extends AppCompatActivity implements UserView , View.OnClickListener{

    private static final String TAG = "mUserInfoActivity";

    private ImageView iv_userIcon;
    private TextView tv_userName;
    private TextView tv_userTel;
    private TextView tv_userEmail;
    private TextView tv_userIg;
    private TextView tv_userTask;
    private TextView tv_userAllLabel;
    private TextView tv_userSucLabel;
    private TextView tv_userGrantSess;
    private String user_name;
    private TopMenuHeader topMenuHeader;
    private static int is_manager;
    private String email;
    private String tel;
    private String is_m;
    private String manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        initViews();
        initMenu();
        initData();
    }

    private void initMenu() {
        topMenuHeader = new TopMenuHeader(getWindow().getDecorView());
        topMenuHeader.topMenuTitle.setText("用户信息");
        topMenuHeader.topMenuTitle.setTextSize(20);
        topMenuHeader.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuLeft.setText("返回");
        topMenuHeader.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuRight.setVisibility(View.GONE);
        topMenuHeader.topMenuRight.setOnClickListener(this);
        topMenuHeader.topMenuLeft.setOnClickListener(this);
        topMenuHeader.ivTopMenuLeft.setOnClickListener(this);
    }

    /**
     * 加载控件
     */
    private void initViews() {
        Intent intent = getIntent();
        user_name = intent.getStringExtra("userName");

        iv_userIcon = (ImageView) findViewById(R.id.iv_user_micon);
        tv_userName = (TextView) findViewById(R.id.tv_user_name);
        tv_userTel = (TextView) findViewById(R.id.tv_user_tel);
        tv_userEmail = (TextView) findViewById(R.id.tv_user_email);
        tv_userIg = (TextView) findViewById(R.id.tv_user_integral);
        tv_userTask = (TextView) findViewById(R.id.tv_user_task);
        tv_userAllLabel = (TextView) findViewById(R.id.tv_user_all_labels);
        tv_userSucLabel = (TextView) findViewById(R.id.tv_user_label_suc_num);
        tv_userGrantSess = (TextView) findViewById(R.id.tv_user_grant_manager);

        tv_userTel.setOnClickListener(this);
        tv_userEmail.setOnClickListener(this);
        tv_userGrantSess.setOnClickListener(this);
    }

    private void initData() {
        // 根据用户名请求用户的所有信息
        String TYPE = "userinfo";
        Map<String,String> map = new HashMap<>();
        map.put("user_name",user_name);
        map.put("type","query_user_info");
        new UserPresenter(this, map, TYPE).fetch();
    }

    @Override
    public void ShowLoading() {
        DialogUtil.showLoadingDialog(mUserInfoActivity.this, "loading...",true);
    }

    @Override
    public void ShowBackMsg(Object obj) {
//        DialogUtil.closeLoadingDialog();
        Log.e(TAG, obj.toString());
        try {
            JSONObject rjson = new JSONObject(obj.toString());
            String RESULT = rjson.getString("result");
            if (RESULT.equals("Update_Success")) {
                Toast.makeText(this, "save success...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "save fail...", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            JSONObject json = new JSONObject(obj.toString());
            iv_userIcon.setImageResource(R.mipmap.icon10);
            tv_userName.setText("昵称  " + json.getString("user_name"));
            tv_userTel.setText(json.getString("user_tel"));
            tv_userEmail.setText(json.getString("user_email"));
            tv_userIg.setText("积分  " + json.getString("user_integral"));
            tv_userAllLabel.setText("标签总数  " + json.getString("label_all_num"));
            tv_userSucLabel.setText("标签成功数  " + json.getString("label_success_num"));
            tv_userTask.setText("任务完成情况  " + json.getString("task_completion"));
            is_manager = json.getInt("is_manager");
            if (is_manager == 1) {
                tv_userGrantSess.setText("授权管理员  " + "YES");
            } else {
                tv_userGrantSess.setText("授权管理员  " + "NO");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_menu_right:
                tel = tv_userTel.getText().toString().trim();
                email = tv_userEmail.getText().toString().trim();
                is_m = tv_userGrantSess.getText().toString().trim();
                manager = is_m.substring(is_m.length() - 3, is_m.length());
                Map<String,String> map = new HashMap<>();
                map.put("user_name", user_name);
                map.put("update_tel",tel);
                map.put("update_email",email);
                map.put("manager",manager);
                map.put("type","update");
                new UserPresenter(mUserInfoActivity.this, map, "userinfo").fetch();
                break;
            case R.id.top_menu_left:
                finish();
                break;
            case R.id.iv_icon:
                finish();
                break;
            case R.id.tv_user_tel:
                inputDialog(tv_userTel,1); // 1 tel
                break;
            case R.id.tv_user_email:
                inputDialog(tv_userEmail,2);   // 2 email
                break;
            case R.id.tv_user_grant_manager:
                String is;
                if (is_manager == 0) {
                    is = "是否授权管理员？";
                } else {
                    is = "是否取消授权？";
                }
                new AlertDialog.Builder(this).setTitle(is).setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                topMenuHeader.topMenuRight.setVisibility(View.VISIBLE);
                                if (is_manager == 0) {
                                    is_manager = 1;
                                    tv_userGrantSess.setText("授权管理员  " + "YES");
                                } else {
                                    is_manager = 0;
                                    tv_userGrantSess.setText("授权管理员  " + "NO");
                                }
                            }
                        }).show();

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
}
