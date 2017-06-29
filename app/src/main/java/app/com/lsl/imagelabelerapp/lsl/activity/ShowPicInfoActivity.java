package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.utils.DbUtils;
import app.com.lsl.imagelabelerapp.lsl.utils.StrUtils;
import okhttp3.Call;

/** 显示图片标签化的标签信息
 * Created by M1308_000 on 2017/6/10.
 */

public class ShowPicInfoActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "ShowPicInfoActivity";

    private Intent intent;
    private String img_url;

    private TextView tv_pic_labels;
    private TextView tv_pic_label_time;
    private TextView tv_pic_name;
    private ImageView iv_pic_info;
    private String fileName;
    private String[] urls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_info);

        initViews();
        getData();
        initMenu();
    }

    private void initMenu() {
        TopMenuHeader topMenuHeader = new TopMenuHeader(getWindow().getDecorView());
        topMenuHeader.topMenuTitle.setText("图片信息");
        topMenuHeader.topMenuTitle.setTextSize(20);
        topMenuHeader.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuLeft.setText("返回");
        topMenuHeader.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuRight.setVisibility(View.GONE);
        topMenuHeader.topMenuLeft.setOnClickListener(this);
        topMenuHeader.ivTopMenuLeft.setOnClickListener(this);
    }


    private void getData() {
        intent = getIntent();
        img_url = intent.getStringExtra("img_url");

        Log.e(TAG, img_url);
        urls = img_url.split("/");

        fileName = urls[urls.length - 1];// 得到图片名称
        tv_pic_name.setText(fileName);  // 显示图片名称
        tv_pic_label_time.setText(DbUtils.GetFinishTime(fileName)); // 显示完成标签化时间
        tv_pic_labels.setText(StrUtils.GetLabelsStr(urls));

        // 根据图片URL  加载显示整张大图片
        OkHttpUtils.get().url(img_url).tag(this).build().connTimeOut(20000)
                .readTimeOut(20000).writeTimeOut(20000).execute(new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(Bitmap bitmap, int id) {
                iv_pic_info.setImageBitmap(bitmap);
            }
        });

    }

    private void initViews() {
        iv_pic_info = (ImageView) findViewById(R.id.iv_pic);
        tv_pic_name = (TextView) findViewById(R.id.tv_pic_name);
        tv_pic_label_time = (TextView) findViewById(R.id.tv_pic_label_success_time);
        tv_pic_labels = (TextView) findViewById(R.id.tv_pic_labels);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
