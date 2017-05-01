package app.com.lsl.imagelabelerapp.lsl.utils;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.lsl.App.MyApplication;
import app.com.lsl.imagelabelerapp.lsl.activity.AppAcitivities;
import app.com.lsl.imagelabelerapp.lsl.activity.LoginActivity;
import app.com.lsl.imagelabelerapp.lsl.activity.MainActivity;
import app.com.lsl.imagelabelerapp.lsl.activity.RegisterActivity;

/** http方式请求 已弃用
 * Created by M1308_000 on 2017/4/29.
 */

public class HttpUtils implements Runnable{
    // 服务器地址
    String urlPath = "http://192.168.1.101/webServer/";
    // 返回码
    int code;

    Map<String, String> map = new HashMap<>();

    public HttpUtils(Map<String, String> map, String type) {
        this.map = map;
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

                // 解析登录请求返回值
                JSONObject json = new JSONObject(RESULT);
                String result = json.getString("result");
                Log.e("HttpUtils", "result:" + result);
                // 更新UI
                Message message = new Message();
                message.obj = result;
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
            String result = msg.obj.toString();

            if (result.equals("Login_Success")) { // 登录成功
                Toast.makeText(MyApplication.getContext(),"" + result, Toast.LENGTH_SHORT).show();
                // 登录成功，跳转到主页面
                Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);

            } else if (result.equals("Psw_Err")){// 密码错误
                Toast.makeText(MyApplication.getContext(),"" + result, Toast.LENGTH_SHORT).show();

            } else if (result.equals("User_Not_Exist")) {// 用户不存在
                Toast.makeText(MyApplication.getContext(),"" + result, Toast.LENGTH_SHORT).show();
            } else if (result.equals("Login_Fail")){    // 登录失败
                Toast.makeText(MyApplication.getContext(),"" + result, Toast.LENGTH_SHORT).show();
            } else if (result.equals("User_Name_Exist")) {  // 用户名已存在
                Toast.makeText(MyApplication.getContext(),"" + result, Toast.LENGTH_SHORT).show();
            } else if (result.equals("Register_Success")) { // 注册成功
                // 注册成功。跳转到登录页面
                Intent intent2 = new Intent(MyApplication.getContext(), LoginActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent2);
                AppAcitivities.removeActivity(new RegisterActivity());

                Toast.makeText(MyApplication.getContext(),"" + result, Toast.LENGTH_SHORT).show();
            } else {    // 注册失败
                Toast.makeText(MyApplication.getContext(),"" + result, Toast.LENGTH_SHORT).show();
            }
        }
    };


}
