package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.view.UserView;
import app.com.lsl.imagelabelerapp.lsl.adapter.ImageAdapter;
import app.com.lsl.imagelabelerapp.lsl.base.Base64Image;
import app.com.lsl.imagelabelerapp.lsl.presenter.UserPresenter;
import app.com.lsl.imagelabelerapp.lsl.utils.DbUtils;

import static app.com.lsl.imagelabelerapp.lsl.activity.LoginActivity.AUTO_LOGIN;
import static app.com.lsl.imagelabelerapp.lsl.activity.LoginActivity.SPF_USERINFO;
import static app.com.lsl.imagelabelerapp.lsl.utils.DbUtils.GetImgUrl;
import static app.com.lsl.imagelabelerapp.lsl.utils.FileUtils.CreateDirInSDCard;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, UserView {

    private ImageView iv_user_icon;
    private View headerView;
    private NavigationView navigationView;
    private TextView tv_user_task_status;
    private TextView tv_user_integral;
    private TextView tv_user_name;
    private TextView tv_user_status;
    private String user_name;
    private SharedPreferences spf,spf2;
    public static final String USER_ID = "user_id";		// 用户id
    public static final String USER_NAME = "user_name";	// 用户名
    public static final String USER_PSW = "user_psw";	// 用户密码
    public static final String USER_TEL = "user_tel";	// 用户电话号码
    public static final String USER_EMAIL = "user_email";	// 用户邮箱
    public static final String USER_INTEGRAL = "user_integral";	// 用户积分
    public static final String USER_ICON = "icon_name";		// 用户头像名称
    public static final String USER_ICON_CODE = "icon_code";
    public static final String USER_TASK_COMPLETION = "task_completion";	// 用户任务完成情况
    public static final String IS_MANAGER = "is_manager";	// 是否为管理员
    public static final String USER_LABEL_ALL_NUM = "label_all_num";	// 总标签数
    public static final String USER_LABEL_SUCCESS_NUM = "label_success_num";	// 标签成功数
    public static final String USER_ICON_PATH = "/mnt/sdcard/LabelerApp/icon/";
    public static final String SPF_USERALLINFO = "UserAllInfo";
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private long FirstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Connector.getDatabase();//创建数据库
            Log.e("MainActivity","create database success!!!");
        } catch (Exception e) {
            Log.e("MainActivity","create database fail!!!" + e.getMessage());
            e.printStackTrace();
        }

       // 加载控件
        initLayout();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    // 加载数据
    private void initLayout() {
        LoadData(); // 从服务器下载相关数据

        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // 找到头像id 并设置点击监听
        headerView = navigationView.getHeaderView(0);

        // 流布局图片显示部分
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adapter = new ImageAdapter();
        recyclerView.setAdapter(adapter);
        adapter.replaceAll(GetImgUrl());
        // 流布局图片显示部分

        iv_user_icon = (ImageView) headerView.findViewById(R.id.iv_user_icon);
        tv_user_status = (TextView) headerView.findViewById(R.id.tv_status);
        tv_user_name = (TextView) headerView.findViewById(R.id.tv_name);
        tv_user_integral = (TextView) headerView.findViewById(R.id.tv_integral);
        tv_user_task_status = (TextView) headerView.findViewById(R.id.tv_task);

        iv_user_icon.setOnClickListener(this);

    }

    // 加载数据
    private void LoadData() {
        CreateDirInSDCard();
        // 获取用户名
        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        spf = getSharedPreferences(SPF_USERALLINFO, Context.MODE_WORLD_READABLE);
        // 根据用户名请求用户的所有信息
        String TYPE = "userinfo";
        Map<String,String> map = new HashMap<>();
        map.put("user_name",user_name);
        map.put("type","query_user_info");
        new UserPresenter(this, map, TYPE).fetch();

        // 获取图片URL列表
        String TYPE2 = "images";
        Map<String,String> map2 = new HashMap<>();
        map2.put("type","GetImgUrl");
        new UserPresenter(this, map2, TYPE2).fetch();

    }

    /**
     * 得到图片的URL列表 模拟数据
     * @return
     */
    public ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("http://192.168.1.101:8080/ImageServer/servlet/LoadImage/i.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760755_6715.jpeg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760724_2371.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760707_4653.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760706_6864.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760706_9279.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760704_2341.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760704_5707.jpg");
        list.add("http://192.168.1.101/webServer/LoadImage/商场15.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760685_4444.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760684_8827.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760683_3691.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760683_7315.jpg");
        list.add("http://img.my.csdn.net/uploads/201508/05/1438760663_7318.jpg");
        list.add("http://192.168.1.101:8080/ImageServer/servlet/LoadImage/i.jpg");
        list.add("http://192.168.1.101:8080/ImageServer/servlet/LoadImage/i.jpg");
        return list;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_task) {
            Toast.makeText(MainActivity.this, "今日任务", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_guess_your_like) {
            Toast.makeText(MainActivity.this, "猜你喜欢", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_history) {
            Toast.makeText(MainActivity.this, "标签历史", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manager) {
            Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
            this.startActivity(intent);

            Toast.makeText(MainActivity.this, "我是管理员", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_exit_acc) {
            spf2 = getSharedPreferences(SPF_USERINFO, Context.MODE_WORLD_READABLE);
            spf2.edit().putBoolean(AUTO_LOGIN, false).commit();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            this.startActivity(intent);
            finish();
            Toast.makeText(MainActivity.this, "退出当前用户", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_icon:
                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void ShowBackMsg(Object obj) {
        SaveUserInfo(obj.toString());
        ShowUserInfo();

        try {
            JSONObject object = new JSONObject(obj.toString());
            int num = object.getInt("num");
            DbUtils.SaveImgUrl(object,num);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 显示用户一些信息
     */
    private void ShowUserInfo() {
        boolean is_manager = spf.getBoolean(IS_MANAGER, false);

        if (is_manager) {
            tv_user_status.setText("MANAGER");
        } else {
            tv_user_status.setText("USER");
        }

        tv_user_name.setText(spf.getString(USER_NAME,""));
        tv_user_integral.setText("积分 " + spf.getString(USER_INTEGRAL,""));
        tv_user_task_status.setText("今日进度 " + spf.getString(USER_TASK_COMPLETION,""));
        Bitmap bitmap = BitmapFactory.decodeFile(spf.getString(USER_ICON,""));
        iv_user_icon.setImageBitmap(bitmap);
    }

    /**
     * 保存用户信息
     * @param jsonStr
     */
    private void SaveUserInfo(String jsonStr) {
        Log.e("MainActivity","json : " + jsonStr);
        try {
            JSONObject json = new JSONObject(jsonStr);
            SharedPreferences.Editor editor = spf.edit();
            editor.putString(USER_ID, json.getString(USER_ID));
            editor.putString(USER_NAME, json.getString(USER_NAME));
            editor.putString(USER_TEL, json.getString(USER_TEL));
            editor.putString(USER_EMAIL, json.getString(USER_EMAIL));
            editor.putString(USER_ICON, USER_ICON_PATH + json.getString(USER_ICON));
//            editor.putString(USER_ICON_CODE, json.getString(USER_ICON_CODE));
            editor.putString(USER_INTEGRAL, json.getString(USER_INTEGRAL));
            editor.putString(USER_TASK_COMPLETION, json.getString(USER_TASK_COMPLETION));
            if (json.getString(IS_MANAGER).equals("1")) {
                editor.putBoolean(IS_MANAGER,true);
            } else {
                editor.putBoolean(IS_MANAGER,false);
            }
            editor.putString(USER_LABEL_ALL_NUM, json.getString(USER_LABEL_ALL_NUM));
            editor.putString(USER_LABEL_SUCCESS_NUM, json.getString(USER_LABEL_SUCCESS_NUM));
            editor.commit();

            Base64Image.GenerateImage(json.getString(USER_ICON_CODE),USER_ICON_PATH + json.getString(USER_ICON));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - FirstTime > 2000) {
                Toast.makeText(MainActivity.this,"双击退出",Toast.LENGTH_SHORT).show();
                FirstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
