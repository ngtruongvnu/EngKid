package com.example.engkit.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.engkit.R;
import com.example.engkit.database.EngkitHelperDB;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GetAccountBack extends AppCompatActivity {

    private EngkitHelperDB engkitHelperDB;
    private String message = "";
    ImageButton back;
    TextInputLayout emailTextIL,codeTextIL,passwordTextIL,passwordAgainTextIL;
    TextInputEditText emailInput,codeInput,passwordInput,passwordAgainInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getback_account);
        createVariable();
        TransferToLogin();
        System.out.println("Alo"+codeInput.getText().toString());
        codeInput.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (codeInput.getText().toString().equals(message)) {
                      passwordTextIL.setVisibility(View.VISIBLE);
                      passwordAgainTextIL.setVisibility(View.VISIBLE);
                  } else {
                      passwordTextIL.setVisibility(View.INVISIBLE);
                      passwordAgainTextIL.setVisibility(View.INVISIBLE);
                  }
              }
        });
    }

    private void createVariable(){
        //DB
        engkitHelperDB = new EngkitHelperDB(this, "EngkitDB.sqlite", null, 1);
        //
        back = findViewById(R.id.ButtonBack);
        emailTextIL = (TextInputLayout) findViewById(R.id.email_gba_layout);
        emailInput = (TextInputEditText) findViewById(R.id.email_gba_field);
        passwordTextIL = (TextInputLayout) findViewById(R.id.passw_layout);
        passwordAgainTextIL = (TextInputLayout) findViewById(R.id.passw_again_layout);
        passwordInput = (TextInputEditText) findViewById(R.id.passw_input_field);
        passwordAgainInput = (TextInputEditText) findViewById(R.id.passw_again_input_field);
        codeTextIL = (TextInputLayout) findViewById(R.id.code_input_layout);
        codeInput = (TextInputEditText) findViewById(R.id.code_input_field);
    }

    void TransferToLogin(){

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentSignIn = new Intent(GetAccountBack.this,Login.class);
                startActivity(intentSignIn);
            }
        });
    }

    private int random() {
        int min = 100000;
        int max = 999999;
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        return random_int;
    }

    public void buttonSendCodeGAB(View view) {

        try {
            String stringSenderEmail = "hoang240102@gmail.com";
            String stringReceiverEmail = "20020413@vnu.edu.vn";
            String stringPasswordSenderEmail = "gvgogqkxgmurfdql";

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
            message = Integer.toString(random());
            mimeMessage.setSubject("Engkit App: OTP Code");
            mimeMessage.setText("Your OTP code Setback Account: " + message);
            Toast.makeText(GetAccountBack.this, "Success", Toast.LENGTH_SHORT).show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void changePassWordButton(View view){
        if(checkInfoGAB()){
            Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show();
            String nPassword = passwordInput.getText().toString();
            String email = emailInput.getText().toString();
            engkitHelperDB.queryData("UPDATE Account SET Password = '"+nPassword+"' WHERE Email = '"+email+"' ");
        } else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    Boolean checkInfoGAB(){
        String email = emailInput.getText().toString();
        String nPassword = passwordInput.getText().toString();
        String nPasswordAg = passwordAgainInput.getText().toString();
        String code = codeInput.getText().toString();
        boolean check1 = false;
        Cursor dataAccount = engkitHelperDB.GetData("SELECT * FROM Account AS ac WHERE ac.Email = '"+email+"' ");
        while (dataAccount.moveToNext()) {
            check1 = true;
        }
        if(check1 == false){
            emailTextIL.setErrorEnabled(true);
            emailTextIL.setError("You Email hasn't registered");
        }
        if(email.equals("")){
            emailTextIL.setErrorEnabled(true);
            emailTextIL.setError("You need to enter an Email");
            return false;
        } else if(!email.contains("@")){
            emailTextIL.setErrorEnabled(true);
            emailTextIL.setError("The syntax of an Email was wrong");
            return false;
        }
        if(code.equals("")) {
            codeTextIL.setErrorEnabled(true);
            codeTextIL.setError("You need to enter your Code");
            return false;
        } else if(!code.equals(message)) {
            codeTextIL.setErrorEnabled(true);
            codeTextIL.setError("You Code is incorrect");
            return false;
        }
        if(nPassword.equals("")) {
            passwordTextIL.setErrorEnabled(true);
            passwordTextIL.setError("You need to enter your password");
            return false;
        } else if(nPassword.length()<6) {
            passwordTextIL.setErrorEnabled(true);
            passwordTextIL.setError("Your password must more than 6 kt");
            return false;
        }
        if(nPasswordAg.equals("")) {
            passwordAgainTextIL.setErrorEnabled(true);
            passwordAgainTextIL.setError("Your password again and password isn't the same");
            return false;
        }
        if(!nPasswordAg.equals(nPassword)) {
            passwordAgainTextIL.setErrorEnabled(true);
            passwordAgainTextIL.setError("Your password again and password isn't the same");
            return false;
        }
        return check1;
    }

}