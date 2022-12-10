package com.example.engkit.fragment.function;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.engkit.R;
import com.example.engkit.adapter.RankAdapter;
import com.example.engkit.database.NetworkProvider;
import com.example.engkit.database.RankUser;
import com.example.engkit.database.RankUserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private RankUserService rankUserService;

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
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        listView = view.findViewById(R.id.listUser);

        data = new ArrayList<>();
        rankUserService = NetworkProvider.getClient().create(RankUserService.class);
        Call<RankUser[]> call = rankUserService.getAllUsers();
        call.enqueue(new Callback<RankUser[]>() {
            @Override
            public void onResponse(Call<RankUser[]> call, Response<RankUser[]> response) {
                RankUser[] res = response.body();
                for (int i = 0; i < res.length; i++) {
                    System.out.println("" + res[i].rank + res[i].imageUrl + res[i].fullname + res[i].score);
                    data.add(res[i]);
                }
                RankAdapter adapter = new RankAdapter(view.getContext(), data);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RankUser[]> call, Throwable t) {
                System.out.println("Fail");
                System.out.println(t.fillInStackTrace());
                call.cancel();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}