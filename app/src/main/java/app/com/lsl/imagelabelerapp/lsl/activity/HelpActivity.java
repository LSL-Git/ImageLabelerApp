package app.com.lsl.imagelabelerapp.lsl.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;

/** 帮助
 * Created by M1308_000 on 2017/6/23.
 */

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initMenu();
    }

    private void initMenu() {
        TopMenuHeader topMenu = new TopMenuHeader(getWindow().getDecorView());
        topMenu.topMenuTitle.setText("帮 助");
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
        }
    }
}
