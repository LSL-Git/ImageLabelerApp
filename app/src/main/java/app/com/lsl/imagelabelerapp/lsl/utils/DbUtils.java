package app.com.lsl.imagelabelerapp.lsl.utils;

import android.util.Log;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import app.com.lsl.imagelabelerapp.lsl.dbtable.ImageURLTable;
import app.com.lsl.imagelabelerapp.lsl.dbtable.PicFileTable;
import app.com.lsl.imagelabelerapp.lsl.dbtable.PicTypeAndNumTb;

/** 数据库操作类
 * Created by M1308_000 on 2017/5/19.
 */

public class DbUtils {

    private static final String TAG = "DbUtils";

    public static void SavePicTypeNum(String picType, int picNum) {
        PicTypeAndNumTb typeAndNumTb = new PicTypeAndNumTb();
        List<PicTypeAndNumTb> typeAndNumTbList = DataSupport.select("PicType")
                .where("PicType = ?", picType).find(PicTypeAndNumTb.class);
        if (typeAndNumTbList.size() == 0) {
            typeAndNumTb.setPicType(picType);
            typeAndNumTb.setPicNum(picNum);
            if (typeAndNumTb.save()) {
                Log.e(TAG, "pic types num save success");
            }
        }
    }

    /**
     * 保存图片文件目录信息
     * @param Level
     * @param fileName
     * @param Num
     * @param parentName
     */
    public static void SavePicFile(int Level, String fileName, int Num, String parentName) {
        PicFileTable picFileTable = new PicFileTable();
        List<PicFileTable> picFileList = DataSupport.select("fileName")
                .where("fileName = ?", fileName).find(PicFileTable.class);
        if (picFileList.size() == 0) {
            picFileTable.setLevel(Level);
            picFileTable.setFileName(fileName);
            picFileTable.setParentName(parentName);
            picFileTable.setNum(Num);
            if (picFileTable.save()) {
                Log.e(TAG, "file info save success");
            }
        }
    }

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
