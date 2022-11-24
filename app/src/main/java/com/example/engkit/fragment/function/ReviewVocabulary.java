package com.example.engkit.fragment.function;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.engkit.R;
import com.example.engkit.fragment.vocabulary.CustomListAdapter;
import com.example.engkit.fragment.vocabulary.Vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewVocabulary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewVocabulary extends Fragment {

    public ReviewVocabulary() {
        // Required empty public constructor
    }

    public static ReviewVocabulary newInstance(String param1, String param2) {
        ReviewVocabulary fragment = new ReviewVocabulary();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listVocabulary);
        listView.setAdapter(new CustomListAdapter(view.getContext(), getData()));
        return view;
    }

    private List<Vocabulary> getData() {
        List<Vocabulary> listData = new ArrayList<Vocabulary>();

        listData.add(new Vocabulary("horse", "con ngựa"));
        listData.add(new Vocabulary("horse", "con ngựa"));
        listData.add(new Vocabulary("horse", "con ngựa"));
        listData.add(new Vocabulary("horse", "con ngựa"));
        listData.add(new Vocabulary("announce", "thông báo"));
        listData.add(new Vocabulary("announce", "thông báo"));
        listData.add(new Vocabulary("announce", "thông báo"));

        return listData;
    }
}