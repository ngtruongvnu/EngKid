package com.example.engkit.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.engkit.R;
import com.example.engkit.database.RankUser;

import java.util.List;

public class RankAdapter extends BaseAdapter {
    private List<RankUser> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public RankAdapter(Context context, List<RankUser> listData) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return this.listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.listData.get(i).rank;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        RankUser user = this.listData.get(position);
        layoutInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.rank_item, null);
            holder.fullname = (TextView) convertView.findViewById(R.id.fullName);
            holder.avatar = (ImageView) convertView.findViewById(R.id.rankItemAvatar);
            holder.exp = (TextView) convertView.findViewById(R.id.expRank);
            holder.rankUser = convertView.findViewById(R.id.rankUser);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(convertView).load(user.imageUrl).into(holder.avatar);
        holder.fullname.setText(user.fullname);
        holder.exp.setText(user.score + " KN");
        holder.rankUser.setText(user.rank + "");

        return convertView;
    }


    static class ViewHolder {
        TextView rankUser;
        ImageView avatar;
        TextView fullname;
        TextView exp;
    }
}


