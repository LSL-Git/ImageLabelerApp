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

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.App.User;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.activity.view.UserView;
import app.com.lsl.imagelabelerapp.lsl.presenter.UserPresenter;
import app.com.lsl.imagelabelerapp.lsl.utils.DbUtils;
import app.com.lsl.imagelabelerapp.lsl.utils.JsonUtils;
import okhttp3.Call;


/** 图片标签活动
 * Created by M1308_000 on 2017/5/17.
 */

public class ImageLabelActivity extends AppCompatActivity implements View.OnClickListener, UserView{

    private static final String TAG = "ImageLabelActivity";

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
    private static int iv5 = 0;
    private static int iv6 = 0;
    private static int iv7 = 0;
    private static int iv8 = 0;
    private static int iv9 = 0;
    private String la_1;
    private String la_2;
    private String la_3;
    private String la_4;
    private String la_5;
    private String la_6;
    private String la_7;
    private String la_8;
    private String la_9;
    private String la_10;
    private String picName;

    private static final String COMM = "COMMIT";
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
        String [] s = img_url.split("/");
        picName = s[s.length - 1];

    }

    /**
     * 判断标签是否有相同的
     * @param label
     * @param id
     * @return
     */
    private boolean isSame(String label, int id) {
        boolean same = false;
        if (!TextUtils.isEmpty(la_1) && id != 1) same = la_1.equals(label);
        if (!TextUtils.isEmpty(la_2) && id != 2) same = la_2.equals(label);
        if (!TextUtils.isEmpty(la_3) && id != 3) same = la_3.equals(label);
        if (!TextUtils.isEmpty(la_4) && id != 4) same = la_4.equals(label);
        if (!TextUtils.isEmpty(la_5) && id != 5) same = la_5.equals(label);
        if (!TextUtils.isEmpty(la_6) && id != 6) same = la_6.equals(label);
        if (!TextUtils.isEmpty(la_7) && id != 7) same = la_7.equals(label);
        if (!TextUtils.isEmpty(la_8) && id != 8) same = la_8.equals(label);
        if (!TextUtils.isEmpty(la_9) && id != 9) same = la_9.equals(label);
        if (!TextUtils.isEmpty(la_10) && id != 10) same = la_10.equals(label);
        return same;
    }

    /**
     * 将标签结果上传服务器
     * @param label1
     * @param label2
     * @param label3
     * @param label4
     * @param label5
     * @param label6
     * @param label7
     * @param label8
     * @param label9
     * @param label10
     */
    private void UpLoadData(String label1, String label2, String label3, String label4, String label5,
                            String label6, String label7, String label8, String label9, String label10) {
        String TYPE = "PicLabelServlet";
        Map<String, String> map = new HashMap<>();
        map.put("userName", User.getUser());
        map.put("picName", picName);
        map.put("type","putlabels");
        map.put("label1", label1);
        map.put("label2", label2);
        map.put("label3", label3);
        map.put("label4", label4);
        map.put("label5", label5);
        map.put("label6", label6);
        map.put("label7", label7);
        map.put("label8", label8);
        map.put("label9", label9);
        map.put("label10", label10);

        new UserPresenter(this, map, TYPE).fetch();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_YES_label:
                la_1 = et_label_1.getText().toString().trim();
                la_2 = et_label_2.getText().toString().trim();
                la_3 = et_label_3.getText().toString().trim();
                la_4 = et_label_4.getText().toString().trim();
                la_5 = et_label_5.getText().toString().trim();
                la_6 = et_label_6.getText().toString().trim();
                la_7 = et_label_7.getText().toString().trim();
                la_8 = et_label_8.getText().toString().trim();
                la_9 = et_label_9.getText().toString().trim();
                la_10 = et_label_10.getText().toString().trim();

                if (!TextUtils.isEmpty(la_1)) {
                    if (isSame(la_1,1) || isSame(la_2,2) || isSame(la_3,3) || isSame(la_4,4) || isSame(la_5,5) || isSame(la_6,6)
                            || isSame(la_7,7) || isSame(la_8,8) || isSame(la_9,9) || isSame(la_10,10)) {
                        Toast.makeText(ImageLabelActivity.this,"Err:存在相同标签", Toast.LENGTH_SHORT).show();
                    } else {
                        UpLoadData(la_1,la_2,la_3,la_4,la_5,la_6,la_7,la_8,la_9,la_10);
                    }
                } else {
                    Toast.makeText(ImageLabelActivity.this,"请输入标签",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.iv_add_et_4:
                et_label_4.setEnabled(false);
                ll_et_5.setVisibility(View.VISIBLE);
                iv_add_et5.setImageResource(R.mipmap.nsp);
                iv_add_et5.setVisibility(View.VISIBLE);
                iv5 = 0;
                iv_add_et4.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_add_et_5:
                if (iv5 == 1) {
                    iv_add_et5.setVisibility(View.INVISIBLE);
                    ll_et_6.setVisibility(View.VISIBLE);
                    et_label_5.setEnabled(false);
                } else {
                    et_label_4.setEnabled(true);
                    ll_et_5.setVisibility(View.INVISIBLE);
                    iv_add_et4.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_add_et_6:
                if (iv6 == 1) {
                    et_label_6.setEnabled(false);
                    iv_add_et6.setVisibility(View.INVISIBLE);
                    ll_et_7.setVisibility(View.VISIBLE);
                } else {
                    et_label_5.setEnabled(true);
                    ll_et_6.setVisibility(View.INVISIBLE);
                    iv_add_et5.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_add_et_7:
                if (iv7 == 1) {
                    et_label_7.setEnabled(false);
                    iv_add_et7.setVisibility(View.INVISIBLE);
                    ll_et_8.setVisibility(View.VISIBLE);
                } else {
                    et_label_6.setEnabled(true);
                    ll_et_7.setVisibility(View.INVISIBLE);
                    iv_add_et6.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_add_et_8:
                if (iv8 == 1) {
                    et_label_8.setEnabled(false);
                    iv_add_et8.setVisibility(View.INVISIBLE);
                    ll_et_9.setVisibility(View.VISIBLE);
                } else {
                    et_label_7.setEnabled(true);
                    ll_et_8.setVisibility(View.INVISIBLE);
                    iv_add_et7.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_add_et_9:
                if (iv9 == 1) {
                    et_label_9.setEnabled(false);
                    iv_add_et9.setVisibility(View.INVISIBLE);
                    ll_et_10.setVisibility(View.VISIBLE);
                } else {
                    et_label_8.setEnabled(true);
                    ll_et_9.setVisibility(View.INVISIBLE);
                    iv_add_et8.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_add_et_10:
                ll_et_10.setVisibility(View.INVISIBLE);
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
                                iv_add_et5.setVisibility(View.VISIBLE);
                                iv5 = 1;
                                if (!TextUtils.isEmpty(et_label_6.getText().toString().trim())) {
                                    et_label_7.setEnabled(true);
                                    et_label_5.setEnabled(false);
                                    iv_add_et5.setVisibility(View.INVISIBLE);
                                    iv_add_et6.setImageResource(R.mipmap.ouv);
                                    iv_add_et6.setVisibility(View.VISIBLE);
                                    iv6 = 1;
                                    if (!TextUtils.isEmpty(et_label_7.getText().toString().trim())) {
                                        et_label_8.setEnabled(true);
                                        et_label_6.setEnabled(false);
                                        iv_add_et6.setVisibility(View.INVISIBLE);
                                        iv_add_et7.setImageResource(R.mipmap.ouv);
                                        iv_add_et7.setVisibility(View.VISIBLE);
                                        iv7 = 1;
                                        if (!TextUtils.isEmpty(et_label_8.getText().toString().trim())) {
                                            et_label_9.setEnabled(true);
                                            et_label_7.setEnabled(false);
                                            iv_add_et7.setVisibility(View.INVISIBLE);
                                            iv_add_et8.setImageResource(R.mipmap.ouv);
                                            iv_add_et8.setVisibility(View.VISIBLE);
                                            iv8 = 1;
                                            if (!TextUtils.isEmpty(et_label_9.getText().toString().trim())) {
                                                et_label_10.setEnabled(true);
                                                et_label_8.setEnabled(false);
                                                iv_add_et8.setVisibility(View.INVISIBLE);
                                                iv_add_et9.setImageResource(R.mipmap.ouv);
                                                iv_add_et9.setVisibility(View.VISIBLE);
                                                iv9 = 1;
                                                if (!TextUtils.isEmpty(et_label_10.getText().toString().trim())) {
                                                    et_label_9.setEnabled(false);
                                                    iv_add_et9.setVisibility(View.INVISIBLE);
                                                    iv_add_et10.setVisibility(View.INVISIBLE);
                                                } else if (TextUtils.isEmpty(et_label_10.getText().toString().trim())){
                                                    et_label_9.setEnabled(true);
                                                    iv_add_et10.setImageResource(R.mipmap.nsp);
                                                    iv_add_et10.setVisibility(View.VISIBLE);
                                                }
                                            } else if (TextUtils.isEmpty(et_label_9.getText().toString().trim())){
                                                et_label_10.setEnabled(false);
                                                iv_add_et9.setImageResource(R.mipmap.nsp);
                                                iv_add_et9.setVisibility(View.VISIBLE);
                                                iv9 = 0;
                                                et_label_8.setEnabled(true);
                                            }
                                        } else if (TextUtils.isEmpty(et_label_8.getText().toString().trim())){
                                            et_label_9.setEnabled(false);
                                            iv_add_et8.setImageResource(R.mipmap.nsp);
                                            iv_add_et8.setVisibility(View.VISIBLE);
                                            iv8 = 0;
                                        }
                                    } else if (TextUtils.isEmpty(et_label_7.getText().toString().trim())){
                                        et_label_8.setEnabled(false);
                                        iv_add_et7.setImageResource(R.mipmap.nsp);
                                        iv_add_et7.setVisibility(View.VISIBLE);
                                        iv7 = 0;
                                    }
                                } else if (TextUtils.isEmpty(et_label_6.getText().toString().trim())){
                                    et_label_7.setEnabled(false);
                                    iv_add_et6.setImageResource(R.mipmap.nsp);
                                    iv_add_et6.setVisibility(View.VISIBLE);
                                    iv6 = 0;
                                }
                            } else if (TextUtils.isEmpty(et_label_5.getText().toString().trim())){
                                et_label_6.setEnabled(false);
                                iv_add_et5.setImageResource(R.mipmap.nsp);
                                iv_add_et5.setVisibility(View.VISIBLE);
                                iv5 = 0;
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

    @Override
    public void ShowLoading() {
        Toast.makeText(this, "upload...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowBackMsg(Object obj) {
        try {
            String result = JsonUtils.LoginAndRegisterJson(obj.toString());
            if (result.equals("OK")) {
                if (DbUtils.UpdateTaskPicState(picName, COMM)) {
                    Toast.makeText(ImageLabelActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ImageLabelActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ImageLabelActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
