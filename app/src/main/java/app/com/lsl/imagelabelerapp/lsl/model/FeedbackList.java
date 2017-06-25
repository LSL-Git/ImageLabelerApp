package app.com.lsl.imagelabelerapp.lsl.model;

/**
 * Created by M1308_000 on 2017/6/26.
 */

public class FeedbackList {

    private String state;

    private String commitUser;

    private String commitTime;

    private String fbContent;

    public FeedbackList(String state, String commitUser, String commitTime, String fbContent) {
        this.state = state;
        this.commitUser = commitUser;
        this.commitTime = commitTime;
        this.fbContent = fbContent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCommitUser() {
        return commitUser;
    }

    public void setCommitUser(String commitUser) {
        this.commitUser = commitUser;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }
}
