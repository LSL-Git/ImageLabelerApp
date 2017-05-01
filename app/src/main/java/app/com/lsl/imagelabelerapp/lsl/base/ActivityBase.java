package app.com.lsl.imagelabelerapp.lsl.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import app.com.lsl.imagelabelerapp.lsl.activity.AppAcitivities;

/**
 * Created by M1308_000 on 2017/4/30.
 */

public class ActivityBase extends Activity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 获取活动名称，并调用移除该活动
        AppAcitivities.removeActivity(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取活动名称，并调用将其加入缓存
        AppAcitivities.addActivity(this);
    }
}
