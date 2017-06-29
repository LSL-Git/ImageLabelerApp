package app.com.lsl.imagelabelerapp.lsl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.text.ParseException;
import java.util.List;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.model.HistoryList;
import app.com.lsl.imagelabelerapp.lsl.utils.DateUtils;
import okhttp3.Call;

/**
 * Created by M1308_000 on 2017/6/27.
 */

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private List<HistoryList> historyLists;
    private ImageView iv_pic;
    private TextView tv_labels;
    private TextView tv_time;
    private TextView tv_state;

    public HistoryAdapter(Context context, List<HistoryList> historyLists) {
        this.context = context;
        this.historyLists = historyLists;
    }

    @Override
    public int getCount() {
        return historyLists == null ? 0 : historyLists.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.history_list_item, null);

            iv_pic = (ImageView) convertView.findViewById(R.id.iv_history);
            tv_labels = (TextView) convertView.findViewById(R.id.tv_history_labels);
            tv_time = (TextView) convertView.findViewById(R.id.tv_history_label_time);
            tv_state = (TextView) convertView.findViewById(R.id.tv_history_label_state);

            viewHolder = new ViewHolder();
            viewHolder.iv_Pic = iv_pic;
            viewHolder.tv_Labels = tv_labels;
            viewHolder.tv_Time = tv_time;
            viewHolder.tv_State = tv_state;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HistoryList list = historyLists.get(position);

        // 根据图片URL  加载显示整张大图片
        OkHttpUtils.get().url(list.getIconId()).tag(context).build().connTimeOut(20000)
                .readTimeOut(20000).writeTimeOut(20000).execute(new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(Bitmap bitmap, int id) {
                viewHolder.iv_Pic.setImageBitmap(bitmap);
            }
        });
        viewHolder.tv_Labels.setText("标签：" + list.getPicLabels());
        try {
            if (DateUtils.IsToday(list.getLabelTime())) {
                viewHolder.tv_Time.setText("今天：" + list.getLabelTime());
                viewHolder.tv_Time.setTextColor(Color.parseColor("#25B813"));
            } else {
                viewHolder.tv_Time.setText("提交时间：" + list.getLabelTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.tv_State.setText("状态：" + list.getLabelState());
        if (list.getLabelState().equals("已处理"))
            viewHolder.tv_State.setTextColor(Color.parseColor("#13BD34"));

        return convertView;
    }

    private class ViewHolder {
        ImageView iv_Pic;
        TextView tv_Labels;
        TextView tv_Time;
        TextView tv_State;
    }
}
