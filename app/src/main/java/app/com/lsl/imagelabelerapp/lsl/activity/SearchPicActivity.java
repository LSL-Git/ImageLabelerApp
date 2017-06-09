package app.com.lsl.imagelabelerapp.lsl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.App.User;
import app.com.lsl.imagelabelerapp.lsl.activity.view.SearchView;
import app.com.lsl.imagelabelerapp.lsl.adapter.SearchAdapter;
import app.com.lsl.imagelabelerapp.lsl.model.Bean;
import app.com.lsl.imagelabelerapp.lsl.utils.DbUtils;
import app.com.lsl.imagelabelerapp.lsl.utils.HttpUtils;

import static app.com.lsl.imagelabelerapp.lsl.activity.MainActivity.GETPICINFO;

/** 根据图片名称检索图片
 * Created by M1308_000 on 2017/6/7.
 */

public class SearchPicActivity extends AppCompatActivity implements SearchView.SearchViewListener{

    /**
     * 搜索结果列表view
     */
    private ListView lvResults;

    /**
     * 搜索view
     */
    private SearchView searchView;


    /**
     * 热搜框列表adapter
     */
    private ArrayAdapter<String> hintAdapter;

    /**
     * 自动补全列表adapter
     */
    private ArrayAdapter<String> autoCompleteAdapter;

    /**
     * 搜索结果列表adapter
     */
    private SearchAdapter resultAdapter;

    private List<Bean> dbData;

    /**
     * 热搜版数据
     */
    private List<String> hintData;

    /**
     * 搜索过程中自动补全数据
     */
    private List<String> autoCompleteData;

    /**
     * 搜索结果的数据
     */
    private List<Bean> resultData;

    /**
     * 默认提示框显示项的个数
     */
    private static int DEFAULT_HINT_SIZE = 6;

    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;
    private EditText SearchEtInput;
    private ListView lvTips;

    /**
     * 设置提示框显示项的个数
     *
     * @param hintSize 提示框显示个数
     */
    public static void setHintSize(int hintSize) {
        SearchPicActivity.hintSize = hintSize;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pic);
        initData();
        initViews();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        lvResults = (ListView) findViewById(R.id.main_lv_search_results);
        searchView = (SearchView) findViewById(R.id.main_search_layout);
        // 设置监听
        searchView.setSearchViewListener(this);
        // 设置adapter
        searchView.setHistoryAdapter(hintAdapter);
        searchView.setmAutoCompleteAdapter(autoCompleteAdapter);

        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private String picNum;
            private TextView tv_num;
            private Intent intent;
            private String fileName;
            private String filePath;
            private TextView tv_content;
            private TextView tv_title;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                tv_title = (TextView) view.findViewById(R.id.item_search_tv_title);
                tv_content = (TextView) view.findViewById(R.id.item_search_tv_content);
                tv_num = (TextView) view.findViewById(R.id.item_search_tv_comments);

                filePath = tv_content.getText().toString().trim();
                fileName = tv_title.getText().toString().trim();
                picNum = tv_num.getText().toString().trim();

                getPicInfo(fileName);

                intent = new Intent(SearchPicActivity.this, ShowSearchResultActivity.class);
                intent.putExtra("filePath", filePath);
                intent.putExtra("fileName", fileName);
                intent.putExtra("picNum", Integer.parseInt(picNum));
                startActivity(intent);

                Toast.makeText(SearchPicActivity.this, position + "" + fileName, Toast.LENGTH_SHORT).show();
            }
        });

        SearchEtInput = (EditText) findViewById(R.id.search_et_input);
        lvTips = (ListView) findViewById(R.id.search_lv_tips);

    }

    /**
     * 根据该类图片名称获取该类图片信息
     * @param fileName
     */
    private void getPicInfo(String fileName) {
        Map<String,String> map = new HashMap<>();
        map.put("type", "GetPicInfo");
        map.put("fileName", fileName);
        new Thread(new HttpUtils(map, GETPICINFO)).start();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 从数据库获取数据
        getDbData();
        // 初始化历史搜索数据
        getHintData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {
        if (autoCompleteData == null) {
            // 初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取自动 data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < dbData.size() && count < hintSize; i++) {
                if (dbData.get(i).getTitle().contains(text.trim())) {
                    autoCompleteData.add(dbData.get(i).getTitle());
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, autoCompleteData);
        } else  {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取搜索结果data和adapter
     * @param text
     */
    private void getResultData(String text) {
        if (resultData == null) {
            // 初始化
            resultData = new ArrayList<>();
        } else {
            resultData.clear();
            for (int i = 0; i < dbData.size(); i++) {
                if (dbData.get(i).getTitle().contains(text.trim())) {
                    resultData.add(dbData.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new SearchAdapter(this, resultData, R.layout.search_item_bean_list);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取db 数据
     */
    private void getDbData() {
        ArrayList<String> picTypeList = DbUtils.GetPicType();
        int size = picTypeList.size();
        dbData = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String picType = picTypeList.get(i);
            dbData.add(new Bean(R.mipmap.icon, picType, DbUtils.GetPicPath(picType,"/")
                    + picType, DbUtils.GetPicNum(picType) + ""));
        }
    }

    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData() {
        hintData = new ArrayList<>();
        hintData = DbUtils.GetSearchHistory(User.getUser());
        hintAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hintData);
    }

    /**
     * 当搜索框文本改变时，触发回调，更新自动补全数据
     * @param text 传入补全后的文本
     */
    @Override
    public void onRefreshAutoComplete(String text) {
        // 更新数据
        getAutoCompleteData(text);
    }

    /**
     * \点击搜索键时edit text触发的回调
     * @param text 传入输入框的文本
     */
    @Override
    public void onSearch(String text) {
        // 更新result数据
        getResultData(text);
        lvResults.setVisibility(View.VISIBLE);
        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            lvResults.setAdapter(resultAdapter);
        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();
        }

        String searchContent = SearchEtInput.getText().toString().trim();   // 获取搜索框的内容
        if (!TextUtils.isEmpty(searchContent)) {
            // 保存搜索记录
            DbUtils.SaveSearchRecord(searchContent, User.getUser());
        }

    }

    /**
     * 当输入框为空时，点击list view之外空白处隐藏list view
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (TextUtils.isEmpty(SearchEtInput.getText().toString()))
            lvTips.setVisibility(View.GONE);
        return super.onTouchEvent(event);
    }
}
