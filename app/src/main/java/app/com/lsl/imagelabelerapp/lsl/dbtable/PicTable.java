package app.com.lsl.imagelabelerapp.lsl.dbtable;

import org.litepal.crud.DataSupport;

/** 已完成标签化图片的相关信息
 * Created by M1308_000 on 2017/6/7.
 */

public class PicTable extends DataSupport {
    /**
     * 上级目录
     */
    private String parentFile;
    /**
     * 图片名称
     */
    private String picName;
    /**
     * 图片标签
     */
    private String PicLabel;
    /**
     * 完成标签化时间
     */
    private String FinishTime;

    public String getParentFile() {
        return parentFile;
    }

    public void setParentFile(String parentFile) {
        this.parentFile = parentFile;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicLabel() {
        return PicLabel;
    }

    public void setPicLabel(String picLabel) {
        PicLabel = picLabel;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }
}
