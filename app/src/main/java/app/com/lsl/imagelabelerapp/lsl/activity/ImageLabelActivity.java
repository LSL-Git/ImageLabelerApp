package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import okhttp3.Call;


/** 图片标签活动
 * Created by M1308_000 on 2017/5/17.
 */

public class ImageLabelActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Intent intent;
    private String img_url;
    private EditText et_label_1;
    private EditText et_label_2;
    private EditText et_label_3;
    private EditText et_label_4;
    private EditText et_label_5;
    private EditText et_label_6;
    private EditText et_label_7;
    private EditText et_label_8;
    private EditText et_label_9;
    private EditText et_label_10;
    private Button but_yes_label;
    private ImageView iv_add_et4;
    private LinearLayout ll_et_5;
    private ImageView iv_add_et5;
    private ImageView iv_add_et6;
    private ImageView iv_add_et7;
    private ImageView iv_add_et8;
    private ImageView iv_add_et9;
    private ImageView iv_add_et10;
    private LinearLayout ll_et_6;
    private LinearLayout ll_et_7;
    private LinearLayout ll_et_8;
    private LinearLayout ll_et_9;
    private LinearLayout ll_et_10;

    private int visible = 0;
    private final long add = 2130903065;
//    private final long remove =

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Layout();
        initMenu();

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

    private void initMenu() {
        TopMenuHeader topMenu = new TopMenuHeader(getWindow().getDecorView());
        topMenu.topMenuTitle.setText("图片标签");
        topMenu.topMenuTitle.setTextSize(20);
        topMenu.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenu.topMenuLeft.setText("返回");
        topMenu.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenu.topMenuRight.setVisibility(View.GONE);
        topMenu.topMenuLeft.setOnClickListener(this);
        topMenu.ivTopMenuLeft.setOnClickListener(this);
    }

    // 加载控件
    private void Layout() {
        AppActivities.addActivity(this);
        setContentView(R.layout.activity_img);
        imageView = (ImageView) findViewById(R.id.iv_show_image_one);
        but_yes_label = (Button) findViewById(R.id.but_YES_label);
        et_label_1 = (EditText) findViewById(R.id.et_label_1);
        et_label_2 = (EditText) findViewById(R.id.et_label_2);
        et_label_3 = (EditText) findViewById(R.id.et_label_3);
        et_label_4 = (EditText) findViewById(R.id.et_label_4);
        et_label_5 = (EditText) findViewById(R.id.et_label_5);
        et_label_6 = (EditText) findViewById(R.id.et_label_6);
        et_label_7 = (EditText) findViewById(R.id.et_label_7);
        et_label_8 = (EditText) findViewById(R.id.et_label_8);
        et_label_9 = (EditText) findViewById(R.id.et_label_9);
        et_label_10 = (EditText) findViewById(R.id.et_label_10);

        iv_add_et4 = (ImageView) findViewById(R.id.iv_add_et_4);
        iv_add_et5 = (ImageView) findViewById(R.id.iv_add_et_5);
        iv_add_et6 = (ImageView) findViewById(R.id.iv_add_et_6);
        iv_add_et7 = (ImageView) findViewById(R.id.iv_add_et_7);
        iv_add_et8 = (ImageView) findViewById(R.id.iv_add_et_8);
        iv_add_et9 = (ImageView) findViewById(R.id.iv_add_et_9);
        iv_add_et10 = (ImageView) findViewById(R.id.iv_add_et_10);
        ll_et_5 = (LinearLayout) findViewById(R.id.ll_et_5);
        ll_et_6 = (LinearLayout) findViewById(R.id.ll_et_6);
        ll_et_7 = (LinearLayout) findViewById(R.id.ll_et_7);
        ll_et_8 = (LinearLayout) findViewById(R.id.ll_et_8);
        ll_et_9 = (LinearLayout) findViewById(R.id.ll_et_9);
        ll_et_10 = (LinearLayout) findViewById(R.id.ll_et_10);

        but_yes_label.setOnClickListener(this);
        iv_add_et4.setOnClickListener(this);
        iv_add_et5.setOnClickListener(this);
        iv_add_et6.setOnClickListener(this);
        iv_add_et7.setOnClickListener(this);
        iv_add_et8.setOnClickListener(this);
        iv_add_et9.setOnClickListener(this);
        iv_add_et10.setOnClickListener(this);

        et_label_1.addTextChangedListener(watcher);
        et_label_2.addTextChangedListener(watcher);
        et_label_3.addTextChangedListener(watcher);
        et_label_4.addTextChangedListener(watcher);
        et_label_5.addTextChangedListener(watcher);
        et_label_6.addTextChangedListener(watcher);
        et_label_7.addTextChangedListener(watcher);
        et_label_8.addTextChangedListener(watcher);
        et_label_9.addTextChangedListener(watcher);
        et_label_10.addTextChangedListener(watcher);

        intent = getIntent();
        img_url = intent.getStringExtra("img_url");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_YES_label:
                String label_one = et_label_1.getText().toString();
                String label_two = et_label_2.getText().toString();
                String label_three = et_label_3.getText().toString();
                String label_four  = et_label_4.getText().toString();
                Toast.makeText(this,"" + label_one + label_two ,1).show();
                break;
            case R.id.iv_add_et_4:
                ll_et_5.setVisibility(View.VISIBLE);
                iv_add_et5.setImageResource(R.mipmap.nsp);
                iv_add_et4.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_add_et_5:
                ll_et_6.setVisibility(View.VISIBLE);
                iv_add_et6.setImageResource(R.mipmap.nsp);
                iv_add_et5.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_add_et_6:
                ll_et_7.setVisibility(View.VISIBLE);
                iv_add_et7.setImageResource(R.mipmap.nsp);
                iv_add_et6.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_add_et_7:
                ll_et_8.setVisibility(View.VISIBLE);
                iv_add_et8.setImageResource(R.mipmap.nsp);
                iv_add_et7.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_add_et_8:
                ll_et_9.setVisibility(View.VISIBLE);
                iv_add_et9.setImageResource(R.mipmap.nsp);
                iv_add_et8.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_add_et_9:
                ll_et_10.setVisibility(View.VISIBLE);
                iv_add_et10.setImageResource(R.mipmap.nsp);
                iv_add_et9.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_add_et_10:

                break;
            case R.id.top_menu_left:
                finish();
                break;
            case R.id.iv_icon:
                finish();
                break;
        }
    }


    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Toast.makeText(ImageLabelActivity.this,visible + "changed..." + s.toString(), Toast.LENGTH_SHORT).show();
            if (!TextUtils.isEmpty(et_label_4.getText().toString().trim()) && visible == 0) {
                iv_add_et4.setImageResource(R.mipmap.ouv);
                iv_add_et4.setVisibility(View.VISIBLE);
                visible = 1;
            } else if (TextUtils.isEmpty(et_label_4.getText().toString().trim())) {
                iv_add_et4.setVisibility(View.INVISIBLE);
                visible = 0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(et_label_1.getText().toString().trim())) {
                et_label_2.setEnabled(true);
                if (!TextUtils.isEmpty(et_label_2.getText().toString().trim())) {
                    et_label_3.setEnabled(true);
                    et_label_1.setEnabled(false);
                    if (!TextUtils.isEmpty(et_label_3.getText().toString().trim())) {
                        et_label_4.setEnabled(true);
                        et_label_2.setEnabled(false);
                        if (!TextUtils.isEmpty(et_label_4.getText().toString().trim())) {
                            et_label_5.setEnabled(true);
                            et_label_3.setEnabled(false);
                            if (!TextUtils.isEmpty(et_label_5.getText().toString().trim())) {
                                et_label_6.setEnabled(true);
                                et_label_4.setEnabled(false);
                                iv_add_et5.setImageResource(R.mipmap.ouv);
                                if (!TextUtils.isEmpty(et_label_6.getText().toString().trim())) {
                                    et_label_7.setEnabled(true);
                                    et_label_5.setEnabled(false);
                                    iv_add_et6.setImageResource(R.mipmap.ouv);
                                    if (!TextUtils.isEmpty(et_label_7.getText().toString().trim())) {
                                        et_label_8.setEnabled(true);
                                        et_label_6.setEnabled(false);
                                        iv_add_et7.setImageResource(R.mipmap.ouv);
                                        if (!TextUtils.isEmpty(et_label_8.getText().toString().trim())) {
                                            et_label_9.setEnabled(true);
                                            et_label_7.setEnabled(false);
                                            iv_add_et8.setImageResource(R.mipmap.ouv);
                                            if (!TextUtils.isEmpty(et_label_9.getText().toString().trim())) {
                                                et_label_10.setEnabled(true);
                                                et_label_8.setEnabled(false);
                                                iv_add_et9.setImageResource(R.mipmap.ouv);
                                                if (!TextUtils.isEmpty(et_label_10.getText().toString().trim())) {
                                                    et_label_9.setEnabled(false);
                                                } else if (TextUtils.isEmpty(et_label_10.getText().toString().trim())){
                                                    et_label_9.setEnabled(true);
                                                }
                                            } else if (TextUtils.isEmpty(et_label_9.getText().toString().trim())){
                                                et_label_10.setEnabled(false);
                                                iv_add_et9.setImageResource(R.mipmap.nsp);
                                                et_label_8.setEnabled(true);
                                            }
                                        } else if (TextUtils.isEmpty(et_label_8.getText().toString().trim())){
                                            et_label_9.setEnabled(false);
                                            iv_add_et8.setImageResource(R.mipmap.nsp);
                                            et_label_7.setEnabled(true);
                                        }
                                    } else if (TextUtils.isEmpty(et_label_7.getText().toString().trim())){
                                        et_label_8.setEnabled(false);
                                        iv_add_et7.setImageResource(R.mipmap.nsp);
                                        et_label_6.setEnabled(true);
                                    }
                                } else if (TextUtils.isEmpty(et_label_6.getText().toString().trim())){
                                    et_label_7.setEnabled(false);
                                    iv_add_et6.setImageResource(R.mipmap.nsp);
                                    et_label_5.setEnabled(true);
                                }
                            } else if (TextUtils.isEmpty(et_label_5.getText().toString().trim())){
                                et_label_6.setEnabled(false);
                                et_label_4.setEnabled(true);
                                iv_add_et5.setImageResource(R.mipmap.nsp);
                            }
                        } else if (TextUtils.isEmpty(et_label_4.getText().toString().trim())){
                            et_label_5.setEnabled(false);
                            et_label_3.setEnabled(true);
                        }
                    } else if (TextUtils.isEmpty(et_label_3.getText().toString().trim())){
                        et_label_4.setEnabled(false);
                        et_label_2.setEnabled(true);
                    }
                } else if (TextUtils.isEmpty(et_label_2.getText().toString().trim())){
                    et_label_3.setEnabled(false);
                    et_label_1.setEnabled(true);
                }
            } else if (TextUtils.isEmpty(et_label_1.getText().toString().trim())){
                et_label_2.setEnabled(false);
            }
        }
    };
}
