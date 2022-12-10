package com.example.engkit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.engkit.R;
import com.example.engkit.fragment.question.lv1.Question2;

// cai activity nay se quan li 10 cau hoi cua cái khoá
public class LessonManager2 extends AppCompatActivity {

    private Question2 question2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_manager2);
        question2 = new Question2();
        getSupportFragmentManager().executePendingTransactions();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_ques_manager2, question2).commit();
    }
}