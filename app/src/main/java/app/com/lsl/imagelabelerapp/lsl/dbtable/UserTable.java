package app.com.lsl.imagelabelerapp.lsl.dbtable;

import org.litepal.crud.DataSupport;

/** 用户信息表模型
 * Created by M1308_000 on 2017/4/17.
 */

public class UserTable extends DataSupport{

    private int user_id; // 用户id

    private String User_Name; // 用户昵称

    private String User_Psw;    // 用户密码

    private String User_tel;    // 用户电话号码

    private String User_mail;   // 用户e-mail

    private int User_Integral;  // 用户积分

    private String Icon_Id;     // 用户对应头像id

    private String Task_Completion; //用户任务完成情况

    private boolean Is_Manager;     // 是否管理员

    public boolean getIs_Manager() {
        return Is_Manager;
    }

    public void setIs_Manager(boolean is_Manager) {
        Is_Manager = is_Manager;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Psw() {
        return User_Psw;
    }

    public void setUser_Psw(String user_psw) {
        User_Psw = user_psw;
    }

    public String getUser_tel() {
        return User_tel;
    }

    public void setUser_tel(String user_tel) {
        User_tel = user_tel;
    }

    public String getUser_mail() {
        return User_mail;
    }

    public void setUser_mail(String user_mail) {
        User_mail = user_mail;
    }

    public int getUser_Integral() {
        return User_Integral;
    }

    public void setUser_Integral(int user_Integral) {
        User_Integral = user_Integral;
    }

    public String getIcon_Id() {
        return Icon_Id;
    }

    public void setIcon_Id(String icon_Id) {
        Icon_Id = icon_Id;
    }

    public String getTask_Completion() {
        return Task_Completion;
    }

    public void setTask_Completion(String task_Completion) {
        Task_Completion = task_Completion;
    }
}
