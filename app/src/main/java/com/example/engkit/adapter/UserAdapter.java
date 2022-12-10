package com.example.engkit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.engkit.R;
import com.example.engkit.database.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<User> userList;
    private TextView textView;

    public UserAdapter(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> UserList) {
        this.userList = UserList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvEmail;
        TextView tvCode;
        TextView tvId;
        TextView tvPassword;
        TextView tvName;
        TextView tvDate;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvEmail = (TextView) view.findViewById(R.id.email_user);
            holder.tvCode = (TextView) view.findViewById(R.id.code_user);
            holder.tvId = (TextView) view.findViewById(R.id.id_user);
            holder.tvPassword = (TextView) view.findViewById(R.id.pw_user);
            holder.tvName = (TextView) view.findViewById(R.id.name_user);
            holder.tvDate = (TextView) view.findViewById(R.id.dob_user);
            view.setTag(holder);
        } else{
            holder = (ViewHolder) view.getTag();
        }

        User User = userList.get(i);
        holder.tvEmail.setText(User.getEmail());
        holder.tvCode.setText(User.getCode());
        holder.tvId.setText(String.valueOf(User.getId()));
        holder.tvPassword.setText(User.getPassword());
        holder.tvName.setText(User.getName());
        holder.tvDate.setText(User.getDateOfBirth().toString());
        return view;
    }

}
