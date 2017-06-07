package app.com.lsl.imagelabelerapp.lsl.dbtable;

/** 已完成标签化图片的存放目录信息
 * Created by M1308_000 on 2017/6/7.
 */

public class PicFileTable {
    /**
     * 目录级
     */
    private int Level;
    /**
     * 父目录名称
     */
    private String parentName;
    /**
     * 本目录名称
     */
    private String fileName;
    /**
     * 本目录下的子文件个数
     */
    private int Num;

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }
}
