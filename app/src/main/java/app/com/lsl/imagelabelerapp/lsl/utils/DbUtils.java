package app.com.lsl.imagelabelerapp.lsl.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.dbtable.AllUserNameTb;
import app.com.lsl.imagelabelerapp.lsl.dbtable.BatchTb;
import app.com.lsl.imagelabelerapp.lsl.dbtable.ImageURLTable;
import app.com.lsl.imagelabelerapp.lsl.dbtable.LabelsTb;
import app.com.lsl.imagelabelerapp.lsl.dbtable.PicFileTable;
import app.com.lsl.imagelabelerapp.lsl.dbtable.PicTable;
import app.com.lsl.imagelabelerapp.lsl.dbtable.PicTypeAndNumTb;
import app.com.lsl.imagelabelerapp.lsl.dbtable.SearchHistoryTable;
import app.com.lsl.imagelabelerapp.lsl.dbtable.TaskPicUrlTb;
import app.com.lsl.imagelabelerapp.lsl.model.HistoryList;
import app.com.lsl.imagelabelerapp.lsl.model.UserList;

import static app.com.lsl.imagelabelerapp.lsl.activity.ImageLabelActivity.COMM;
import static app.com.lsl.imagelabelerapp.lsl.utils.JsonUtils.NOP;

/** 数据库操作类
 * Created by M1308_000 on 2017/5/19.
 */

public class DbUtils {

    private static final String TAG = "DbUtils";
    private static String picPath ;
    private static int nop;
    private static int commit;

    /**
     * 获取当前编号
     * @return
     */
    public static int GetBatch() {
        List<BatchTb> list = DataSupport.select("batch").find(BatchTb.class);
        int re = 0;
        for (BatchTb b : list) {
            re = b.getBatch();
        }
        return re;
    }

    /**
     * 保存当前批次号
     * @param batch
     */
    public static void SaveToDayBatch(int batch) {
        BatchTb tb = new BatchTb();
        List<BatchTb> list = DataSupport.select("batch").find(BatchTb.class);
        if (list.size() == 0) {
            tb.setBatch(batch);
            if (tb.save())
                Log.e(TAG, "batch save success");
        } else {
            ContentValues values = new ContentValues();
            values.put("batch", batch);
            DataSupport.updateAll(BatchTb.class, values);
        }
    }

    /**
     * 更新其他图片URL的标签信息
     * @param picName
     * @param isLabel
     */
    public static void UpdateImgUrlInfo(String picName, int isLabel) {
        ContentValues values = new ContentValues();
        values.put("is_label",isLabel);
        int row = DataSupport.updateAll(ImageURLTable.class, values, "img_name = ?", picName);
//        Log.e(TAG, "update " + row + " item");
    }

