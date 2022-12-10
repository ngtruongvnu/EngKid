package com.example.engkit.fragment.function;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.engkit.R;
import com.example.engkit.adapter.VocabularyAdapter;
import com.example.engkit.database.EngkitHelperDB;
import com.example.engkit.entities.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class ReviewVocabulary extends Fragment {

    private EngkitHelperDB engkitHelperDB;
    public ReviewVocabulary() {
        // Required empty public constructor
    }

    public static ReviewVocabulary newInstance(String param1, String param2) {
        ReviewVocabulary fragment = new ReviewVocabulary();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listVocabulary);
        engkitHelperDB = new EngkitHelperDB(view.getContext(), "EngkitDB.sqlite", null, 1);
        listView.setAdapter(new VocabularyAdapter(view.getContext(), getVocabularyList()));
        return view;
    }

    private List<Vocabulary> getVocabularyList() {
//        Cursor lesson =  engkitHelperDB.GetData("SELECT lesson FROM Account WHERE id = " + userID);
//        Cursor vocabularyData = engkitHelperDB.GetData("SELECT * FROM Vocabulary WHERE lesson == " + lesson);
        List<Vocabulary> vocabularyList = new ArrayList<>();

        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("abc", "def"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("abc", "def"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("abc", "def"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("abc", "def"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("abc", "def"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("horse", "con ngựa"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("announce", "thông báo"));
        vocabularyList.add(new Vocabulary("abc", "def"));

        return vocabularyList;
    }
}