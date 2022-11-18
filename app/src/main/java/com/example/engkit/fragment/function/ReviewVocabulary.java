package com.example.engkit.fragment.function;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReviewVocabulary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Review.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewVocabulary newInstance(String param1, String param2) {
        ReviewVocabulary fragment = new ReviewVocabulary();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listVocabulary);

        listView.setAdapter(new CustomListAdapter(view.getContext(), getData()));

        return view;
    }

    private List<Vocabulary> getData() {
        List<Vocabulary> listData = new ArrayList<Vocabulary>();

        listData.add(new Vocabulary("horse", "con ngựa"));
        listData.add(new Vocabulary("announce", "thông báo"));

        return listData;
    }
}