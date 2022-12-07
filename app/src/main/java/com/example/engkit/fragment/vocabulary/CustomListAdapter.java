<<<<<<< HEAD
package com.example.engkit.fragment.vocabulary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.engkit.R;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private List<Vocabulary> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public CustomListAdapter(Context context, List<Vocabulary> listData) {
        this.listData = listData;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.vocabulary_list_item_layout, null);
            holder = new ViewHolder();
            holder.vocabularyName = (TextView) convertView.findViewById(R.id.vocabularyName);
            holder.vocabularyMeaning = (TextView) convertView.findViewById(R.id.vocabularyMeaning);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Vocabulary vocabulary = this.listData.get(position);
        holder.vocabularyName.setText(vocabulary.getVocabularyName());
        holder.vocabularyMeaning.setText(vocabulary.getVocabularyMeaning());

        return convertView;
    }

    static class ViewHolder {
        TextView vocabularyName;
        TextView vocabularyMeaning;
    }
}
=======
package com.example.engkit.fragment.vocabulary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.engkit.R;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private List<Vocabulary> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public CustomListAdapter(Context context, List<Vocabulary> listData) {
        this.listData = listData;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.vocabulary_list_item_layout, null);
            holder = new ViewHolder();
            holder.vocabularyName = (TextView) convertView.findViewById(R.id.vocabularyName);
            holder.vocabularyMeaning = (TextView) convertView.findViewById(R.id.vocabularyMeaning);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Vocabulary vocabulary = this.listData.get(position);
        holder.vocabularyName.setText(vocabulary.getVocabularyName());
        holder.vocabularyMeaning.setText(vocabulary.getVocabularyMeaning());

        return convertView;
    }

    static class ViewHolder {
        TextView vocabularyName;
        TextView vocabularyMeaning;
    }
}
>>>>>>> hoang_dev
