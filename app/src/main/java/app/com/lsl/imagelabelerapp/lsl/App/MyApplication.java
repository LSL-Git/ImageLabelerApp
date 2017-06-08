package app.com.lsl.imagelabelerapp.lsl.App;

import org.litepal.LitePalApplication;

import app.com.lsl.imagelabelerapp.lsl.activity.AppActivities;

/** 编写自己的Application，管理全局状态信息，如Context 
 * Created by M1308_000 on 2017/4/30.
 */

public class MyApplication extends LitePalApplication {

    // 退出程序
    public static void quitApp() {
        AppActivities.FinishAll();
    }
}
