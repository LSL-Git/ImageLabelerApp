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
    public static void SavePicInfo(Object data) throws JSONException {
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
