package app.com.lsl.imagelabelerapp.lsl.activity.menu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.lsl.imagelabelerapp.R;

/** 头部菜单栏控件
 * Created by M1308_000 on 2017/6/10.
 */

public class TopMenuHeader {

    // 顶部菜单左边返回图片
    public ImageView ivTopMenuLeft;

    // 顶部菜单左边按钮
    public TextView topMenuLeft;

    // 顶部菜单右边按钮
    public ImageView topMenuRight;

    // 顶部菜单文字
    public TextView topMenuTitle;

    public TopMenuHeader(View v) {

        ivTopMenuLeft = (ImageView) v.findViewById(R.id.iv_icon);

        // 右边按钮
        topMenuRight = (ImageView) v.findViewById(R.id.top_menu_right);

        // 左边按钮
        topMenuLeft = (TextView) v.findViewById(R.id.top_menu_left);

        // 顶部中间文字
        topMenuTitle = (TextView) v.findViewById(R.id.top_menu_title);

    }

}
