package app.com.lsl.imagelabelerapp.lsl.utils;

import android.util.Log;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import app.com.lsl.imagelabelerapp.lsl.createtable.ImageURLTable;

/** 数据库操作类
 * Created by M1308_000 on 2017/5/19.
 */

public class DbUtils {

    /**
     * 保存图片URL等信息到数据库
     * @param object
     * @param num
     * @throws Exception
     */
    public static void SaveImgUrl(JSONObject object, int num) throws Exception{


        for (int i = 0; i < num; i++) {
            JSONObject json = object.getJSONObject("img" + i);

            ImageURLTable urlTable = new ImageURLTable();
            List<ImageURLTable> urlTables = DataSupport.select("img_url").
                    where("img_name = ?",json.getString("img_name")).find(ImageURLTable.class); // 判断是否存在相同条目
            if (urlTables.size() == 0) {
                urlTable.setImg_id(i);
                urlTable.setImg_name(json.getString("img_name"));
                urlTable.setImg_url(json.getString("imgUrl"));
                urlTable.setUpload_time(json.getString("upload_time"));
                urlTable.setIs_label(0);
                if (urlTable.save()) {
                    Log.e("DbUtils", "url save success");
                }
            } else {
                //Log.e("DbUtils", "data is exist");
            }
        }
    }

    /**
     * 从数据库获取未标签的图片URL
     * @return
     */
    public static ArrayList<String> GetImgUrl() {
        ArrayList<String> lists = new ArrayList<>();

        try {
            List<ImageURLTable> urlTables = DataSupport.select("img_url","is_label").find(ImageURLTable.class);
            for (ImageURLTable urlTable : urlTables) {
                if (urlTable.getIs_label() == 0) {
                    lists.add(urlTable.getImg_url());
                }
            }
        } catch (Exception e) {
            Log.e("DbUtils:", " " + e.getMessage());
        }

        return lists;
    }

}
