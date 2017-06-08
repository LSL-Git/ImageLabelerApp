package app.com.lsl.imagelabelerapp.lsl.App;

/** 保存当前用户，（全局变量）
 * Created by M1308_000 on 2017/6/8.
 */

public class User {

    private static String User;

    public static void setUser(String user) {
        User  = user;
    }

    public static String getUser() {
        return User;
    }
}
