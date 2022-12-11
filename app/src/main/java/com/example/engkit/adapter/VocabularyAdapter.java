package com.example.engkit.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.engkit.R;
import com.example.engkit.entities.Vocabulary;

import java.util.List;
import java.util.Locale;

public class VocabularyAdapter extends BaseAdapter {

    private List<Vocabulary> listVocabulary;
    private LayoutInflater layoutInflater;
    private Context context;
    private TextToSpeech tts;

    public VocabularyAdapter(Context context, List<Vocabulary> listData, TextToSpeech tts) {
        this.listVocabulary = listData;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.tts = tts;
    }

    @Override
    public int getCount() {
        return listVocabulary.size();
    }

    @Override
    public Object getItem(int i) {
        return listVocabulary.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.vocabulary_list_item_layout, null);
            holder = new ViewHolder();
            holder.vocabularyName = (TextView) convertView.findViewById(R.id.vocabularyName);
            holder.vocabularyMeaning = (TextView) convertView.findViewById(R.id.vocabularyMeaning);
            holder.pronunciationBtn = (ImageView) convertView.findViewById(R.id.pronunciationBtn);
            holder.detailBtn = (TextView) convertView.findViewById(R.id.detailBtn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Vocabulary vocabulary = this.listVocabulary.get(position);
        holder.vocabularyName.setText(vocabulary.getVocabularyName());
        holder.vocabularyMeaning.setText(vocabulary.getVocabularyMeaning());
        holder.setOnClickPronunciationBtn(vocabulary.getVocabularyName());
        holder.setOnClickDetailBtn();
        return convertView;
    }



    private class ViewHolder {
        TextView vocabularyName;
        TextView vocabularyMeaning;
        ImageView pronunciationBtn;
        TextView detailBtn;

        public void setOnClickPronunciationBtn(final String text) {
            pronunciationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(text == null||"".equals(text))
                    {
                        tts.speak("Content not available", TextToSpeech.QUEUE_FLUSH, null,"available");
                    } else
                        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, text);
                }
            });
        }

        public void setOnClickDetailBtn() {
            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }

    }
}