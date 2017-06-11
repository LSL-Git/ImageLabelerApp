package app.com.lsl.imagelabelerapp.lsl.model;

/**
 * Created by M1308_000 on 2017/6/11.
 */

public class UserList {

    private int userIcon;

    private String userName;

    public int getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(int userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserList(int userIcon, String userName) {
        this.userIcon = userIcon;
        this.userName = userName;
    }
}
