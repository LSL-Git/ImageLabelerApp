package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.menu.TopMenuHeader;
import app.com.lsl.imagelabelerapp.lsl.adapter.UserListAdapter;
import app.com.lsl.imagelabelerapp.lsl.model.UserList;
import app.com.lsl.imagelabelerapp.lsl.utils.DbUtils;

/** 所有用户名列表
 * Created by M1308_000 on 2017/5/19.
 */

public class UserNameListActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_user_name;
    private List<UserList> userLists;
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_name_list);
        AppActivities.addActivity(this);
        initMenu();

        lv_user_name = (ListView) findViewById(R.id.lv_user_name_list);

        userLists = DbUtils.GetAllUserName();
        userListAdapter = new UserListAdapter(this, userLists);

        lv_user_name.setAdapter(userListAdapter);

        lv_user_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_userName = (TextView) view.findViewById(R.id.tv_user_list_item_name);
                Intent intent = new Intent(UserNameListActivity.this, mUserInfoActivity.class);
                intent.putExtra("userName",tv_userName.getText().toString());
                startActivity(intent);
            }
        });

    }

    private void initMenu() {
        TopMenuHeader topMenuHeader = new TopMenuHeader(getWindow().getDecorView());
        topMenuHeader.topMenuTitle.setText("所有用户");
        topMenuHeader.topMenuTitle.setTextSize(20);
        topMenuHeader.topMenuTitle.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuLeft.setText("返回");
        topMenuHeader.topMenuLeft.setTextColor(Color.parseColor("#33CCB6"));
        topMenuHeader.topMenuRight.setVisibility(View.GONE);
        topMenuHeader.topMenuLeft.setOnClickListener(this);
        topMenuHeader.ivTopMenuLeft.setOnClickListener(this);
    }

    /**
     * 加载数据
     * @return
     */
    private List<UserList> initData() {
        List<UserList> userLists = new ArrayList<>();
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"TomCat"));
        userLists.add(new UserList(R.mipmap.ic_launcher,"TomCat"));
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"TomCat"));
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"TomCat"));
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"TomCat"));
        userLists.add(new UserList(R.mipmap.ic_launcher,"TomCat"));
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"TomCat"));
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"NMB"));
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"TomCat"));
        userLists.add(new UserList(R.mipmap.ic_launcher,"TomCat"));
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"Tom"));
        userLists.add(new UserList(R.mipmap.e587562aa756ccac8766c63_p,"TomCat"));
        return userLists;
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
