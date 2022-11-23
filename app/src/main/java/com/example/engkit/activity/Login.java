package com.example.engkit.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.engkit.R;
import com.example.engkit.database.EngkitHelperDB;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private EngkitHelperDB engkitHelperDB;
    private Button signIn_button,loginButton;
    private TextView mk_text;
    private TextInputEditText emailTv,passwordTv;
    private TextInputLayout emailTextLayout,passwordTextLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createVariable();
        buildDatabase();
        transferToMenu();
        transferToFogetMK();
        transferToSignin();

    }

    void createVariable(){
        //db
        engkitHelperDB = new EngkitHelperDB(this,"EngkitDB.sqlite",null,1);
        //element
        signIn_button = (Button) findViewById(R.id.signIn_button);
        loginButton = (Button) findViewById(R.id.login_button);
        mk_text = (TextView) findViewById(R.id.quenmk_text);
        emailTv = (TextInputEditText) findViewById(R.id.email_login_field);
        passwordTv = (TextInputEditText) findViewById(R.id.password_login_field);
        emailTextLayout = (TextInputLayout) findViewById(R.id.email_login_layout);
        passwordTextLayout = (TextInputLayout) findViewById(R.id.password_login_layout);
    }

    void transferToMenu(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checklogin() == true) {
                    Intent intentLogin = new Intent(Login.this, MenuManager.class);
                    startActivity(intentLogin);
                    //engkitHelperDB.queryData("DROP TABLE Account");

                }
            }
        });
    }


    void transferToSignin(){
        signIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignin = new Intent(Login.this,SignIn.class);
                startActivity(intentSignin);
                //engkitHelperDB.queryData("DROP TABLE Account");
                //buildDatabase();
            }
        });
    }

    void transferToFogetMK(){
        mk_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMK = new Intent(Login.this, com.example.engkit.activity.GetAccountBack.class);
                startActivity(intentMK);
            }
        });
    }


    Boolean checklogin(){
        String email = emailTv.getText().toString();
        String password = passwordTv.getText().toString();
        Cursor dataAccount = engkitHelperDB.GetData("SELECT * FROM Account AS ac WHERE ac.Email = '"+email+"' AND ac.Password = '"+password+"'");
        while (dataAccount.moveToNext()) {
            return true;
        }
        if(emailTv.getText().toString().equals("")){
            emailTextLayout.setErrorEnabled(true);
            emailTextLayout.setError("You need to enter an Email");
            return false;
        } else if(!emailTv.getText().toString().contains("@")){
            emailTextLayout.setErrorEnabled(true);
            emailTextLayout.setError("The syntax of an Email was wrong");
            return false;
        }
        if(passwordTv.getText().toString().equals("")) {
            passwordTextLayout.setErrorEnabled(true);
            passwordTextLayout.setError("You need to enter a Password");
            return false;
        }

        return false;
    }

    private void buildDatabase(){
        // tao bang
        //engkitHelperDB.queryData("DROP TABLE Account");
        engkitHelperDB.queryData("CREATE TABLE IF NOT EXISTS Account (Id INTEGER PRIMARY KEY AUTOINCREMENT, Email varchar(30), Code varchar(10), Password varchar(30), Name varchar(30), Date date)");
        // them du lieu
        //engkitHelperDB.queryData("INSERT INTO Account VALUES (null,'hoang@gmail.com','123456', 'Vu Duy Hoang','2022-01-24')");

    }

    public void transferToDatabase(View view){
        Intent intent = new Intent(Login.this,Database.class);
        startActivity(intent);
    }
}