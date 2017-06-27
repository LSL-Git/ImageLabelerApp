package app.com.lsl.imagelabelerapp.lsl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.model.HistoryList;

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
        ViewHolder viewHolder;
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
        viewHolder.iv_Pic.setImageResource(R.mipmap.icon);
        viewHolder.tv_Labels.setText(list.getPicLabels());
        viewHolder.tv_Time.setText(list.getLabelTime());
        viewHolder.tv_State.setText(list.getLabelState());

        return convertView;
    }

    private class ViewHolder {
        ImageView iv_Pic;
        TextView tv_Labels;
        TextView tv_Time;
        TextView tv_State;
    }
}
