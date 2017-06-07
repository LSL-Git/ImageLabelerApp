package app.com.lsl.imagelabelerapp.lsl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.com.lsl.imagelabelerapp.R;

/** 所有用户名列表
 * Created by M1308_000 on 2017/5/19.
 */

public class UserNameListActivity extends AppCompatActivity {

    private ListView lv_user_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_name_list);
        AppActivities.addActivity(this);

        ArrayList<String> list = new ArrayList<>();
        list.add("n安静爱回家me");
        list.add("n安静爱回家me");
        list.add("n安静爱回家me");
        list.add("n安静爱回家me");
        list.add("ewre");
        list.add("ewre");
        list.add("ewre");
        list.add("ewre");
        list.add("ewre");
        list.add("ewre");
        list.add("name");
        list.add("n发的e");
        list.add("n发的e");
        list.add("n发的e");
        list.add("n发的e");
        list.add("n发的e");
        list.add("n发的e");
        list.add("n发的e");
        list.add("name");
        lv_user_name = (ListView) findViewById(R.id.lv_user_name_list);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv_user_name.setAdapter(adapter);


    }
}
