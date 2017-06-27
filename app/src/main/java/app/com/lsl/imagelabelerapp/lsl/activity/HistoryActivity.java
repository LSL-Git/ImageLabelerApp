package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.adapter.HistoryAdapter;
import app.com.lsl.imagelabelerapp.lsl.model.HistoryList;

/** 查看历史页面
 * Created by M1308_000 on 2017/6/23.
 */

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private String type;
    private ListView lv_history;
    private List<HistoryList> historyLists;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        initViews();
        initMenu();
    }

    private void initViews() {
        lv_history = (ListView) findViewById(R.id.lv_history_list);
        historyLists = getHistoryLists();
        historyAdapter = new HistoryAdapter(HistoryActivity.this, historyLists);
        Collections.sort(historyLists, new Comparator<HistoryList>() {
            @Override
            public int compare(HistoryList o1, HistoryList o2) {
                Date date1 = getDataToString(o1.getLabelTime());
                Date data2 = getDataToString(o2.getLabelTime());
                if (date1.before(data2)) {
                    return 1;
                }
                return -1;
            }
        });
//        historyAdapter.
        lv_history.setAdapter(historyAdapter);
    }

    private static Date getDataToString(String s) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date data = simpleDateFormat.parse(s, position);
        return data;
    }

    private List<HistoryList> getHistoryLists() {
        List<HistoryList> lists = new ArrayList<>();
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2012-12-12 12:30", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2012-12-12 10:20:55.9", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2012-12-11 10:21", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2011-12-11 10:20:55.9", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2012-12-13 01:03", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2012-12-10 02:04", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2017-12-15 23:00", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2012-11-12 22:30", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2012-12-17 08:24:55.9", "yes"));
        lists.add(new HistoryList(0,"hi爱奇偶及公安", "2012-11-10 11:10", "yes"));
        return lists;
    }


    private void initMenu() {
        TopMenuHeader topMenu = new TopMenuHeader(getWindow().getDecorView());
        topMenu.topMenuTitle.setText(type);
        topMenu.topMenuTitle.setTextSize(20);
        topMenu.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenu.topMenuLeft.setText("返回");
        topMenu.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenu.topMenuRight.setVisibility(View.GONE);
        topMenu.topMenuLeft.setOnClickListener(this);
        topMenu.ivTopMenuLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_menu_left:
                finish();
                break;
            case R.id.iv_icon:
                finish();
                break;
        }
    }
}
