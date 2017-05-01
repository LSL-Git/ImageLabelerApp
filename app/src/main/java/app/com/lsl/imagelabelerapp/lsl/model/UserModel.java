package app.com.lsl.imagelabelerapp.lsl.model;

import java.util.Map;

/** model接口 用于加载数据
 * Created by M1308_000 on 2017/4/30.
 */

public interface UserModel {

    /**
     * 加载数据
     * @param listener
     */
    void loadData(Map<String,String> map, String Type, DataLoadOnListener listener);

    /**
     * 监听加载情况，并回调结果
     */
    interface DataLoadOnListener {
        void CallBack(Object obj);
    }
}
