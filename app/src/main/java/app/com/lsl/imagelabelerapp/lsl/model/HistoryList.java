package app.com.lsl.imagelabelerapp.lsl.model;

/**
 * Created by M1308_000 on 2017/6/27.
 */

public class HistoryList {

    private String iconId;

    private String picLabels;

    private String labelTime;

    private String labelState;

    public HistoryList(String iconId, String picLabels, String labelTime, String labelState) {
        this.iconId = iconId;
        this.picLabels = picLabels;
        this.labelTime = labelTime;
        this.labelState = labelState;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public String getPicLabels() {
        return picLabels;
    }

    public void setPicLabels(String picLabels) {
        this.picLabels = picLabels;
    }

    public String getLabelTime() {
        return labelTime;
    }

    public void setLabelTime(String labelTime) {
        this.labelTime = labelTime;
    }

    public String getLabelState() {
        return labelState;
    }

    public void setLabelState(String labelState) {
        this.labelState = labelState;
    }
}
