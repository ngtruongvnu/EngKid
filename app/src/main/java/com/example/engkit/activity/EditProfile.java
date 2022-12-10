package com.example.engkit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.engkit.R;
import com.example.engkit.database.EngkitHelperDB;

import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    private CircleImageView profileImage;
    private Button saveBtn;
    private EditText editProfileName, editProfileDate, editProfileAddress, editProfileEmail;
    private TextView editProfileNameError, editProfileDateError, editProfileAddressError, editProfileEmailError;
    private TextView changeProfileImageBtn;

    private EngkitHelperDB engkitHelperDB;

    private URL imageURL;
    private String myURL = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        createVariable();
        setOnClickSaveBtn();
        setOnClickChangeProfileImgBtn();
    }

    private void createVariable() {
        engkitHelperDB = new EngkitHelperDB(this,"EngkitDB.sqlite",null,1);
        profileImage = findViewById(R.id.profileImage);
        saveBtn = findViewById(R.id.saveBtn);
        editProfileName = findViewById(R.id.editProfileName);
        editProfileDate = findViewById(R.id.editProfileDate);
        editProfileAddress = findViewById(R.id.editProfileAddress);
        editProfileEmail = findViewById(R.id.editProfileEmail);
        editProfileNameError = findViewById(R.id.editProfileNameError);
        editProfileDateError = findViewById(R.id.editProfileDateError);
        editProfileAddressError = findViewById(R.id.editProfileAddressError);
        editProfileEmailError = findViewById(R.id.editProfileEmailError);
        changeProfileImageBtn = findViewById(R.id.changeProfileImageBtn);
    }

    private void setOnClickSaveBtn() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editProfileName.getText().toString();
//              check date
                String userDate = editProfileDate.getText().toString();
                String userAddress = editProfileAddress.getText().toString();
//              check email
                String userEmail = editProfileEmail.getText().toString();
//                engkitHelperDB.queryData("UPDATE Account as ac SET ac.name = '" + userName + "' ac.date = '" + userDate + "' ac.address = '" + userAddress + "'ac.email = '" + userEmail + "'");
//                Intent intent = new Intent(EditProfile.this, MenuManager.class);
//                intent.putExtra("OPEN_FRAGMENT", "information");
//                startActivity(intent);
                finish();
            }
        });
    }

    private void setOnClickChangeProfileImgBtn() {
        changeProfileImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
