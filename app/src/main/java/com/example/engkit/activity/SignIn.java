package com.example.engkit.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.engkit.R;
import com.example.engkit.database.EngkitHelperDB;
import com.example.engkit.database.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.threeten.bp.LocalDate;

import java.util.Calendar;
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

public class SignIn extends AppCompatActivity {

    private EngkitHelperDB engkitHelperDB;
    private TextView datePickerTV;
    private DatePickerDialog datePickerDialog;
    private Button signInButton, getCodeButton;
    private TextInputEditText emailET, passwordET, passwordAgET, codeET, nameET;
    private TextInputLayout emailTextLayout, passwordTextLayout, passwordAgTextLayout, codeTextLayout, yearTextLayout, nameTextLayout;
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initDatePicker();
        createVariable();
        //getCode();
        transferToLogin();
    }

    void createVariable() {
        //db
        engkitHelperDB = new EngkitHelperDB(this, "EngkitDB.sqlite", null, 1);
        //element
        signInButton = (Button) findViewById(R.id.signIn_button);
        getCodeButton = (Button) findViewById(R.id.getcode_button);
        emailET = (TextInputEditText) findViewById(R.id.email_singin_field);
        passwordET = (TextInputEditText) findViewById(R.id.password_signin_field);
        passwordAgET = (TextInputEditText) findViewById(R.id.passwordag_signin_field);
        codeET = (TextInputEditText) findViewById(R.id.code_signin_field);
        datePickerTV = (TextView) findViewById(R.id.datePicker);
        nameET = (TextInputEditText) findViewById(R.id.name_signin_field);
        emailTextLayout = (TextInputLayout) findViewById(R.id.email_signin_layout);
        passwordTextLayout = (TextInputLayout) findViewById(R.id.password_signin_layout);
        passwordAgTextLayout = (TextInputLayout) findViewById(R.id.passwordag_signin_layout);
        codeTextLayout = (TextInputLayout) findViewById(R.id.code_signin_layout);
        nameTextLayout = (TextInputLayout) findViewById(R.id.name_signin_layout);
        datePickerTV.setText(getTodaysDate());
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                datePickerTV.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + month + "-" + day;
    }


    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        //default should never happen
        return "JAN";
    }

    public void openDatePicker1(View view) {
        datePickerDialog.show();
    }

    Boolean checkInfomation() {
        if (!emailET.getText().toString().equals("") && !passwordET.getText().toString().equals("")
                && !passwordAgET.getText().toString().equals("") && codeET.getText().toString().equals(message)
                && !nameET.getText().toString().equals("") && !datePickerTV.getText().toString().equals("")) {
            return true;
        }
        return false;
    }

    void transferToLogin() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInfoSignIn() == true) {
                    Intent intentLogin = new Intent(SignIn.this, Login.class);
                    String emailUser = emailET.getText().toString();
                    String codeUser = codeET.getText().toString();
                    String pwUser = passwordET.getText().toString();
                    String nameUser = nameET.getText().toString();
                    LocalDate dobUser = LocalDate.parse(datePickerTV.getText().toString());
                    //LocalDate dobUser = LocalDate.parse("2022-01-01");
                    User user1 = new User(0, emailUser, codeUser, pwUser, dobUser, nameUser);
                    System.out.println(emailUser + "   " + pwUser + "   " + nameUser + "    " + dobUser);
                    engkitHelperDB.queryData("INSERT INTO Account VALUES(null,'" + emailUser + "','" + codeUser + "','" + pwUser + "', '" + nameUser + "','" + dobUser + "')");

                    startActivity(intentLogin);
                }
            }
        });
    }


//    void getCode() {
//        getCodeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkEmail() == true) {
//                    Toast.makeText(SignIn.this, "OKKKKKKKKK", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(SignIn.this, "Failllllll", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

//    Boolean checkEmail() {
//        String email = emailET.getText().toString();
//        Cursor dataAccount = engkitHelperDB.GetData("SELECT * FROM Account AS ac WHERE ac.Email = '" + email + "'");
//        while (dataAccount.moveToNext()) {
//            return false;
//        }
//        return true;
//    }



//    public void checkCode(View view) {
//        if (codeET.getText().toString().equals(message)) {
//            Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Failt", Toast.LENGTH_SHORT).show();
//        }
//    }

    private int random() {
        int min = 100000;
        int max = 999999;
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        return random_int;
    }

    public void buttonSendCode(View view) {

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
            mimeMessage.setText("Your OTP code: " + message);
            Toast.makeText(SignIn.this, "Success", Toast.LENGTH_SHORT).show();
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

    Boolean checkInfoSignIn(){
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String passwordAg = passwordAgET.getText().toString();
        String code = codeET.getText().toString();
        String name = nameET.getText().toString();
        if(email.equals("")){
            emailTextLayout.setErrorEnabled(true);
            emailTextLayout.setError("You need to enter an Email");
            return false;
        } else if(!email.contains("@")){
            emailTextLayout.setErrorEnabled(true);
            emailTextLayout.setError("The syntax of an Email was wrong");
            return false;
        }
        if(code.equals("")) {
            codeTextLayout.setErrorEnabled(true);
            codeTextLayout.setError("You need to enter your Code");
            return false;
        } else if(!code.equals(message)) {
            codeTextLayout.setErrorEnabled(true);
            codeTextLayout.setError("You Code is incorrect");
            return false;
        }
        if(name.equals("")) {
            nameTextLayout.setErrorEnabled(true);
            nameTextLayout.setError("You need to enter your name");
            return false;
        }
        if(password.equals("")) {
            passwordTextLayout.setErrorEnabled(true);
            passwordTextLayout.setError("You need to enter your password");
            return false;
        } else if(password.length()<6) {
            passwordTextLayout.setErrorEnabled(true);
            passwordTextLayout.setError("Your password must more than 6 kt");
            return false;
        }
        if(passwordAg.equals("")) {
            passwordAgTextLayout.setErrorEnabled(true);
            passwordAgTextLayout.setError("Your password again and password isn't the same");
            return false;
        }
        if(!passwordAg.equals(password)) {
            passwordAgTextLayout.setErrorEnabled(true);
            passwordAgTextLayout.setError("Your password again and password isn't the same");
            return false;
        }
        return true;
    }
}
