package app.com.lsl.imagelabelerapp.lsl.task;

import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.lsl.base.Base64Image;
import app.com.lsl.imagelabelerapp.lsl.utils.ServerUtil;

/** 注册业务
 * Created by M1308_000 on 2017/4/25.
 */

public class RegisterTask {

    private static final String TYPE = "register";

    /**
     * 封装注册信息
     * @param name
     * @param tel
     * @param Img_path
     * @param psw
     * @return
     */
    public static String Register(String name, String tel, String Img_path, String psw) {

        // 将图片的信息转化为base64编码
        String imgStr =  Base64Image.getImageStr(Img_path);
        String isRegSuccess = "Register_Fail";
        // 将信息封装到Map集合中
        Map<String, String> map = new HashMap<>();
        map.put("type", TYPE);
        map.put("register_name", name);
        map.put("register_tel", tel);
        map.put("register_email", "xxx@xxx.com");
        map.put("register_psw", psw);
        map.put("img", imgStr);

        isRegSuccess = ServerUtil.Server(map);

        return isRegSuccess;
    }
}
