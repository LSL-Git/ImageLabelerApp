package app.com.lsl.imagelabelerapp.lsl.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import app.com.lsl.imagelabelerapp.lsl.App.User;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.activity.view.UserView;
import app.com.lsl.imagelabelerapp.lsl.presenter.UserPresenter;
import app.com.lsl.imagelabelerapp.lsl.utils.DialogUtil;

/**
 * Created by M1308_000 on 2017/6/25.
 */

public class LabelSetActivity extends AppCompatActivity implements UserView ,View.OnClickListener{

    private ImageView iv_ed2;
    private ImageView iv_ed1;
    private EditText et_taskNum;
    private EditText et_relyNum;
    private TextView tv_setInfo;
    private static final String TYPE = "LabelsSetServlet";
    private TopMenuHeader topMenuHeader;

    @Override
    public void ShowLoading() {
        DialogUtil.showLoadingDialog(LabelSetActivity.this, "加载中...", true);
    }

    @Override
    public void ShowBackMsg(Object obj) {
        DialogUtil.closeLoadingDialog(LabelSetActivity.this);
        try {
            JSONObject json = new JSONObject(obj.toString());
            String data = json.getString("result");
            if (data.equals("OK")) {
                Toast.makeText(LabelSetActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            } else if (data.equals("Fail")) {
                Toast.makeText(LabelSetActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
            } else {
                JSONObject d = new JSONObject(data);
                int rn = d.getInt("picLabelRelyOnNum");
                int tn = d.getInt("taskNum");
                if (rn != 0 && tn != 0) {
                    et_relyNum.setText(rn + "");
                    et_taskNum.setText(tn + "");
                }
                tv_setInfo.setText("上次修改时间：" + d.getString("alterTime") + "\n" + "修改人：" + d.getString("managerName"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initMenu() {
        topMenuHeader = new TopMenuHeader(getWindow().getDecorView());
        topMenuHeader.topMenuTitle.setText("管理员设置");
        topMenuHeader.topMenuTitle.setTextSize(20);
        topMenuHeader.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuLeft.setText("返回");
        topMenuHeader.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuRight.setVisibility(View.GONE);
        topMenuHeader.topMenuLeft.setOnClickListener(this);
        topMenuHeader.ivTopMenuLeft.setOnClickListener(this);
        topMenuHeader.topMenuRight.setOnClickListener(this);
    }

    private void initViews() {
        et_relyNum = (EditText) findViewById(R.id.et_rely_num);
        et_taskNum = (EditText) findViewById(R.id.et_task_num);
        iv_ed1 = (ImageView) findViewById(R.id.iv_set_ed1);
        iv_ed2 = (ImageView) findViewById(R.id.iv_set_ed2);
        tv_setInfo = (TextView) findViewById(R.id.tv_other_set_info);

        iv_ed1.setOnClickListener(this);
        iv_ed2.setOnClickListener(this);

    }

    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("type","getsetinfo");
        new UserPresenter(this, map, TYPE).fetch();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_set);
        initMenu();
        initViews();
        loadData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_set_ed1:
                et_taskNum.setEnabled(false);
                et_relyNum.setEnabled(true);
                topMenuHeader.topMenuRight.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_set_ed2:
                et_relyNum.setEnabled(false);
                et_taskNum.setEnabled(true);
                topMenuHeader.topMenuRight.setVisibility(View.VISIBLE);
                break;
            case R.id.top_menu_right:
                if (!TextUtils.isEmpty(et_relyNum.getText().toString().trim())
                        && !TextUtils.isEmpty(et_taskNum.getText().toString().trim())) {
                    String relyNum = et_relyNum.getText().toString().trim();
                    String taskNum = et_taskNum.getText().toString().trim();
                    int rNum = Integer.parseInt(relyNum);
                    int tNum = Integer.parseInt(taskNum);
                    if (rNum > tNum || rNum <= 0 || tNum <= 0) {
                        Toast.makeText(LabelSetActivity.this, "参数设置错误！", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    Map<String, String> map = new HashMap<>();
                    map.put("type","putsetinfo");
                    map.put("relyNum", relyNum);
                    map.put("taskNum", taskNum);
                    map.put("manageName", User.getUser());
                    new UserPresenter(this, map, TYPE).fetch();
                } else {
                    Toast.makeText(LabelSetActivity.this, "依赖和任务数不能留空！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_icon:
                finish();
                break;
            case R.id.top_menu_left:
                finish();
                break;
        }
    }
}
