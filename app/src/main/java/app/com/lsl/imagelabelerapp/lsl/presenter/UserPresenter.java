package app.com.lsl.imagelabelerapp.lsl.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import app.com.lsl.imagelabelerapp.lsl.activity.view.UserView;
import app.com.lsl.imagelabelerapp.lsl.model.UserModel;
import app.com.lsl.imagelabelerapp.lsl.utils.HttpUtil;

/** 中间者
 * Created by M1308_000 on 2017/4/30.
 */

public class UserPresenter {
    String TYPE;
    Map<String, String> map = new HashMap<>();
    // view
    UserView userView;
    // model
    UserModel model = new HttpUtil();

    /**
     * 通过构造方法实例化 userView
     * @param userView
     */
    public UserPresenter(UserView userView, Map<String, String> map, String type) {
        super();
        Log.e("UserPresenter","UserPresenter...");
        this.userView = userView;
        this.TYPE = type;
        this.map = map;
    }

    /**
     * 绑定 view 和 model
     */
    public void fetch() {
        // 显示进度条
        userView.ShowLoading();
        // 让model加载数据
        if (model != null) {
            model.loadData(map, TYPE, new UserModel.DataLoadOnListener() {
                @Override
                public void CallBack(Object obj) {
                    userView.ShowBackMsg(obj);
                }
            });
        }
    }
}
