package app.com.lsl.imagelabelerapp.lsl.dbtable;

import org.litepal.crud.DataSupport;

/** 所有用户的用户名
 * Created by M1308_000 on 2017/6/11.
 */

public class AllUserNameTb extends DataSupport {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
