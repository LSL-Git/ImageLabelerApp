package app.com.lsl.imagelabelerapp.lsl.dbtable;

import org.litepal.crud.DataSupport;

/** 图片URL信息表
 * Created by M1308_000 on 2017/5/17.
 */

public class ImageURLTable extends DataSupport{

    private int img_id;     // 图片id

    private String img_name;    // 图片名称

    private String img_url;     // 图片URL

    private String upload_time;    // 图片上传时间

    private int is_label;    // 是否已标签

    public int getIs_label() {
        return is_label;
    }

    public void setIs_label(int is_label) {
        this.is_label = is_label;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }
}
