package app.com.lsl.imagelabelerapp.lsl.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;

/** 问题反馈
 * Created by M1308_000 on 2017/6/23.
 */

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_commit;
    private EditText et_tel;
    private EditText et_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initMenu();

        et_content = (EditText) findViewById(R.id.et_problem_content);
        et_tel = (EditText) findViewById(R.id.et_problem_tel);
        but_commit = (Button) findViewById(R.id.but_problem_commit);
        but_commit.setOnClickListener(this);

    }

    private void initMenu() {
        TopMenuHeader topMenu = new TopMenuHeader(getWindow().getDecorView());
        topMenu.topMenuTitle.setText("问题反馈");
        topMenu.topMenuTitle.setTextSize(20);
        topMenu.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenu.topMenuLeft.setText("返回");
        topMenu.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenu.topMenuRight.setVisibility(View.GONE);
        topMenu.topMenuLeft.setOnClickListener(this);
        topMenu.ivTopMenuLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_menu_left:
                finish();
                break;
            case R.id.iv_icon:
                finish();
                break;
            case R.id.but_problem_commit:
                if (!TextUtils.isEmpty(et_content.getText().toString())) {
                    // do something
                    String pro_content = et_content.getText().toString().trim();
                    String tel = et_tel.getText().toString().trim();
                    try {
                        Thread.sleep(1000);
                        Toast.makeText(FeedBackActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(FeedBackActivity.this, "提交失败，请填写问题或意见", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
