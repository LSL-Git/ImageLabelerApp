package app.com.lsl.imagelabelerapp.lsl.task;

import java.util.HashMap;
import java.util.Map;

/** 注册业务
 * Created by M1308_000 on 2017/4/25.
 */

public class RegisterTask {

    private static String TYPE = "register";

    /**
     * 封装注册信息
     * @param name
     * @param tel
     * @param psw
     * @return
     */
    public static Map<String,String> Register(String name, String tel, String psw) {

//        // 将图片的信息转化为base64编码 然后存入map集合中
//        String imgStr =  Base64Image.getImageStr(Img_path);

        // 将信息封装到Map集合中
        Map<String, String> map = new HashMap<>();
        map.put("register_name", name);
        map.put("register_tel", tel);
        map.put("register_psw", psw);

        return map;
    }

    public static String getType() {
        return TYPE;
    }
}
