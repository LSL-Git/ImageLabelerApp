package app.com.lsl.imagelabelerapp.lsl.task;

import java.util.HashMap;
import java.util.Map;

/** 登录业务
 * Created by M1308_000 on 2017/4/25.
 */

public class LoginTask {

    private static String TYPE = "login";

    public static Map<String,String> Login(String name, String psw) {

        Map<String,String> map = new HashMap<>();
        map.put("login_name",name);
        map.put("login_psw", psw);

        return map;
    }

    public static String getTYPE() {
        return TYPE;
    }

}
