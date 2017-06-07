package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sleepyzzz.photo_selector.activity.PhotoPickerActivity;

import app.com.lsl.imagelabelerapp.R;

import static android.widget.Toast.makeText;

/** 管理员操作页面
 * Created by M1308_000 on 2017/5/19.
 */

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String url = "http://114.115.141.43:4040/webServer/UploadImages";

    private Button but_export_label_result;
    private Button but_upload_img;
    private Button but_check_users_info;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        AppActivities.addActivity(this);
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
                makeText(this,"查看用户信息",Toast.LENGTH_SHORT).show();
                intent = new Intent(ManagerActivity.this, UserNameListActivity.class);
                this.startActivity(intent);
                break;
            case R.id.but_export_label_result:
                makeText(this,"导出标签化结果",Toast.LENGTH_SHORT).show();
                break;
            case R.id.but_upload_img:
                // 调用图片选择并上传模块
                // 参数二：最大选择图片数，
                // 参数三：图片选择路径，null表示所有路径
                // 图片上传的服务器地址
                PhotoPickerActivity.actionStart(ManagerActivity.this, 10, null, url);
                break;
        }
    }
}
