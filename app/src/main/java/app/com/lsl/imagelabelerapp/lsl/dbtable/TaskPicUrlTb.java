package app.com.lsl.imagelabelerapp.lsl.dbtable;

import org.litepal.crud.DataSupport;

/**
 * Created by M1308_000 on 2017/6/21.
 */

public class TaskPicUrlTb extends DataSupport {
    /**
     * 图片名
     */
    private String picName;
    /**
     * 图片URL
     */
    private String picUrl;
    /**
     * 所在批次
     */
    private int batch;
    /**
     * 图片状态
     */
    private String State;
    /**
     * 领取任务时间
     */
    private String getTaskTime;

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getGetTaskTime() {
        return getTaskTime;
    }

    public void setGetTaskTime(String getTaskTime) {
        this.getTaskTime = getTaskTime;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
}
