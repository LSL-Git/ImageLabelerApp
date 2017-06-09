package app.com.lsl.imagelabelerapp.lsl.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by M1308_000 on 2017/4/29.
 */

public class StrUtils {


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
