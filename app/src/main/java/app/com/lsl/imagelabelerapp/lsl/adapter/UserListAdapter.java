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
import app.com.lsl.imagelabelerapp.lsl.model.UserList;

/**
 * Created by M1308_000 on 2017/6/11.
 */

public class UserListAdapter extends BaseAdapter {

    private List<UserList> mList;
    private Context context;
    private ImageView userIcon;
    private TextView userName;

    public UserListAdapter(Context context, List<UserList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
            // 获取布局加载器对象
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            // 从XML布局加载view对象
            convertView = layoutInflater.inflate(R.layout.user_name_list_item, null);
            // 从布局加载控件
            userIcon = (ImageView) convertView.findViewById(R.id.iv_user_list_item_icon);
            userName = (TextView) convertView.findViewById(R.id.tv_user_list_item_name);

            viewHolder = new ViewHolder();
            viewHolder.userIcon = userIcon;
            viewHolder.userName = userName;
            // 设置标记
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 设置显示内容
        UserList userList = mList.get(position);
        viewHolder.userIcon.setImageResource(userList.getUserIcon());
        viewHolder.userName.setText(userList.getUserName());
        viewHolder.userIcon.setOnClickListener(listener);
        return convertView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

//            Toast.makeText(context, "click image" + userName.getText().toString(),Toast.LENGTH_SHORT).show();
        }
    };

    class ViewHolder {
        ImageView userIcon;
        TextView userName;
    }
}
