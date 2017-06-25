package app.com.lsl.imagelabelerapp.lsl.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.adapter.FeedbackAdapter;
import app.com.lsl.imagelabelerapp.lsl.model.FeedbackList;

/**
 * Created by M1308_000 on 2017/6/26.
 */

public class FeedbackSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv_fb;
    private List<FeedbackList> fb_list;
    private FeedbackAdapter feedbackAdapter;


    private void initMenu() {
        TopMenuHeader topMenuHeader = new TopMenuHeader(getWindow().getDecorView());
        topMenuHeader.topMenuTitle.setText("用户反馈信息");
        topMenuHeader.topMenuTitle.setTextSize(20);
        topMenuHeader.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuLeft.setText("设置");
        topMenuHeader.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuRight.setVisibility(View.GONE);
        topMenuHeader.topMenuLeft.setOnClickListener(this);
        topMenuHeader.ivTopMenuLeft.setOnClickListener(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_feedback);
        initMenu();

        initViews();
    }

    private void initViews() {
        lv_fb = (ListView) findViewById(R.id.lv_feedback_list);
        fb_list = initData();
        feedbackAdapter = new FeedbackAdapter(fb_list, FeedbackSearchActivity.this);
        lv_fb.setAdapter(feedbackAdapter);
    }

    private List<FeedbackList> initData() {
        List<FeedbackList> list = new ArrayList<>();
        list.add(new FeedbackList("已处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("已处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("已处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("已处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        list.add(new FeedbackList("未处理","张三","2017-06-15 22:24:23","叫你公安奇偶的及高级按基金公安局"));
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_icon:
                finish();
                break;
            case R.id.top_menu_left:
                finish();
                break;
        }
    }
}
