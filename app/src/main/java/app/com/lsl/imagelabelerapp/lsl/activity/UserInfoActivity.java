package app.com.lsl.imagelabelerapp.lsl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.com.lsl.imagelabelerapp.R;

/** 查看用户信息和修改用户信息页面
 * Created by M1308_000 on 2017/4/26.
 */

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_info_save;
    private Button but_cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        but_cancel = (Button) findViewById(R.id.but_info_save);
        but_info_save = (Button) findViewById(R.id.but_info_cancel);

        but_cancel.setOnClickListener(this);
        but_info_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_info_save:

                break;
            case R.id.but_info_cancel:
                finish();
                break;
        }
    }
}
