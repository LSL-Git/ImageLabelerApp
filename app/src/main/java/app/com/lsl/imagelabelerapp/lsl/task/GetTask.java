package app.com.lsl.imagelabelerapp.lsl.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.lsl.App.User;
import app.com.lsl.imagelabelerapp.lsl.utils.DbUtils;
import app.com.lsl.imagelabelerapp.lsl.utils.HttpUtils;

import static app.com.lsl.imagelabelerapp.lsl.utils.DbUtils.GetBatch;

/**
 * Created by M1308_000 on 2017/6/21.
 */

public class GetTask {

    private static ArrayList<String> list;

    /**
     * 从服务器获取今日任务图片信息
     */
    public static void getMyTask() {
        // 获取图片URL列表
        String TYPE = "images";
        Map<String, String> map = new HashMap<>();
        map.put("type", "GetTaskPicUrl");
        map.put("userName",User.getUser());
        new Thread(new HttpUtils(map, TYPE)).start();
    }

    /**
     * 获取任务中未标签的图片URL
     * @return
     */
    public static ArrayList<String> getTaskPicUrl() {
        list = DbUtils.GetTaskPicUrl("NOP", User.getUser(), GetBatch());
        if (list.size() == 0) {
            getMyTask();
            do {
                list = DbUtils.GetTaskPicUrl("NOP", User.getUser(), GetBatch());
            } while (list.size() > 0);
        }
        return list;
    }
}
