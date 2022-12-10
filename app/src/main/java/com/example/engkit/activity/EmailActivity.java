package com.example.engkit.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.engkit.R;

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

public class EmailActivity extends AppCompatActivity {

    private EditText editText;
    private String message = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        editText = findViewById(R.id.edt1);
    }

    public void check(View view){
        if(editText.getText().toString().equals(message)){
            Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failt", Toast.LENGTH_SHORT).show();
        }
    }

    private int random() {
        int min = 100000;
        int max = 999999;
        int random_int = (int)(Math.random() * (max - min + 1) + min);
        return random_int;
    }

    public void buttonSendEmail(View view){

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
            mimeMessage.setText("Your OTP code: "+message);
            Toast.makeText(EmailActivity.this, "Success", Toast.LENGTH_SHORT).show();
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
}