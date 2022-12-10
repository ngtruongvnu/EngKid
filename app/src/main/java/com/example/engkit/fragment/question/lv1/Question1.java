package com.example.engkit.fragment.question.lv1;

import static android.graphics.Color.GREEN;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.engkit.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question1 extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Pair<String, String> pairWord = new Pair<>("banana", "chuối");


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Question1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment question1.
     */
    // TODO: Rename and change types and number of parameters
    public static Question1 newInstance(String param1, String param2) {
        Question1 fragment = new Question1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//        btn1.setOnClickListener(this);
    }



//    public ArrayList<Pair<String, String>> pairWord(InputStream inputStream) throws Exception {
//        ArrayList<Pair<String,String>> pairArrayList = new ArrayList<>();
//        inputStream = getResources().openRawResource(R.raw.tappair);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        String eachLine;
//        while ((eachLine = bufferedReader.readLine()) != null){
//            String[] separate;
//            separate = eachLine.split(":", 2);
//            Pair<String,String> pair = new Pair<>(separate[0], separate[1]);
//            pairArrayList.add(pair);
//        }
//        return pairArrayList;
//    }


    private HashMap keyAndValue;

    public HashMap mapWord() throws Exception {
        HashMap<String,String> hashMap = new HashMap<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.tappair);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String eachLine;
            while ((eachLine = bufferedReader.readLine()) != null) {
                String[] separate;
                separate = eachLine.split(":", 2);
                hashMap.put(separate[0], separate[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return hashMap;
    }

    public boolean checkPair(String keys, String values) {
        boolean check = false;
        if(!keyAndValue.isEmpty()) {
            if(keyAndValue.containsKey(keys)) {
                if(keyAndValue.get(keys).equals(values)) {
                    check = true;
                }
            }
        }
        return check;
    }

    public List<Button> getAllBtn(ViewGroup layout) throws Exception {
//        keyAndValue = mapWord();
        List<Button> buttonList = new ArrayList<>();
        for(int i = 0; i < layout.getChildCount(); i++){
            View v =layout.getChildAt(i);
            if(v instanceof Button){
                buttonList.add((Button) v);
            }
        }
        return  buttonList;
    }

    private static final int[] BUTTONS = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6
    };

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Boolean checkbt1 = false;
    Boolean checkbt2 = false;
    Boolean checkbt3 = false;
    Boolean checkbt4 = false;
    Boolean checkbt5 = false;
    Boolean checkbt6 = false;
//    int count =0;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_first_game, container, false);
        TextView txt = rootView.findViewById(R.id.tap_the_pair);
        txt.setText("Tap the pair");

        try {
            keyAndValue = mapWord();
            TextView count = rootView.findViewById(R.id.countBtn);
            count.setText("6");
            btn1 = rootView.findViewById(R.id.button1);
             btn2 = rootView.findViewById(R.id.button2);
             btn3 = rootView.findViewById(R.id.button3);
             btn4 = rootView.findViewById(R.id.button4);
             btn5 = rootView.findViewById(R.id.button5);
             btn6 = rootView.findViewById(R.id.button6);
            View btnChild = rootView.findViewById(R.id.to_get_child);
            List<Button> allBtn = getAllBtn((ViewGroup) btnChild);
            ArrayList keys = new ArrayList<>(keyAndValue.keySet());
            ArrayList values = new ArrayList<>(keyAndValue.values());
            // TODO lấy ngẫu nhiên
            keys.addAll(values);
            Collections.shuffle(keys);
            setBgrColorBegin();
            onClickbtn1();
            onClickbtn2();
            onClickbtn3();
            onClickbtn4();
            onClickbtn5();
            onClickbtn6();
            for (int i = 0; i < BUTTONS.length; i++) {
                Button b = rootView.findViewById(BUTTONS[i]);
                b.setText(keys.get(i).toString());
            }
            count.setText(btn1.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @SuppressLint("ResourceAsColor")
    public void setBgrColorBegin(){
        btn1.setBackgroundColor(R.color.teal_200);
        btn2.setBackgroundColor(R.color.teal_200);
        btn3.setBackgroundColor(R.color.teal_200);
        btn4.setBackgroundColor(R.color.teal_200);
        btn5.setBackgroundColor(R.color.teal_200);
        btn6.setBackgroundColor(R.color.teal_200);
    }

    public void onClickbtn1() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(!checkbt1) {
                    checkbt1 = true;
                } else {
                    checkbt1 = false;
                    btn1.setBackgroundColor(R.color.teal_200);
                }
            }
        });
    }


    public void onClickbtn2() {}
    public void onClickbtn3() {}
    public void onClickbtn4() {}
    public void onClickbtn5() {}
    public void onClickbtn6() {}

    public void searchPair() {

    }

    int countGreen = 0;
    int countBlue = 0;
    int countYellow = 0;
    public void setButtonPickedColor(Button button, int count) {
    }
//    public void onClickbtn1() {
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(checkbt1 == false) {
//                    checkbt1 = true;
//                    setButtonPickedColor(btn1,count);
//                } else {
//                    checkbt1 = false;
//                    btn1.setBackgroundColor(R.color.teal_200);
//                    count--;
//                    if (count<0) count = 0;
//                }
//            }
//        });
//    }
//    public void onClickbtn2() {
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(checkbt2 == false) {
//                    checkbt2 = true;
//                    count++;
//                    setButtonPickedColor(btn2,count);
//                } else {
//                    checkbt2 = false;
//                    btn2.setBackgroundColor(R.color.teal_200);
//                    count--;
//                    if (count<0) count = 0;
//                }
//            }
//        });
//    }
//    public void onClickbtn3() {
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(checkbt3 == false) {
//                    checkbt3 = true;
//                    count++;
//                    setButtonPickedColor(btn3,count);
//                } else {
//                    checkbt3 = false;
//                    btn3.setBackgroundColor(R.color.teal_200);
//                    count--;
//                    if (count<0) count = 0;
//                }
//            }
//        });
//    }
//    public void onClickbtn4() {
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(checkbt4 == false) {
//                    checkbt4 = true;
//                    count++;
//
//                    setButtonPickedColor(btn4,count);
//                } else {
//                    checkbt4 = false;
//                    btn4.setBackgroundColor(R.color.teal_200);
//                    count--;
//                    if (count<0) count = 0;
//
//                }
//            }
//        });
//    }
//    public void onClickbtn5() {
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(checkbt5 == false) {
//                    checkbt5 = true;
//                    count++;
//                    setButtonPickedColor(btn5,count);
//                } else {
//                    checkbt5 = false;
//                    btn5.setBackgroundColor(R.color.teal_200);
//                    count--;
//                    if (count<0) count = 0;
//                }
//            }
//        });
//    }
//    public void onClickbtn6() {
//        btn6.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(checkbt6 == false) {
//                    checkbt6 = true;
//                    count++;
//                    setButtonPickedColor(btn6,count);
//                } else {
//                    checkbt6 = false;
//                    btn6.setBackgroundColor(R.color.teal_200);
//                    count--;
//                    if (count<0) count = 0;
//                }
//            }
//        });
//    }

//    int countGreen = 0;
//    int countBlue = 0;
//    int countYellow = 0;
//
//    public void setButtonPickedColor(Button button, int count){
//        if(count == 1 || count == 2) {
//            button.setBackgroundColor(Color.GREEN);
//            countGreen++;
//        }  if (count == 3 || count == 4) {
//                button.setBackgroundColor(Color.BLUE);
//                countBlue++;
//        } if (count == 5 || count == 6){
//            countYellow++;
//            button.setBackgroundColor(Color.YELLOW);
//        }
//    }
//
//
}