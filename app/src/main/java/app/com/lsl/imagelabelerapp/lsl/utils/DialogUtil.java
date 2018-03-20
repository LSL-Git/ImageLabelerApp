package app.com.lsl.imagelabelerapp.lsl.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import app.com.lsl.imagelabelerapp.R;

/**
 * Created by M1308_000 on 2017/6/29.
 */

public class DialogUtil {

    private static ProgressDialog processDia;
//    private Context context = null;

    /**
     * 显示加载中对话框
     *
     * @param context
     */
    public static void showLoadingDialog(Context context, String message, boolean isCancelable) {
        if (processDia == null && isValidContext(context)) {
            Log.e("DialogUtil", isValidContext(context) + "");
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
    public static void closeLoadingDialog(Context context) {
        if (processDia != null) {
            Log.e("DialogUtil", isValidContext(context) + "2");
            if (processDia.isShowing() && isValidContext(context)) {
//                processDia.cancel();
                processDia.dismiss();
            }
            processDia = null;
        }
    }

    private static boolean isValidContext(Context c) {
        Activity a = (Activity)c;

        if (a.isDestroyed() || a.isFinishing()){
            Log.e("YXH", "Activity is invalid." + " isDestoryed-->" + a.isDestroyed() + " isFinishing-->" + a.isFinishing());
            return false;
        }else{
            return true;
        }
    }

}
