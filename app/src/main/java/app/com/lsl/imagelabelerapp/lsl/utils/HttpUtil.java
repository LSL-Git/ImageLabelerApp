package app.com.lsl.imagelabelerapp.lsl.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import app.com.lsl.imagelabelerapp.lsl.model.UserModel;

/** http请求方式
 * Created by M1308_000 on 2017/5/1.
 */

public class HttpUtil implements UserModel {
    DataLoadOnListener listener;
    // 云服务器地址
    String urlPath = "http://114.115.141.43:4040/webServer/";

//    String urlPath = "http://192.168.1.101/webServer/";
    @Override
    public void loadData(final Map<String, String> map, final String Type, final DataLoadOnListener listener) {
        urlPath += Type;
        this.listener = listener;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int code;
                try {
                    String result_url = StrUtils.GetRequestString(urlPath, map, "UTF-8");
                    URL url = new URL(result_url);
                    Log.e("HttpUtil", result_url);
                    // 获取连接
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.disconnect();
                    // 设置请求方式
                    httpURLConnection.setRequestMethod("GET");
                    // 设置连接超时为5秒
                    httpURLConnection.setConnectTimeout(5000);

                    httpURLConnection.connect();
                    code = httpURLConnection.getResponseCode();
                    Log.e("HttpUtil","code:" + code);

                    if (code == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        String RESULT = StrUtils.readMyInputStream(inputStream);

                        // 更新UI
                        Message message = new Message();
                        message.obj = RESULT;
                        handler.sendMessage(message);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 回调请求结果
            listener.CallBack(msg.obj);
        }
    };

}

