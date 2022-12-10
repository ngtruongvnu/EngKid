package com.example.engkit.activity.lessonmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.engkit.R;
import com.example.engkit.fragment.question.lv1.Question2;

public class LessonManager4 extends AppCompatActivity {

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