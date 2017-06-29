package app.com.lsl.imagelabelerapp.lsl.dbtable;

import org.litepal.crud.DataSupport;

/**
 * Created by M1308_000 on 2017/6/29.
 */

public class LabelsTb extends DataSupport {

    private String labels;

    private String commitTime;

    private String labelState;

    private String picName;

    private String picUrl;

    private String User;

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getLabelState() {
        return labelState;
    }

    public void setLabelState(String labelState) {
        this.labelState = labelState;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

}
