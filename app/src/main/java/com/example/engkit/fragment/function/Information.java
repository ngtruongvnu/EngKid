package com.example.engkit.fragment.function;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engkit.R;
import com.example.engkit.activity.EditProfile;
import com.example.engkit.activity.GetAccountBack;
import com.example.engkit.activity.Login;
import com.example.engkit.activity.MenuManager;
import com.example.engkit.database.EngkitHelperDB;

public class Information extends Fragment {

    private EngkitHelperDB engkitHelperDB;
    private TextView profileLanguage, profileScore, profileAddress, profileEmail, profileDate, profileName, joinDate;
    private TextView profileNumLesson, profileTotalLesson;

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
        getProfileData();
        setOnClickForgotPass();
        setOnClickEditProfile();
        setOnclickLogout();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getProfileData();
    }

    public void createVariable(View view) {
        engkitHelperDB = new EngkitHelperDB(getActivity(),"EngkitDB.sqlite",null,1);
        engkitHelperDB.queryData("CREATE TABLE IF NOT EXISTS Account (Id int PRIMARY KEY AUTOINCREMENT, Email varchar(30), Code varchar(10), Password varchar(30), Name varchar(30), Date date, Address varchar(30), lesson int)");
        engkitHelperDB.queryData("INSERT INTO Account VALUES (null, 'hoang@gmail.com', '2507', '123456', 'Lương Thế Hùng', '2002-01-24', 'Hà nội', 10)");
        profileLanguage = view.findViewById(R.id.profileLanguage);
        profileScore = view.findViewById(R.id.profileScore);
        profileAddress = view.findViewById(R.id.profileAddress);
        profileEmail = view.findViewById(R.id.profileEmail);
        profileNumLesson = view.findViewById(R.id.profileNumLesson);
        profileTotalLesson = view.findViewById(R.id.profileTotalLesson);
        profileDate = view.findViewById(R.id.profileDate);
        profileName = view.findViewById(R.id.profileName);
        joinDate = view.findViewById(R.id.joinDate);
        forgotPasswordBtn = view.findViewById(R.id.forgotPasswordBtn);
        editProfileBtn = view.findViewById(R.id.editProfileBtn);
        logoutBtn = view.findViewById(R.id.logoutBtn);
    }

    public void getProfileData() {
        try {
            Cursor profileData = engkitHelperDB.GetData("SELECT Name, Email, Date, Address, lesson FROM Account ac WHERE ac.Email = '" + getResources().getString(R.string.userEmail) + "'");
            if (profileData.moveToFirst()) {
                do {
                    profileName.setText(profileData.getString(0));
                    profileEmail.setText(profileData.getString(1));

                    profileDate.setText(convertDate(profileData.getString(2)));
                    profileAddress.setText(profileData.getString(3));
                    profileNumLesson.setText(profileData.getString(4));
                } while (profileData.moveToNext());
            } else {
                Toast.makeText(getActivity(), "data fetch failed", Toast.LENGTH_SHORT).show();
            }
            profileData.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "data fetch failed", Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
        }
    }

    private String convertDate(String date) {
        String[] dateArray = date.split("-");
        StringBuilder dateConvert = new StringBuilder(dateArray[dateArray.length - 1]);
        for (int i = dateArray.length - 2; i >= 0; i--) {
            dateConvert.append("/" + dateArray[i]);
        }
        return dateConvert.toString();
    }

    private void setOnClickForgotPass() {
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GetAccountBack.class);
                startActivity(intent);
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
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
    }
}