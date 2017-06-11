package app.com.lsl.imagelabelerapp.lsl.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/** http方式请求 已弃用
 * Created by M1308_000 on 2017/4/29.
 */

public class HttpUtils implements Runnable{
    private String TAG = "HttpUtils";
    // 服务器地址
    String urlPath = "http://114.115.141.43:4040/webServer/";
    // 返回码
    int code;

    private static String TYPE = "";

    Map<String, String> map = new HashMap<>();

    public HttpUtils(Map<String, String> map, String type) {
        this.map = map;
        TYPE = type;
        urlPath += type;
    }

    @Override
    public void run() {
        try {
            String result_url = StrUtils.GetRequestString(urlPath, map, "UTF-8");
            URL url = new URL(result_url);
            Log.e("HttpUtils", result_url);
            // 获取连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.disconnect();
            // 设置请求方式
            httpURLConnection.setRequestMethod("GET");
            // 设置连接超时为5秒
            httpURLConnection.setConnectTimeout(5000);
            code = httpURLConnection.getResponseCode();

            Log.e("HttpUtils","code:" + code);
            if (code == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                String RESULT = StrUtils.readMyInputStream(inputStream);

                Message message = new Message();
                message.obj = RESULT;
                handler.sendMessage(message);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String type = map.get("type");
            String fileName = map.get("fileName");
            try {
                if (type.equals("GetFileInfo")) {
                    JsonUtils.SaveFileInfo(msg.obj);

                } else if (type.equals("GetPicInfo")) {
                    JsonUtils.SavePicInfo(fileName,msg.obj);

                } else if (type.equals("get_all_user_name")) {
                    JsonUtils.UserName(msg.obj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };


}
