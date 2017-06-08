package app.com.lsl.imagelabelerapp.lsl.dbtable;

import org.litepal.crud.DataSupport;

/** 保存图片种类和该种类的数量
 * Created by M1308_000 on 2017/6/8.
 */

public class PicTypeAndNumTb extends DataSupport{
    /**
     * 图片种类
     */
    private String PicType;
    /**
     * 该种类图片数量
     */
    private int PicNum;

    public String getPicType() {
        return PicType;
    }

    public void setPicType(String picType) {
        PicType = picType;
    }

    public int getPicNum() {
        return PicNum;
    }

    public void setPicNum(int picNum) {
        PicNum = picNum;
    }
}
