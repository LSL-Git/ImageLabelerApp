package app.com.lsl.imagelabelerapp.lsl.config;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 获取app 的配置信息
 * Created by LSL on 2018/3/10.
 */

public class ProperTies {

    public static Properties getProperties (Context context) {
        Properties properties;
        Properties props = new Properties();
        try {
            //方法一：通过activity中的context攻取setting.properties的FileInputStream
            InputStream in = context.getAssets().open("appConfig");
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties = props;
        return properties;
    }
}
