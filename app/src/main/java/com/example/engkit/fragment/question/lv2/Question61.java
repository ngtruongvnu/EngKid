package com.example.engkit.fragment.question.lv2;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.engkit.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question61#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question61 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView engSentence;
    LinearLayout wordSet;
    LinearLayout userAns;
    ImageButton speakSlower;
    Button checkOrder;
    private HashMap engToVie;
    TextToSpeech tts;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Question61() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question61.
     */
    // TODO: Rename and change types and number of parameters
    public static Question61 newInstance(String param1, String param2) {
        Question61 fragment = new Question61();
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
    }

    public HashMap keyAndValue() {
        HashMap<String, String> imgAndPath = new HashMap<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.sentence);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String eachLine;
            while ((eachLine = bufferedReader.readLine()) != null) {
                String[] separate;
                separate = eachLine.split(":", 2);
                imgAndPath.put(separate[0], separate[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return imgAndPath;
    }

    List<Button> buttonList;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_third_game, container, false);
        engSentence = rootView.findViewById(R.id.eng_sentence);
        wordSet = rootView.findViewById(R.id.wordSet);
        userAns = rootView.findViewById(R.id.user_awswer);
        speakSlower = rootView.findViewById(R.id.spkSlower);
        checkOrder = rootView.findViewById(R.id.checkOrder);
        engToVie = keyAndValue();
        String english = randomSentence();
//        Log.d(s,s);
        ArrayList separate = separateSentence(english);
        ArrayList vieSentence = vieSentence(english);
        int size = separate.size()-1;
        for(Object o:separate) {
            Button button = new Button(this.getContext());
            button.setTag((Integer) size);
            size--;
            button.setText(o.toString());
            wordSet.addView(button);
//            buttonList.add(button);
        }

        for(int i = 0; i < wordSet.getChildCount(); i++) {
            onClickEachButton((Button) wordSet.getChildAt(i));
        }
        checkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkUserOrder(orderUser(), vieSentence)) {
                    MediaPlayer ting = MediaPlayer.create(getContext(), R.raw.ting);
                    ting.start();
                    checkOrder.setText("True");
                } else {
                    MediaPlayer unting = MediaPlayer.create(getContext(), R.raw.unting);
                    unting.start();
                    userAns.removeAllViews();
                    for(Object o:separate) {
                        Button button = new Button(getContext());
//                        button.setTag((Integer) size);
//                        size--;
                        button.setText(o.toString());
                        wordSet.addView(button);
//            buttonList.add(button);
                    }
                    for(int i = 0; i < wordSet.getChildCount(); i++) {
                        onClickEachButton((Button) wordSet.getChildAt(i));
                    }
                }
            }
        });
        tts = new TextToSpeech(this.getContext(), status -> {
            // TODO Auto-generated method stub
            if(status == TextToSpeech.SUCCESS){
                int result=tts.setLanguage(Locale.US);
                if(result==TextToSpeech.LANG_MISSING_DATA ||
                        result==TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("error", "This Language is not supported");
                }
                else{
                    ConvertTextToSpeech();
                }
            }
            else
                Log.e("error", "Initilization Failed!");
        });
        speakSlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertTextToSpeech();
            }
        });
        return rootView;
    }

    public void onClickEachButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordSet.removeView(button);
                userAns.addView(button);
            }
        });
    }

    public ArrayList orderUser() {
        ArrayList<String> orders = new ArrayList<>();
        int userSize = userAns.getChildCount();
        for(int i = 0 ; i < userSize; i++) {
            Button btn =(Button) userAns.getChildAt(i);
            orders.add((String) btn.getText());
        }
        return orders;
    }

    public boolean checkUserOrder(ArrayList order, ArrayList vieSentence) {
        if(order.equals(vieSentence)) {
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList vieSentence(String key) {
//        key = "I am a women";
        String vieSentence = (String) engToVie.get((String) key);
        String afterSplit[] = vieSentence.split(" ");
        ArrayList vietWord = new ArrayList<>();
        for(String s:afterSplit) {
            vietWord.add((String) s);
            Log.d(s, s);
        }
        return vietWord;
    }

    public ArrayList separateSentence(String key) {
//        key = "I am a women";
        String vieSentence = (String) engToVie.get((String) key);
        String afterSplit[] = vieSentence.split(" ");
        ArrayList vietWord = new ArrayList<>();
        for(String s:afterSplit) {
            vietWord.add((String) s);
            Log.d(s, s);
        }
        Collections.shuffle(vietWord);
        return vietWord;
    }

    public String randomSentence() {
        int size = engToVie.size();
        Random random = new Random();
        int numRan = random.nextInt(size);
        ArrayList sentences = new ArrayList<String>(engToVie.keySet());
        engSentence.setText((String) sentences.get(numRan));
        Log.d((String) sentences.get(numRan), (String) sentences.get(numRan));
        return (String) engSentence.getText();
    }

    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        String text = (String) engSentence.getText();
        if(null == text)
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onClick(View view) {
        ConvertTextToSpeech();
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}