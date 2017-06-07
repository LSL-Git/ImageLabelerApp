package app.com.lsl.imagelabelerapp.lsl.dbtable;

/** 用户搜索图片历史
 * Created by M1308_000 on 2017/6/7.
 */

public class SearchHistoryTable {
    /**
     * 搜索用户名
     */
    private String UserName;
    /**
     * 搜索的内容
     */
    private String SearchContent;
    /**
     * 最新一次搜索时间
     */
    private String SearchTime;
    /**
     * 搜索次数
     */
    private int SearchNum;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSearchContent() {
        return SearchContent;
    }

    public void setSearchContent(String searchContent) {
        SearchContent = searchContent;
    }

    public String getSearchTime() {
        return SearchTime;
    }

    public void setSearchTime(String searchTime) {
        SearchTime = searchTime;
    }

    public int getSearchNum() {
        return SearchNum;
    }

    public void setSearchNum(int searchNum) {
        SearchNum = searchNum;
    }
}
