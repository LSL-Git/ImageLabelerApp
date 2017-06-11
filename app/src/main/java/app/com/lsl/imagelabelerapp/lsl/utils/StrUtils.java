package app.com.lsl.imagelabelerapp.lsl.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 字符串工具类
 * Created by M1308_000 on 2017/4/29.
 */

public class StrUtils {

    public static final String TAG = "StrUtils";

    /**
     * 将所有标签拼接
     * @param urls
     * @return
     */
    public static String GetLabelsStr(String[] urls) {
        String picName = urls[urls.length - 1];
        String labels = "";
        int j = 1;
        for (int i = 7; i < urls.length - 2; i++,j++) {
            labels +=  "label" + j + ": " + urls[i] + "\n";
        }

        Map<String,String> map = DbUtils.GetPicLabels(picName, j);
        int no = Integer.parseInt(map.get("no"));
        int num = map.size() - 1;
        int noo = no - 1;
        for (int i = 0; i < num; i++) {
            labels += "label" + noo + ": " + map.get("label" + noo) + "\n";
            noo++;
        }
        Log.e(TAG, labels);
        return labels;
    }

    /**
     * 将图片名称，文件路径，和http URL 拼装成完整 URL
     * @param fileName
     * @param filePath
     * @return
     */
    public static ArrayList<String> GetPicUrl(String fileName, String filePath){
        String url = "http://114.115.141.43:4040/webServer/LoadImage/Images/已完成";
        ArrayList<String> list = new ArrayList<>();
        List<String> picNameList = DbUtils.GetPicName(fileName);
        for (int i = 0; i < picNameList.size(); i++) {
            list.add(url + filePath + "/" + picNameList.get(i));
        }
        return list;

    }

    /**
     * 解析输入流的信息
     * @param inputStream
     * @return
     */
    public static String readMyInputStream(InputStream inputStream) {
        byte[] result;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len;
            while ((len = inputStream.read(array)) != -1) {
                baos.write(array, 0, len);
            }
            inputStream.close();
            baos.close();
            result = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return "数据获取失败";
        }
        return new String(result);
    }

    /**
     * 把URL和请求信息组合起来
     * @param path
     * @param map
     * @param encode
     * @return
     */
    public static String GetRequestString(String path, Map<String, String> map, String encode) {

        StringBuilder Url = new StringBuilder(path);
        Url.append("?");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            Url.append(entry.getKey()).append("=");
            try {
                Url.append(URLEncoder.encode(entry.getValue(), encode));
                Url.append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(Url.deleteCharAt(Url.length() - 1));
    }
}
