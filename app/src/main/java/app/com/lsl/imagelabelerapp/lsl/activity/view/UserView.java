package app.com.lsl.imagelabelerapp.lsl.activity.view;

/** view 接口，用户显示结果
 * Created by M1308_000 on 2017/4/30.
 */

public interface UserView {

    /**
     * 显示加载进度条
     */
    void ShowLoading();

    /**
     * 用于显示回调信息
     * @param obj
     */
    void ShowBackMsg(Object obj);
}
