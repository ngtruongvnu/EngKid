package com.example.engkit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.engkit.R;
import com.example.engkit.fragment.question.lv1.Question1;
import com.example.engkit.fragment.question.lv1.Question2;
import com.example.engkit.fragment.question.lv2.Question61;
import com.example.engkit.fragment.question.lv2.Question62;

import org.w3c.dom.Text;

public class LessonManager extends AppCompatActivity {

    private TextView textView;
    private Question1 question1;
    private Question2 question2;
    private Question61 question61;
    private Question62 question62;
    private String lesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_manager);

        createVariable();
        getSupportFragmentManager().executePendingTransactions();
        if(lesson.equals("L1")) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_ques_manager, question1).commit();
        } else if(lesson.equals("Default")) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_ques_manager, question61).commit();
        }
    }

    public void createVariable(){
        Bundle bundle = getIntent().getExtras();
        lesson = bundle.getString("1", "Default");
        textView = findViewById(R.id.textView);
        question1 = new Question1();
        question2 = new Question2();
        question61 = new Question61();
        question62 = new Question62();
    }

    public void changeQuestion(View view){
        if(lesson.equals("L1")) {
            getSupportFragmentManager().beginTransaction().remove(question1).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_ques_manager, question2).commit();
        } else if(lesson.equals("Default")) {
            getSupportFragmentManager().beginTransaction().remove(question61).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_ques_manager, question62).commit();
        }
    }
}