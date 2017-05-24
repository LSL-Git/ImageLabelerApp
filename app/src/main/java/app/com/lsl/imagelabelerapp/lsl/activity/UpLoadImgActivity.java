package app.com.lsl.imagelabelerapp.lsl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.com.lsl.imagelabelerapp.R;

/** 显示已选择的将要上传的图片并上传
 * Created by M1308_000 on 2017/5/22.
 */

public class UpLoadImgActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_img_list);

    }
}