    /**
     * 获取今日标签的图片信息
     * @param userName
     * @return
     */
    public static List<HistoryList> GetTodayLabelsInfo(String userName) {
        List<LabelsTb> labelsTbs = DataSupport.select("labels, commitTime, labelState, picUrl")
                .where("User = ?", userName).find(LabelsTb.class);
        List<HistoryList> lists = new ArrayList<>();
        if (labelsTbs.size() != 0) {
            for (LabelsTb info : labelsTbs) {
                try {
                    if (DateUtils.IsToday(info.getCommitTime()))
                        lists.add(new HistoryList(info.getPicUrl(), info.getLabels(),
                                info.getCommitTime(), info.getLabelState()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return lists;
    }

    /**
     * 获取所有标签信息
     * @param userName
     */
    public static List<HistoryList> GetLabelsInfo(String userName) {
        List<LabelsTb> labelsTbs = DataSupport.select("labels, commitTime, labelState, picUrl")
                .where("User = ?", userName).find(LabelsTb.class);
        List<HistoryList> lists = new ArrayList<>();
        if (labelsTbs.size() != 0) {
            for (LabelsTb info : labelsTbs) {
                lists.add(new HistoryList(info.getPicUrl(), info.getLabels(),
                        info.getCommitTime(), info.getLabelState()));
            }
        }
        return lists;
    }

    /**
     * 更新标签信息表
     * @param labels
     * @param cTime
     * @param State
     * @param user
     * @param picName
     * @return
     */
    public static boolean UpdateLabelsInfo(String labels, String cTime, String State,
                                        String user, String picName) {
        LabelsTb labelsTb = new LabelsTb();
        labelsTb.setCommitTime(cTime);
        labelsTb.setLabels(labels);
        labelsTb.setLabelState(State);
        int re = labelsTb.updateAll("picName = ? and User = ?", picName, user);
        return re > 0;
    }

    /**
     * 保存图片标签信息
     * @param labels
     * @param cTime
     * @param State
     * @param picName
     * @param picUrl
     * @param User
     */
    public static void SaveLabelsInfo(String labels, String cTime, String State, String picName,
                                      String picUrl, String User) {
        LabelsTb labelsTb = new LabelsTb();
        Cursor cursor = DataSupport.findBySQL("select * from LabelsTb" + " where picName = '" +
                picName + "' and User = '" + User + "'");
        if (cursor.getCount() == 0) {
            labelsTb.setLabels(labels);
            labelsTb.setCommitTime(cTime);
            labelsTb.setPicName(picName);
            labelsTb.setLabelState(State);
            labelsTb.setPicUrl(picUrl);
            labelsTb.setUser(User);
            if (labelsTb.save()) {
                Log.e(TAG, "labels info save success");
            }
        }
    }

    /**
     * 获取今日任务完成量与总任务量的比
     * @param userName
     * @return
     */
    public static String GetTaskState(String userName, int batch) {
        Cursor cursor = DataSupport.findBySQL("select COUNT(State) as nop from TaskPicUrlTb " +
                "where State = '" + NOP + "' and user = '" + userName + "' and batch = " + batch);
        if (0 < cursor.getCount()) {
            while(cursor.moveToNext()) {
                nop = cursor.getInt(cursor.getColumnIndex("nop"));
            }
        }

        Cursor cursor2 = DataSupport.findBySQL("select COUNT(State) as comm from TaskPicUrlTb " +
                "where State = '" + COMM + "' and user = '" + userName + "' and batch = " + batch);
        if (0 < cursor2.getCount()) {
            while(cursor2.moveToNext()) {
                commit = cursor2.getInt(cursor2.getColumnIndex("comm"));
            }
        }
//        Log.e(TAG, "nop" + nop);
//        Log.e(TAG, "commit" + commit);
        return commit + "/" + (commit + nop);
    }

    /**
     * 更新图片任务状态
     * @param picName
     * @param State
     * @return
     */
    public static boolean UpdateTaskPicState(String picName, String State) {
        TaskPicUrlTb picUrlTb = new TaskPicUrlTb();
        picUrlTb.setState(State);
        picUrlTb.setCommitTime(DateUtils.getNowTime());
        int r = picUrlTb.updateAll("picName = ?", picName);
        return r > 0;
    }

    /**
     * 根据状态获取任务图片URL
     * @param State
     * @return
     */
    public static ArrayList<String> GetTaskPicUrl(String State, String userName, int batch) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = DataSupport.findBySQL("select picUrl from TaskPicUrlTb where State = '" + State
                + "' and user = '" + userName + "' and batch = " + batch);

        if (cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
//                Log.e(TAG, cursor.getString(cursor.getColumnIndex("picurl")));
                list.add(cursor.getString(cursor.getColumnIndex("picurl")));
            }
        }
//        List<TaskPicUrlTb> urlTbList = DataSupport.select("picUrl").where("State = ?", State).find(TaskPicUrlTb.class);
//        if (urlTbList.size() > 0) {
//            for (TaskPicUrlTb url : urlTbList) {
//                list.add(url.getPicUrl());
//            }
//        }
        return list;
    }

    /**
     * 保存任务图片信息
     * @param picName
     * @param picUrl
     * @param batch
     * @param state
     * @param nowTime
     */
    public static void SaveTaskPicInfo(String picName, String picUrl, int batch
            , String state, String nowTime, String userName) {
        TaskPicUrlTb picUrlTb = new TaskPicUrlTb();
        Cursor cursor = DataSupport.findBySQL("select picName,batch from TaskPicUrlTb " +
                "where picName = '" + picName + "'" + " and user = '" + userName + "'" +
                " and batch = " + batch);
        SaveToDayBatch(batch);
        if (cursor.getCount() == 0) {
            picUrlTb.setPicName(picName);
            picUrlTb.setPicUrl(picUrl);
            picUrlTb.setBatch(batch);
            picUrlTb.setState(state);
            picUrlTb.setUser(userName);
            picUrlTb.setGetTaskTime(nowTime);

            if (picUrlTb.save())
                Log.e(TAG, "task pic url save success!");
        }
    }

    /**
     * 获取所有用户名
     * @return
     */
    public static ArrayList<UserList> GetAllUserName() {
        ArrayList<UserList> lists = new ArrayList<>();
        List<AllUserNameTb> nameTbList = DataSupport.select("userName").find(AllUserNameTb.class);
        if (nameTbList.size() != 0) {
            for (AllUserNameTb name : nameTbList) {
                lists.add(new UserList(getIconId(), name.getUserName()));
            }
        }
        return lists;
    }

    /**
     * 获取图片id
     * @return
     */
    private static int getIconId() {
        int i = (int) (1 + Math.random() * 10);
        int icon = 0;
        switch (i) {
            case 1:
                icon = R.mipmap.icon1;
                break;
            case 2:
                icon = R.mipmap.icon2;
                break;
            case 3:
                icon = R.mipmap.icon3;
                break;
            case 4:
                icon = R.mipmap.icon4;
                break;
            case 5:
                icon = R.mipmap.icon5;
                break;
            case 6:
                icon = R.mipmap.icon6;
                break;
            case 7:
                icon = R.mipmap.icon7;
                break;
            case 8:
                icon = R.mipmap.icon8;
                break;
            case 9:
                icon = R.mipmap.icon9;
                break;
            case 10:
                icon = R.mipmap.icon10;
                break;
        }
        return icon;
    }

    /**
     * 保存所有用户昵称
     * @param userName
     */
    public static void SaveAllUserName(String userName) {
        AllUserNameTb userNameTb = new AllUserNameTb();
        List<AllUserNameTb> allUserNameTbs = DataSupport.select("userName")
                .where("userName = ?", userName).find(AllUserNameTb.class);
        if (allUserNameTbs.size() == 0) {
            userNameTb.setUserName(userName);
            if (userNameTb.save()) {
                Log.e(TAG, "user name save success");
            }
        }
    }

    /**
     * 获取完成标签化时间
     * @param picName
     */
    public static String GetFinishTime(String picName) {
        String finishTime = "2017-06-11 14:32:45.0";
        List<PicTable> picList = DataSupport.select("FinishTime")
                .where("picName = ?", picName).find(PicTable.class);
        if (picList.size() != 0) {
            for (PicTable pic : picList) {
                finishTime = pic.getFinishTime();
            }
        }
        return finishTime;
    }

    /**
     * 获取图片的所有标签
     * @param picName
     */
    public static Map<String, String> GetPicLabels(String picName, int no) {
        List<PicTable> picList = DataSupport.select("PicLabel")
                .where("picName = ?", picName).find(PicTable.class);

        Map<String, String> map = new HashMap<>();
        if (picList.size() != 0) {
            for (PicTable pic : picList) {
                map.put("label" + no++, pic.getPicLabel());
            }
        }
        map.put("no", no + "");
        return map;
    }

    /**
     * 根据图片类型获取图片所有名称
     * @param fileName
     * @return
     */
    public static List<String> GetPicName(String fileName) {
        List<String> list = new ArrayList<>();
        List<PicTable> picList = DataSupport.select("picName").where("parentFile = ?", fileName).find(PicTable.class);
        if (picList.size() != 0) {
            for (PicTable pic : picList) {
                list.add(pic.getPicName());
            }
        }
        return list;
    }

    /**
     * 保存完成标签化图片的相关信息
     * @param parentName
     * @param picName
     * @param picLabel
     * @param picFinishTime
     */
    public static void SavePicInfo(String parentName, String picName, String picLabel, String picFinishTime) {
        PicTable picTable = new PicTable();
        Cursor cr = DataSupport.findBySQL("select * from PicTable where picName = '" + picName + "' and" +
                " PicLabel = '" + picLabel + "'");
        if (cr.getCount() == 0) {
            picTable.setParentFile(parentName);
            picTable.setPicName(picName);
            picTable.setPicLabel(picLabel);
            picTable.setFinishTime(picFinishTime);
            if (picTable.save())
                Log.e(TAG, "pic info save success");
        }
    }

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
    public static void SaveImgUrl(JSONObject object, int num) {
        try {
            for (int i = 0; i < num; i++) {
                JSONObject json = object.getJSONObject("img" + i);

                ImageURLTable urlTable = new ImageURLTable();
                List<ImageURLTable> urlTables = DataSupport.select("img_url").
                        where("img_name = ?",json.getString("img_name")).find(ImageURLTable.class); // 判断是否存在相同条目
//                Log.e("SaveImgUrl",urlTables.size() + "+++" + num);
                if (urlTables.size() == 0) {
                    urlTable.setImg_id(i);
                    urlTable.setImg_name(json.getString("img_name"));
                    urlTable.setImg_url(json.getString("imgUrl"));
                    urlTable.setUpload_time(json.getString("upload_time"));
                    urlTable.setIs_label(json.getInt("state"));
                    if (urlTable.save()) {
                        Log.e("DbUtils", "url save success");
                    } else {
                        Log.e("DbUtils", "url save failed");
                    }
                } else {
                    Log.e("DbUtils", "data is exist");
                }
            }
        } catch (Exception e) {
            Log.e("SaveUrl", e.getMessage());
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
