package app.com.lsl.imagelabelerapp.lsl.App;

import android.app.Application;
import android.content.Context;

import app.com.lsl.imagelabelerapp.lsl.activity.AppAcitivities;

/** 编写自己的Application，管理全局状态信息，如Context 
 * Created by M1308_000 on 2017/4/30.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取context
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    // 退出程序
    public static void quitApp() {
        AppAcitivities.FinishAll();
    }
}
