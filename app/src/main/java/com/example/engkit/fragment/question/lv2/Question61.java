package com.example.engkit.fragment.question.lv2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.constraintlayout.helper.widget.Flow;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.engkit.R;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question61#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question61 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Flow wordSet;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Question61() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question61.
     */
    // TODO: Rename and change types and number of parameters
    public static Question61 newInstance(String param1, String param2) {
        Question61 fragment = new Question61();
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

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_third_game, container, false);
        wordSet = rootView.findViewById(R.id.word_set);
        Button button = new Button(this.getContext());
        Button button1 = new Button((this.getContext()));
        button.setText("vcl");
        button1.setText("thaajt");
        button.setId(123);
        button1.setId(234);
        int[] ids = {123, 234};
        wordSet.setReferencedIds(ids);
        return rootView;
    }
}