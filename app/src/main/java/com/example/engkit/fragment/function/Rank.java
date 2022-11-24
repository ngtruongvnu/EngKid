package com.example.engkit.fragment.function;

import android.app.LauncherActivity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.engkit.R;
import com.example.engkit.adapter.RankAdapter;
import com.example.engkit.database.RankUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Rank#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Rank extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;

    private List<RankUser> data;

    public Rank() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Rank.
     */
    // TODO: Rename and change types and number of parameters
    public static Rank newInstance(String param1, String param2) {
        Rank fragment = new Rank();
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
        data = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            data.add(new RankUser("Vu Van Tuan", 70, i, "https://i.imgur.com/bIRGzVO.jpg"));
        }
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        listView = view.findViewById(R.id.listUser);
        RankAdapter adapter = new RankAdapter(view.getContext(), data);
        listView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }

}