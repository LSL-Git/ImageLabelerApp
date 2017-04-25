package app.com.lsl.imagelabelerapp.lsl.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/** 网络请求相关
 * Created by M1308_000 on 2017/4/25.
 */

public class ServerUtil {

    private static final String IP = "192.168.1.101";
    private static final int PORT = 54321;
    private static Socket socket = null;
    private static String isRegSuccess;

    public static String Server(Map map) {
        try {
           socket = new Socket(IP, PORT);
            Log.e("ServerUtil.Server()", "服务器连接成功");

            // 将json转化为String类型    
            JSONObject json = new JSONObject(map);
            String jsonStr = "";
            jsonStr = json.toString();

            // 将String转化为byte[]  
            byte[] jsonByte = jsonStr.getBytes();
            DataOutputStream ops = null;
            ops = new DataOutputStream(socket.getOutputStream());
            Log.e("ServerUtil.Server()", "开始发送的数据");
            ops.write(jsonByte);
            ops.flush();
            Log.e("ServerUtil.Server()", "传输数据完毕");

            socket.shutdownOutput();

            // 读取服务器端数据    
            DataInputStream input = null;
            String strinput = "";
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            strinput = input.readUTF();
            Log.e("RegisterTask", "返回信息为："+ strinput);
            JSONObject js = new JSONObject(strinput);
            Log.e("RegisterTask", "" + js.get("Back_Msg"));
            isRegSuccess = js.getString("Back_Msg");

            // 如接收到 "OK" 则断开连接  

            if (js != null) {
                Log.e("RegisterTask", "客户端将关闭连接");
                Thread.sleep(500);
            }

        } catch (Exception e) {
            Log.e("ServerUtil.Server()",e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    Log.e("ServerUtil.Server()",e.getMessage());
                }
            }
        }

        return isRegSuccess;
    }
}
