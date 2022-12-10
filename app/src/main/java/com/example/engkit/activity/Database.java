package com.example.engkit.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.engkit.R;
import com.example.engkit.adapter.UserAdapter;
import com.example.engkit.database.EngkitHelperDB;
import com.example.engkit.database.User;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Database extends AppCompatActivity {

    private EngkitHelperDB engkitHelperDB;
    private ListView listView;
    private List<User> listUser;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        engkitHelperDB = new EngkitHelperDB(this,"EngkitDB.sqlite",null,1);
        listView = findViewById(R.id.listDB);
        listUser = new ArrayList<>();
        userAdapter = new UserAdapter(this,R.layout.item_db,listUser);
        Cursor dataAccount = engkitHelperDB.GetData("SELECT * FROM Account");
        User user = new User(100,"      email        "," code ","password",LocalDate.parse("2002-01-24"),"name");
        listUser.add(user);
        while (dataAccount.moveToNext()) {
            int id = dataAccount.getInt(0);
            String email = dataAccount.getString(1);
            String code = dataAccount.getString(2);
            String passw = dataAccount.getString(3);
            String name = dataAccount.getString(4);
            LocalDate dob = LocalDate.parse(dataAccount.getString(5));
            user = new User(id,email,code,passw,dob,name);
            listUser.add(user);
        }
        userAdapter = new UserAdapter(this,R.layout.item_db,listUser);
        listView.setAdapter(userAdapter);
    }

}