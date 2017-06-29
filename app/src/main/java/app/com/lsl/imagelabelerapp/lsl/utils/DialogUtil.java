package app.com.lsl.imagelabelerapp.lsl.utils;

import android.app.ProgressDialog;
import android.content.Context;

import app.com.lsl.imagelabelerapp.R;

/**
 * Created by M1308_000 on 2017/6/29.
 */

public class DialogUtil {

    private static ProgressDialog processDia;

    /**
     * 显示加载中对话框
     *
     * @param context
     */
    public static void showLoadingDialog(Context context, String message, boolean isCancelable) {
        if (processDia == null) {
            processDia= new ProgressDialog(context, R.style.MyDialogStyle);
            //点击提示框外面是否取消提示框
            processDia.setCanceledOnTouchOutside(false);
            //点击返回键是否取消提示框
            processDia.setCancelable(isCancelable);
            processDia.setIndeterminate(true);
            processDia.setMessage(message);
            processDia.show();
        }
    }

    /**
     * 关闭加载对话框
     */
    public static void closeLoadingDialog() {
        if (processDia != null) {
            if (processDia.isShowing()) {
                processDia.cancel();
            }
            processDia = null;
        }
    }
}
