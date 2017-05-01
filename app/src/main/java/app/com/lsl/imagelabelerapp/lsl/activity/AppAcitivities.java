package app.com.lsl.imagelabelerapp.lsl.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/** 用于管理activity
 * Created by M1308_000 on 2017/4/30.
 */

public class AppAcitivities {
//    public static HashMap<String, Activity> activities = new HashMap<>();
    public static List<Activity> activities = new ArrayList<>();
    // 将一个活动添加到缓存
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    // 移除一个活动
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    // 移除所有活动
    public static void FinishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
