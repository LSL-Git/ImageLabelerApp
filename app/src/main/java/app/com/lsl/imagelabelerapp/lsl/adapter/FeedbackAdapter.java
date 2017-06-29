package app.com.lsl.imagelabelerapp.lsl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.model.FeedbackList;

/**
 * Created by M1308_000 on 2017/6/26.
 */

public class FeedbackAdapter extends BaseAdapter {

    private List<FeedbackList> fblist;
    private Context context;
    private TextView tv_cm_user;
    private TextView tv_cm_time;
    private TextView tv_content;
    private TextView tv_state;

    public FeedbackAdapter(List<FeedbackList> fblist, Context context) {
        this.fblist = fblist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return fblist == null ? 0 : fblist.size();
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
            convertView = inflater.inflate(R.layout.feedback_list_item, null);

            tv_state = (TextView) convertView.findViewById(R.id.tv_fb_state);
            tv_cm_user = (TextView) convertView.findViewById(R.id.tv_fb_user);
            tv_cm_time = (TextView) convertView.findViewById(R.id.tv_fb_commit_time);
            tv_content = (TextView) convertView.findViewById(R.id.tv_fb_content);

            viewHolder = new ViewHolder();
            viewHolder.tv_state = tv_state;
            viewHolder.tv_cm_user = tv_cm_user;
            viewHolder.tv_cm_time = tv_cm_time;
            viewHolder.tv_content = tv_content;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FeedbackList feedbackList = fblist.get(position);

        viewHolder.tv_state.setText(feedbackList.getState());
        if (feedbackList.getState().equals("已处理")) {
            viewHolder.tv_state.setTextColor(Color.parseColor("#13BD34"));
        } else {
            viewHolder.tv_state.setTextColor(Color.parseColor("#FF0000"));
        }
        viewHolder.tv_cm_user.setText(feedbackList.getCommitUser());
        viewHolder.tv_cm_time.setText(feedbackList.getCommitTime());
        viewHolder.tv_content.setText(feedbackList.getFbContent());

        return convertView;
    }

    class ViewHolder {
        TextView tv_cm_user;
        TextView tv_cm_time;
        TextView tv_content;
        TextView tv_state;
    }
}
