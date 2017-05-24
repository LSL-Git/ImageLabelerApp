package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.com.lsl.imagelabelerapp.R;

/** 管理员操作页面
 * Created by M1308_000 on 2017/5/19.
 */

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener{

    private Button but_export_label_result;
    private Button but_upload_img;
    private Button but_check_users_info;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        initLayout();

    }

    /**
     * 加载控件
     */
    private void initLayout() {
        but_check_users_info = (Button) findViewById(R.id.but_check_users_info);
        but_export_label_result = (Button) findViewById(R.id.but_export_label_result);
        but_upload_img = (Button) findViewById(R.id.but_upload_img);

        but_check_users_info.setOnClickListener(this);
        but_export_label_result.setOnClickListener(this);
        but_upload_img.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_check_users_info:
                Toast.makeText(this,"查看用户信息",Toast.LENGTH_SHORT).show();
                intent = new Intent(ManagerActivity.this, UserNameListActivity.class);
                this.startActivity(intent);
                break;
            case R.id.but_export_label_result:
                Toast.makeText(this,"导出标签化结果",Toast.LENGTH_SHORT).show();
                break;
            case R.id.but_upload_img:
                intent = new Intent(ManagerActivity.this, UpLoadImgActivity.class);
                this.startActivity(intent);
                Toast.makeText(this,"上传图片资料",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
