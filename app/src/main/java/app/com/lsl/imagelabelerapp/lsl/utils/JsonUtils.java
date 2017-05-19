package app.com.lsl.imagelabelerapp.lsl.utils;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/** 解析json数据类
 * Created by M1308_000 on 2017/5/12.
 */

public class JsonUtils {

    private static SharedPreferences spf;

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

}
