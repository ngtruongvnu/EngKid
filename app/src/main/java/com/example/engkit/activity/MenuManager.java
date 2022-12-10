package com.example.engkit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.engkit.R;
import com.example.engkit.fragment.function.Home;
import com.example.engkit.fragment.function.Information;
import com.example.engkit.fragment.function.Rank;
import com.example.engkit.fragment.function.ReviewVocabulary;
import com.example.engkit.fragment.function.Task;

public class MenuManager extends AppCompatActivity {

    FragmentContainerView fragmentContainerView;
    ImageButton homeButton,reviewButton,rankButton,taskButton,infoButton,imageButtonOld;
    private Home homeFragment;
    private Information infoFragment;
    private Task taskFragment;
    private ReviewVocabulary reviewVocabularyFragment;
    private Rank rankFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manager);
        createVariable();
        clickButton();
        getSupportFragmentManager().executePendingTransactions();
        openFragment();
    }

    private void openFragment() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                switch (extras.getString("OPEN_FRAGMENT")) {
                    case "home":
                        homeButton.callOnClick();
                        break;
                    case "information":
                        infoButton.callOnClick();
                        break;
                    case "rank":
                        rankButton.callOnClick();
                        break;
                    case "reviewVocabulary":
                        reviewButton.callOnClick();
                        break;
                    case "task":
                        taskButton.callOnClick();
                        break;
                }
            } catch (NullPointerException e) {
                homeButton.callOnClick();
            }
        } else {
            homeButton.callOnClick();
//        }
        }
    }

    private void createVariable() {
        fragmentContainerView = findViewById(R.id.fragment_container_manager);
        imageButtonOld = null;
        homeButton = findViewById(R.id.home_bt);
        infoButton = findViewById(R.id.info_bt);
        taskButton = findViewById(R.id.task_bt);
        reviewButton = findViewById(R.id.review_bt);
        rankButton = findViewById(R.id.rank_bt);
        homeFragment = new Home();
        infoFragment = new Information();
        rankFragment = new Rank();
        taskFragment = new Task();
        reviewVocabularyFragment = new ReviewVocabulary();
    }

    public void clickButton() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeButton!=imageButtonOld) {
                    homeButton.setImageResource(R.drawable.house_icon_y);
                    processingClickButton();
                    imageButtonOld = homeButton;
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_manager,homeFragment).commit();
                }
            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoButton!=imageButtonOld) {
                    infoButton.setImageResource(R.drawable.avatar_icon_y);
                    processingClickButton();
                    imageButtonOld = infoButton;
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_manager,infoFragment).commit();
                }
            }
        });
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reviewButton!=imageButtonOld) {
                    reviewButton.setImageResource(R.drawable.dictionary_y);
                    processingClickButton();
                    imageButtonOld = reviewButton;
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_manager, reviewVocabularyFragment).commit();
                }
            }
        });
        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskButton!=imageButtonOld) {
                    taskButton.setImageResource(R.drawable.task_icon_y);
                    processingClickButton();
                    imageButtonOld = taskButton;
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_manager,taskFragment).commit();
                }
            }
        });
        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rankButton!=imageButtonOld) {
                    rankButton.setImageResource(R.drawable.rank_icon_y);
                    processingClickButton();
                    imageButtonOld = rankButton;
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_manager,rankFragment).commit();
                }
            }
        });
    }

    private void processingClickButton() {
        if (imageButtonOld == null) return;
        if(imageButtonOld==homeButton){
            getSupportFragmentManager().beginTransaction().remove(homeFragment).commit();
            imageButtonOld.setImageResource(R.drawable.house_icon_n);
        } else if (imageButtonOld==infoButton){
            getSupportFragmentManager().beginTransaction().remove(infoFragment).commit();
            imageButtonOld.setImageResource(R.drawable.avatar_icon_n);
        } else if (imageButtonOld==reviewButton) {
            getSupportFragmentManager().beginTransaction().remove(reviewVocabularyFragment).commit();
            imageButtonOld.setImageResource(R.drawable.dictionary_n);
        } else if (imageButtonOld==taskButton) {
            getSupportFragmentManager().beginTransaction().remove(taskFragment).commit();
            imageButtonOld.setImageResource(R.drawable.task_icon_n);
        } else if (imageButtonOld==rankButton) {
            getSupportFragmentManager().beginTransaction().remove(rankFragment).commit();
            imageButtonOld.setImageResource(R.drawable.rank_icon_n);
        }
    }

}