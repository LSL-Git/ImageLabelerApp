package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;


/** 显示积分情况
 * Created by M1308_000 on 2017/6/23.
 */

public class MyIntegralActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_integral;
    private Button but_gift;
    private LinearLayout ll_record;
    private View v_line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_integral);
        initMenu();
        initViews();

    }

    private void initViews() {
        tv_integral = (TextView) findViewById(R.id.tv_my_integral);
        but_gift = (Button) findViewById(R.id.but_gift);
        ll_record = (LinearLayout) findViewById(R.id.ll_record);
        v_line = findViewById(R.id.v_line);
        but_gift.setOnClickListener(this);

        Intent intent = getIntent();
        String integral = intent.getStringExtra("integral");
        tv_integral.setText(integral);
        if (integral.equals("0")) {
            ll_record.setVisibility(View.GONE);
            v_line.setVisibility(View.GONE);
        }
    }

    private void initMenu() {
        TopMenuHeader topMenu = new TopMenuHeader(getWindow().getDecorView());
        topMenu.topMenuTitle.setText("我的积分");
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
            case R.id.but_gift:
                Toast.makeText(MyIntegralActivity.this, "暂不支持兑换", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
