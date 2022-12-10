package com.example.engkit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engkit.R;
import com.example.engkit.database.EngkitHelperDB;

import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    private CircleImageView profileImage;
    private Button saveBtn, cancelBtn;
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
        setOnClickCancelBtn();
        removeErrorMesssage();
    }

    private void createVariable() {
        engkitHelperDB = new EngkitHelperDB(this,"EngkitDB.sqlite",null,1);
        profileImage = findViewById(R.id.profileImage);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
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
                boolean isSave = true;
                String userName = editProfileName.getText().toString();
                String userDate = editProfileDate.getText().toString();
                String userAddress = editProfileAddress.getText().toString();
                String userEmail = editProfileEmail.getText().toString();

                if (validateRequire()) {
                    isSave = false;
                }

                editProfileEmailError.setVisibility(View.VISIBLE);
                editProfileNameError.setVisibility(View.VISIBLE);
                editProfileAddressError.setVisibility(View.VISIBLE);
                editProfileDateError.setVisibility(View.VISIBLE);

//                engkitHelperDB.queryData("UPDATE Account as ac SET ac.name = '" + userName + "' ac.date = '" + userDate + "' ac.address = '" + userAddress + "'ac.email = '" + userEmail + "'");
//                Intent intent = new Intent(EditProfile.this, MenuManager.class);
//                intent.putExtra("OPEN_FRAGMENT", "information");
//                startActivity(intent);
                Toast.makeText(EditProfile.this, "Sửa hồ sơ thành công", Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }

    private void setOnClickCancelBtn() {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void removeErrorMesssage() {

        editProfileName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editProfileNameError.setVisibility(View.INVISIBLE);
            }
        });

        editProfileDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editProfileDateError.setVisibility(View.INVISIBLE);
            }
        });

        editProfileAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editProfileAddressError.setVisibility(View.INVISIBLE);
            }
        });

        editProfileEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editProfileEmailError.setVisibility(View.INVISIBLE);
            }
        });
    }

    private boolean validateRequire() {
        boolean error = false;
        if (editProfileName.getText().toString().trim() == "") {
            editProfileNameError.setVisibility(View.VISIBLE);
            editProfileNameError.setText(R.string.edit_text_require);
            error = true;
        }

        if (editProfileDate.getText().toString().trim() == "") {
            editProfileDateError.setVisibility(View.VISIBLE);
            editProfileDateError.setText(R.string.edit_text_require);
            error = true;
        }

        if (editProfileAddress.getText().toString().trim() == "") {
            editProfileAddressError.setVisibility(View.VISIBLE);
            editProfileAddressError.setText(R.string.edit_text_require);
            error = true;
        }

        if (editProfileName.getText().toString().trim() == "") {
            editProfileEmailError.setVisibility(View.VISIBLE);
            editProfileEmailError.setText(R.string.edit_text_require);
            error = true;
        }
    return error;
    }
}
