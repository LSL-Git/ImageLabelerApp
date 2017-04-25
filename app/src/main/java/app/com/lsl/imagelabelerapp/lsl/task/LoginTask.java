package app.com.lsl.imagelabelerapp.lsl.task;

import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.lsl.utils.ServerUtil;

/** 登录业务
 * Created by M1308_000 on 2017/4/25.
 */

public class LoginTask {

    private static String TYPE = "login";

    public static String Login(String name, String psw) {
        String Result = "Login_Fail";

        Map<String,String> map = new HashMap<>();
        map.put("type",TYPE);
        map.put("login_name",name);
        map.put("login_psw", psw);

        Result = ServerUtil.Server(map);

        return Result;
    }
}
