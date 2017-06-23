package app.com.lsl.imagelabelerapp.lsl.utils;

import org.json.JSONException;
import org.json.JSONObject;

/** 解析json数据类
 * Created by M1308_000 on 2017/5/12.
 */

public class JsonUtils {
    private static final String TAG = "jsonUtils";
    private static final String FILEINFO = "FileInfo";
    private static final String PICTYPENUM = "PicTypeNum";
    private static final String COUNT = "Count";
    private static final String DATA = "data";
    private static int count;
    public static final String NOP = "NOP";

    /**
     * 解析任务图片信息
     * @param data
     * @throws JSONException
     */
    public static void TaskPicUrl(Object data) throws JSONException {
        JSONObject json = new JSONObject(data.toString());
        int count = json.length();
        for (int i = 1; i <= count; i++) {
            JSONObject da = json.getJSONObject(DATA + i);
            String picName = da.getString("picName");
            String picUrl = da.getString("picUrl");
            int batch = da.getInt("batch");
//            Log.e(TAG, "name:" + picName);
//            Log.e(TAG, "url:" + picUrl);
//            Log.e(TAG,"batch:" + batch);
            DbUtils.SaveTaskPicInfo(picName, picUrl, batch, NOP,DateUtils.getNowTime());
        }

    }

    public static void UserName(Object data) throws JSONException {
        JSONObject json = new JSONObject(data.toString());
        int count = json.getInt(COUNT);
        JSONObject name_json = json.getJSONObject(DATA);
        for (int i = count; i > 0; i--) {
            DbUtils.SaveAllUserName(name_json.getString("user_name" + i));
        }
    }

    /**
     * 解析图片信息
     * @param fileName
     * @param data
     * @throws JSONException
     */
    public static void SavePicInfo(String fileName,Object data) throws JSONException {
        JSONObject json = new JSONObject(data.toString());
        int count = json.getInt(COUNT);

        for (int i = count; i > 0; i--) {
            JSONObject dataJson = json.getJSONObject(DATA + i);
            DbUtils.SavePicInfo(fileName,dataJson.getString("pic_name"),
                    dataJson.getString("pic_label"),dataJson.getString("finish_label_time"));
        }
    }

    /**
     * \解析登录或注册返回来的信息
     * @param result
     * @return
     * @throws JSONException
     */
    public static String LoginAndRegisterJson(String result) throws JSONException {
        JSONObject json = new JSONObject(result);
        String Result = json.getString("result");
        return Result;
    }

    /**
     * 解析图片信息并保存数据库
     * @param data
     */
    public static void SaveFileInfo(Object data) throws JSONException {
        JSONObject json = new JSONObject(data.toString());
        JSONObject FileInfoJson = json.getJSONObject(FILEINFO);//解析文件目录数据
        JSONObject PicTypeNumJson = json.getJSONObject(PICTYPENUM); // 解析图片类型和数量

        count = FileInfoJson.getInt(COUNT);
        SaveFileInfo(FileInfoJson, count);

        count = PicTypeNumJson.getInt(COUNT);
        SavePicTypeNum(PicTypeNumJson, count);
    }

    /**
     * 解析Pic Type Num data的每条数据，并保存数据库
     * @param data
     * @param count
     * @throws JSONException
     */
    private static void SavePicTypeNum(JSONObject data, int count) throws JSONException{
        JSONObject json = data.getJSONObject(DATA);
        String picType;
        int picNum;
        for (int i = 1; i <= count; i++) {
            picType = json.getString("PicType" + i);
            picNum = json.getInt("PicNum" + i);
            // 保存至数据库
            DbUtils.SavePicTypeNum(picType,picNum);
        }
    }

    /**
     * 解析File Info data的每条数据，并保存数据库
     * @param data
     * @param count
     * @throws JSONException
     */
    private static void SaveFileInfo(JSONObject data, int count) throws JSONException {
        JSONObject json = data.getJSONObject(DATA);
        int level;
        String parentName;
        int fileNum;
        String fileName;
        for (int i = 1; i <= count; i++) {
            level = json.getInt("Level" + i);
            parentName = json.getString("ParentName" + i);
            fileName = json.getString("FileName" + i);
            fileNum = json.getInt("FileNum" + i);
            // 保存数据到数据库
            DbUtils.SavePicFile(level, fileName, fileNum, parentName);
        }
    }
}
