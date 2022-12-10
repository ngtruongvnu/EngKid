package com.example.engkit.fragment.function;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.engkit.R;
import com.example.engkit.activity.EditProfile;
import com.example.engkit.activity.MenuManager;

public class Information extends Fragment {


    private TextView profileLanguage, profileScore, profileAddress, profileEmail;
    private TextView profileNumLesson, profileTotalLesson;
    private TextView profileDateDay, profileDateMonth, profileDateYear;

    private Button forgotPasswordBtn, editProfileBtn, logoutBtn;

    public Information() {

    }

//    public static Information newInstance(String param1, String param2) {
//        Information fragment = new Information();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        createVariable(view);
        setOnClickForgotPass();
        setOnClickEditProfile();
        setOnclickLogout();
        return view;
    }

    public void createVariable(View view) {
        profileLanguage = view.findViewById(R.id.profileLanguage);
        profileScore = view.findViewById(R.id.profileScore);
        profileAddress = view.findViewById(R.id.profileAddress);
        profileEmail = view.findViewById(R.id.profileEmail);
        profileNumLesson = view.findViewById(R.id.profileNumLesson);
        profileTotalLesson = view.findViewById(R.id.profileTotalLesson);
        profileDateDay = view.findViewById(R.id.profileDateDay);
        profileDateMonth = view.findViewById(R.id.profileDateMonth);
        profileDateYear = view.findViewById(R.id.profileDateYear);
        forgotPasswordBtn = view.findViewById(R.id.forgotPasswordBtn);
        editProfileBtn = view.findViewById(R.id.editProfileBtn);
        logoutBtn = view.findViewById(R.id.logoutBtn);
    }

    private void setOnClickForgotPass() {
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setOnClickEditProfile() {
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });
    }

    private void setOnclickLogout() {
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), EditProfile.class);
            }
        });
    }
}