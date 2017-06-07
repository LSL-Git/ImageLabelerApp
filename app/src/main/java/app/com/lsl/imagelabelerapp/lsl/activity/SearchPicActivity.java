package app.com.lsl.imagelabelerapp.lsl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.com.lsl.imagelabelerapp.R;

/** 根据图片名称检索图片
 * Created by M1308_000 on 2017/6/7.
 */

public class SearchPicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pic);
        AppActivities.addActivity(this);
    }
}
