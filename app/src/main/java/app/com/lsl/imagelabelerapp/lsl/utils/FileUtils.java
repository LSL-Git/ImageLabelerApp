package app.com.lsl.imagelabelerapp.lsl.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/** 文件操作类
 * Created by M1308_000 on 2017/5/19.
 */

public class FileUtils {

    /**
     * 在SD卡上创建一个文件夹
     */
    public static void CreateDirInSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象。赋值为外存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            // 得到一个路径，内容是SD卡的文件夹路径和名字
            String dir_path = sdcardDir.getPath() + "/LabelerApp";  // /mnt/sdcard/LabelerApp
            String icon_path = dir_path + "/icon";              // /mnt/sdcard/LabelerApp/icon

            File file = new File(dir_path);
            File file1 = new File(icon_path);

            if (!file.exists()) {
                // 若文件夹不存在则创建
                file.mkdirs();
                Log.e("MainActivity", "create dir1 success");
            } else if (!file1.exists()) {
                file1.mkdirs();
                Log.e("MainActivity", "create dir2 success");
            } else {
                Log.e("MainActivity", "create dir fail");
            }
        }
    }


}
