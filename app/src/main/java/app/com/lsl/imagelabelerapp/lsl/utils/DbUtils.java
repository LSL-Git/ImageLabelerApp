package app.com.lsl.imagelabelerapp.lsl.utils;

import android.database.Cursor;
import android.util.Log;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import app.com.lsl.imagelabelerapp.lsl.dbtable.ImageURLTable;
import app.com.lsl.imagelabelerapp.lsl.dbtable.PicFileTable;
import app.com.lsl.imagelabelerapp.lsl.dbtable.PicTypeAndNumTb;
import app.com.lsl.imagelabelerapp.lsl.dbtable.SearchHistoryTable;

/** 数据库操作类
 * Created by M1308_000 on 2017/5/19.
 */

public class DbUtils {

    private static final String TAG = "DbUtils";
    private static String picPath ;

    /**
     * 获取图片搜索历史内容
     * @param user
     * @return
     */
    public static List<String> GetSearchHistory(String user) {
        List<String> list = new ArrayList<>();
        List<SearchHistoryTable> search_history_list = DataSupport.select("SearchContent")
                .where("UserName = ?", user).order("SearchTime desc").limit(10).find(SearchHistoryTable.class);
       if (search_history_list.size() != 0) {
           for (SearchHistoryTable r : search_history_list) {
               list.add(r.getSearchContent());
           }
       }
       return list;
    }

    /**
     * 保存搜索记录
     * @param content   搜索内容
     * @param user  当前用户
     */
    public static void SaveSearchRecord(String content, String user) {
        SearchHistoryTable searchHistory = new SearchHistoryTable();
        int num = 0;
        Cursor cursor = DataSupport.findBySQL("select SearchContent, SearchNum from SearchHistoryTable where " +
                "SearchContent = '" + content + "' and UserName = '" + user + "'");

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                num = cursor.getInt(1);
            }
            searchHistory.setSearchNum(num + 1);
            searchHistory.setSearchTime(DateUtils.getNowTime());
            int row = searchHistory.updateAll("SearchContent = ? and UserName = ?", content, user);
            Log.e(TAG, "update " + row);
        } else {
            searchHistory.setSearchContent(content);
            searchHistory.setUserName(user);
            searchHistory.setSearchNum(1);
            searchHistory.setSearchTime(DateUtils.getNowTime());
            if (searchHistory.save()) {
                Log.e(TAG, "search record save success");
            }
        }
        cursor.close();
    }

    /**
     * 根据图片类型获取图片存储路径
     * @param PicType
     * @param path
     * @return
     */
    public static String GetPicPath(String PicType, String path) {
        picPath = path;
        int level;
        String parentName;
        List<PicFileTable> picFileList = DataSupport.select("Level","parentName")
                .where("fileName = ?", PicType).find(PicFileTable.class);
        if (picFileList.size() != 0) {
            for (PicFileTable picFileTable : picFileList) {
                level = picFileTable.getLevel();
                parentName = picFileTable.getParentName();
                picPath += parentName + "/";
                if (level > 2)
                    GetPicPath(parentName,picPath);
            }
        }
        return s(picPath);
    }

    /**
     * 倒置文件路径
     * @param Path
     * @return
     */
    private static String s(String Path) {
        String result = "/";
        String [] str = Path.split("/");
        for (int i = str.length - 1; i > 0; i--)
            result += str[i] + "/";
        return result;
    }

    /**
     * 根据图片类型获取该类型图片数量
     * @param PicType
     * @return
     */
    public static int GetPicNum(String PicType) {
        int num = 0;
        List<PicTypeAndNumTb> numList = DataSupport.select("PicNum")
                .where("PicType = ?", PicType).find(PicTypeAndNumTb.class);
        if (numList.size() != 0) {
            for (PicTypeAndNumTb picType : numList) {
                num = picType.getPicNum();
            }
        }
        return num;
    }

    /**
     * 获取图片所有种类
     * @return
     */
    public static ArrayList<String> GetPicType() {
        ArrayList<String> lists = new ArrayList<>();
        List<PicTypeAndNumTb> typeList = DataSupport.select("PicType").find(PicTypeAndNumTb.class);
        if (typeList.size() != 0) {
            for (PicTypeAndNumTb picType : typeList) {
                lists.add(picType.getPicType());
            }
        }
        return lists;
    }

    /**
     * 保存图片类型和数量
     * @param picType
     * @param picNum
     */
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
