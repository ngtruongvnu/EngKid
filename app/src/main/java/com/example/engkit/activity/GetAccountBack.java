package com.example.engkit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.engkit.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class GetAccountBack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getback_account);
        TransferToLogin();
        TextInputLayout passwordTextIL = (TextInputLayout) findViewById(R.id.passw_layout);
        TextInputLayout passwordAgainTextIL = (TextInputLayout) findViewById(R.id.passw_again_layout);
        TextInputEditText passwordInput = (TextInputEditText) findViewById(R.id.passw_input_field);
        TextInputEditText passwordAgainInput = (TextInputEditText) findViewById(R.id.passw_again_input_field);
        TextInputEditText codeInput = (TextInputEditText) findViewById(R.id.code_input_field);
        System.out.println("Alo"+codeInput.getText().toString());
        codeInput.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (codeInput.getText().toString().equals("1234")) {
                      passwordTextIL.setVisibility(View.VISIBLE);
                      passwordAgainTextIL.setVisibility(View.VISIBLE);
                  } else {
                      passwordTextIL.setVisibility(View.INVISIBLE);
                      passwordAgainTextIL.setVisibility(View.INVISIBLE);
                  }
              }
        });
    }

    void TransferToLogin(){
        Button changeMK = (Button) findViewById(R.id.changepw_button);
        changeMK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentSignIn = new Intent(GetAccountBack.this,Login.class);
                startActivity(intentSignIn);
            }
        });
    }
}