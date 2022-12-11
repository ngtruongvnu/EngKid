package com.example.engkit.activity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.engkit.R;
import com.example.engkit.database.EngkitHelperDB;

import java.net.URI;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;

    private CircleImageView profileImage;
    private Button saveBtn, cancelBtn;
    private EditText editProfileName, editProfileDate, editProfileAddress, editProfileEmail;
    private TextView editProfileNameError, editProfileDateError, editProfileAddressError, editProfileEmailError;
    private TextView changeProfileImageBtn;
    private String imageURI;

    private EngkitHelperDB engkitHelperDB;

    private URL imageURL;
    private String myURL = "";

    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        createVariable();
        getEditProfileData();
        setOnClickSaveBtn();
        setOnClickChangeProfileImgBtn();
        setOnClickCancelBtn();
        removeErrorMesssage();
    }

    private void createVariable() {
        imageURI = "none";
        sharedPref = this.getSharedPreferences("MyPreferences", this.MODE_PRIVATE);
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

    public void getEditProfileData() {
        try {
            String email = sharedPref.getString("email", "");
            Log.e("edit ",  email);
            Cursor profileData = engkitHelperDB.GetData("SELECT Name, Email, Date, Address, imageUri FROM Account ac WHERE ac.Email = '" + email + "'");
            if (profileData.moveToFirst()) {
                do {
                    editProfileName.setText(profileData.getString(0));
                    editProfileEmail.setText(profileData.getString(1));

                    editProfileDate.setText(convertDate(profileData.getString(2), false));
                    editProfileAddress.setText(profileData.getString(3));

//                    set image
//                    if (profileData.getString(4) != "none") {
//                        imageURI = profileData.getString(4);
//                        profileImage.setImageURI(Uri.parse(imageURI));
//                    }
                } while (profileData.moveToNext());
            } else {
                Toast.makeText(this, "data fetch failed", Toast.LENGTH_SHORT).show();
            }
            profileData.close();
        } catch (Exception e) {
            Toast.makeText(this, "data fetch failed", Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
        }
    }

    private String convertDate(String date, boolean toDatabase) {
        String[] regex = {"-", "/"};
        if (toDatabase) {
            regex[0] = "/";
            regex[1] = "-";
        }
        String[] dateArray = date.split(regex[0]);
        StringBuilder dateConvert = new StringBuilder(dateArray[dateArray.length - 1]);
        for (int i = dateArray.length - 2; i >= 0; i--) {
            dateConvert.append(regex[1] + dateArray[i]);
        }
        return dateConvert.toString();
    }

     private String convertDateToDatabase(String date) {
            String[] dateArray = date.split("/");
            StringBuilder dateConvert = new StringBuilder(dateArray[dateArray.length - 1]);
            for (int i = dateArray.length - 2; i >= 0; i--) {
                dateConvert.append("-" + dateArray[i]);
            }
            return dateConvert.toString();
        }


    private void setOnClickSaveBtn() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSave = true;
                String userName = editProfileName.getText().toString();
                String userDate = convertDate(editProfileDate.getText().toString(), true);
                String userAddress = editProfileAddress.getText().toString();
                String userEmail = editProfileEmail.getText().toString();

                if (validateRequire()) {
                    isSave = false;
                }

                if (!isSave) {
                    return;
                }

                try {
                    String email = sharedPref.getString("email", "");
                    engkitHelperDB.queryData("UPDATE Account SET Name = '" + userName + "', Date = '" + userDate + "', Address = '" + userAddress + "', Email = '" + userEmail + "', imageUri = '" + imageURI + "' WHERE Email = '" + email + "'");
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("email", userEmail);
                    editor.commit();
                    Toast.makeText(EditProfile.this, "Sửa hồ sơ thành công", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(EditProfile.this, "Sửa hồ sơ thất bại", Toast.LENGTH_SHORT).show();
                    System.out.println(e.getMessage());
                }

//                Intent intent = new Intent(EditProfile.this, MenuManager.class);
//                intent.putExtra("OPEN_FRAGMENT", "information");
//                startActivity(intent);
                finish();
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
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                profileImage.setImageURI(data.getData());
                imageURI = data.getData().toString();
            }
        }
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
