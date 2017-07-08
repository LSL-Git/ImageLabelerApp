package app.com.lsl.imagelabelerapp.lsl.dbtable;

import org.litepal.crud.DataSupport;

/**
 * Created by M1308_000 on 2017/7/9.
 */

public class BatchTb extends DataSupport {

    private int batch;

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
}
