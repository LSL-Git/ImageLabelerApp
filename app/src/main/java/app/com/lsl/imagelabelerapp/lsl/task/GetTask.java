package app.com.lsl.imagelabelerapp.lsl.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.lsl.utils.DbUtils;
import app.com.lsl.imagelabelerapp.lsl.utils.HttpUtils;

/**
 * Created by M1308_000 on 2017/6/21.
 */

public class GetTask {

    private static ArrayList<String> list;

    private static void getMyTask() {
        // 获取图片URL列表
        String TYPE = "images";
        Map<String, String> map = new HashMap<>();
        map.put("type", "GetTaskPicUrl");
        new Thread(new HttpUtils(map, TYPE)).start();
    }

    public static ArrayList<String> getTaskPicUrl() {
        list = DbUtils.GetTaskPicUrl("NOP");
        if (list.size() == 0) {
            getMyTask();
            do {
                list = DbUtils.GetTaskPicUrl("NOP");
            } while (list.size() > 0);
        }
        return list;
    }
}
