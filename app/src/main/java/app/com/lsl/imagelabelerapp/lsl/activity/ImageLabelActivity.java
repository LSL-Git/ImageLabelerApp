package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import app.com.lsl.imagelabelerapp.R;
import okhttp3.Call;

/** 图片标签活动
 * Created by M1308_000 on 2017/5/17.
 */

public class ImageLabelActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Intent intent;
    private String img_url;
    private EditText et_label_one;
    private EditText et_label_two;
    private EditText et_label_three;
    private EditText et_label_four;
    private Button but_yes_label;
    private Button but_cancel_label;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Layout();

        // 根据图片URL  加载显示整张大图片
        OkHttpUtils.get().url(img_url).tag(this).build().connTimeOut(20000)
                .readTimeOut(20000).writeTimeOut(20000).execute(new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(Bitmap bitmap, int id) {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    // 加载控件
    private void Layout() {
        setContentView(R.layout.activity_img);
        imageView = (ImageView) findViewById(R.id.iv_show_image_one);
        et_label_one = (EditText) findViewById(R.id.et_label_one);
        et_label_two = (EditText) findViewById(R.id.et_label_two);
        et_label_three = (EditText) findViewById(R.id.et_label_three);
        et_label_four = (EditText) findViewById(R.id.et_label_four);
        but_yes_label = (Button) findViewById(R.id.but_YES_label);
        but_cancel_label = (Button) findViewById(R.id.but_cancel_label);

        but_cancel_label.setOnClickListener(this);
        but_yes_label.setOnClickListener(this);

        intent = getIntent();
        img_url = intent.getStringExtra("img_url");

//        Log.e("img_rul","url: " + img_url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_cancel_label:
                finish();
                break;
            case R.id.but_YES_label:
                String label_one = et_label_one.getText().toString();
                String label_two = et_label_two.getText().toString();
                String label_three = et_label_three.getText().toString();
                String label_four  = et_label_four.getText().toString();
                Toast.makeText(this,"" + label_one + label_two ,1).show();
                break;
        }
    }
}
