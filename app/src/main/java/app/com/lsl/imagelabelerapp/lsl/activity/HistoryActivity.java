package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.App.User;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.adapter.HistoryAdapter;
import app.com.lsl.imagelabelerapp.lsl.model.HistoryList;
import app.com.lsl.imagelabelerapp.lsl.utils.DbUtils;

import static app.com.lsl.imagelabelerapp.lsl.utils.DateUtils.getDataToString;

/** 查看历史页面
 * Created by M1308_000 on 2017/6/23.
 */

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private String type;
    private ListView lv_history;
    private List<HistoryList> historyLists;
    private HistoryAdapter historyAdapter;
    private TextView tv_time;
    private String ctime;
    private String ctimeStr;
    private TextView tv_labels;
    private ImageView iv_icon;
    private String labels;

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
        if (type.equals("今日完成")) {
            historyLists = DbUtils.GetTodayLabelsInfo(User.getUser());
        } else if (type.equals("标签历史")) {
            historyLists = DbUtils.GetLabelsInfo(User.getUser());
        }

        historyAdapter = new HistoryAdapter(HistoryActivity.this, historyLists);
        // 按时间进行排序，优先显示最新标签信息
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
        lv_history.setAdapter(historyAdapter);

        lv_history.setOnItemClickListener(new myOnItemClickListener());
    }

    private class myOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            tv_time = (TextView) view.findViewById(R.id.tv_history_label_time);
            tv_labels = (TextView) view.findViewById(R.id.tv_history_labels);
            iv_icon = (ImageView) view.findViewById(R.id.iv_history);

            labels = tv_labels.getText().toString();

            ctimeStr = tv_time.getText().toString();
            ctime = ctimeStr.substring(3, ctimeStr.length());
            if (ctimeStr.substring(0,2).equals("今天")) {
                Toast.makeText(HistoryActivity.this, labels + "" + ctime + iv_icon.getResources(), Toast.LENGTH_SHORT).show();

            }
        }
    }


    private List<HistoryList> getHistoryLists() {
        List<HistoryList> lists = new ArrayList<>();
        lists.add(new HistoryList("","hi爱奇偶及公安", "2012-12-12 12:30", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2012-12-12 10:20:55.9", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2012-12-11 10:21", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2011-12-11 10:20:55.9", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2012-12-13 01:03", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2012-12-10 02:04", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2017-12-15 23:00", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2012-11-12 22:30", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2012-12-17 08:24:55.9", "yes"));
        lists.add(new HistoryList("","hi爱奇偶及公安", "2012-11-10 11:10", "yes"));
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
