package com.example.engkit.fragment.question.lv1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.UriMatcher;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.engkit.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageButton speaker;
    TextView word;
    ImageButton imgBtn1;
    ImageButton imgBtn2;
    ImageButton imgBtn3;
    ImageButton imgBtn4;
    static List imgIDs = new ArrayList<Integer>() {{
        add(R.drawable.beer);
        add(R.drawable.coke);
        add(R.drawable.fruit_juice);
        add(R.drawable.wine_bottle);
        add(R.drawable.bubble_tea);
        add(R.drawable.coffee_cup);
        add(R.drawable.milk);
        add(R.drawable.lemon_tea);
        add(R.drawable.water);
    }};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Question2() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question2.
     */
    // TODO: Rename and change types and number of parameters
    public static Question2 newInstance(String param1, String param2) {
        Question2 fragment = new Question2();
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


    private HashMap wordAndUrl;

    public HashMap fileToMap() {
        HashMap<String, Integer> imgAndPath = new HashMap<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.word);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String eachLine;
            while ((eachLine = bufferedReader.readLine()) != null) {
                String[] separate;
                separate = eachLine.split(":", 2);
                // TODO chuyển 1 thành 2
                imgAndPath.put(separate[0], Integer.parseInt(separate[1]));
//                imgAndPath.put(separate[0], 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return imgAndPath;
    }
    public HashMap keyAndValue() {
        HashMap<String, Integer> imgAndPath = new HashMap<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.word);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String eachLine;
            while ((eachLine = bufferedReader.readLine()) != null) {
                String[] separate;
                separate = eachLine.split(":", 1);
                // TODO chuyển 1 thành 2
                imgAndPath.put(separate[0], imgIDs.listIterator().nextIndex());
//                imgAndPath.put(separate[0], 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return imgAndPath;
    }

    TextToSpeech tts;
    Button checkLess2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_second_game, container, false);
        speaker = rootView.findViewById(R.id.speakerBtn);
        word = rootView.findViewById(R.id.word);
        imgBtn1 = rootView.findViewById(R.id.imgBtn1);
        imgBtn2 = rootView.findViewById(R.id.imgBtn2);
        imgBtn3 = rootView.findViewById(R.id.imgBtn3);
        imgBtn4 = rootView.findViewById(R.id.imgBtn4);
        checkLess2 = rootView.findViewById(R.id.checkLess2);
        wordAndUrl = fileToMap();
//        ArrayList words = new ArrayList<String>(wordAndUrl.keySet());
//        wordAndUrl = keyAndValue();
        ArrayList ids = new ArrayList<Integer>(wordAndUrl.values());
        String s = ((Integer) R.drawable.coffee_cup).toString();
        int number = (int) ids.get(0);
//        imgBtn1.setTag(123);
//        String s1 = imgBtn1.getTag().toString();
//        for(int i = 0; i < imgIDs.size(); i++)
//            Log.d(imgIDs.get(i).toString(), s1);
        String word = wordRandom();
        randomImg(word);
//        v.getTag() == (Integer) wordAndUrl.get((String) word)
//        v.getTag().toString() == wordAndUrl.get((String) word).toString()
        imgBtn1.setOnClickListener(v -> {
            if(v.getTag().equals(wordAndUrl.get(word))) {
                MediaPlayer ting = MediaPlayer.create(getContext(), R.raw.ting);
                ting.start();
                checkLess2.setText("True");
            } else {
                MediaPlayer unting = MediaPlayer.create(getContext(), R.raw.unting);
                unting.start();
            }
        });
        imgBtn2.setOnClickListener(v -> {
            if(v.getTag().equals(wordAndUrl.get(word))) {
                MediaPlayer ting = MediaPlayer.create(getContext(), R.raw.ting);
                ting.start();
                checkLess2.setText("True");
            } else {
                MediaPlayer unting = MediaPlayer.create(getContext(), R.raw.unting);
                unting.start();
            }
        });
        imgBtn3.setOnClickListener(v -> {
            if(v.getTag().equals(wordAndUrl.get(word))) {
                MediaPlayer ting = MediaPlayer.create(getContext(), R.raw.ting);
                ting.start();
                checkLess2.setText("True");
            } else {
                MediaPlayer unting = MediaPlayer.create(getContext(), R.raw.unting);
                unting.start();
            }
        });
        imgBtn4.setOnClickListener(v -> {
            if(v.getTag().equals(wordAndUrl.get(word))) {
                MediaPlayer ting = MediaPlayer.create(getContext(), R.raw.ting);
                ting.start();
                checkLess2.setText("True");
            } else {
                MediaPlayer unting = MediaPlayer.create(getContext(), R.raw.unting);
                unting.start();
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
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertTextToSpeech();
            }
        });
        return rootView;
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


    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        String text = word.getText().toString();
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

    public void randomImg(String key) {
        List<ImageButton> imageButtons = new ArrayList<>();
//        imageButtons.add(imgBtn1);
        imageButtons.add(imgBtn2);
        imageButtons.add(imgBtn3);
        imageButtons.add(imgBtn4);
        int lenght = imgIDs.size();
        List<Integer> tempIDs = new ArrayList<>(imgIDs);
        int imgID = (int) wordAndUrl.get(key);
        tempIDs.remove((Object) imgID);
        Random random = new Random();
        Set<Integer> set = new LinkedHashSet<>();
        while (set.size() < 3) {
            set.add(random.nextInt(lenght));
        }
        ArrayList<Integer> numRan = new ArrayList<>();
        for(Integer integer:set)
            numRan.add(integer);
        for(ImageButton imageButton : imageButtons) {
            imageButton.setImageResource((int) imgIDs.get(numRan.get(numRan.size()-1)));
            imageButton.setTag(imgIDs.get(numRan.get(numRan.size()-1)));
            numRan.remove(numRan.size()-1);
        }

        imgBtn1.setImageResource(imgID);
        imageButtons.add(imgBtn1);
        imgBtn1.setTag(imgID);
        Collections.shuffle(imageButtons);
        // TODO: cần phải chắc chắn trong các img phải có 1 cái là đáp án
        // TODO: câu hỏi đặt ra là sao có thể ánh xạ được từ word sang ảnh
    }

    public String wordRandom() {
        int lenght = wordAndUrl.size();
        Random random = new Random();
        int numRan = random.nextInt(lenght);
        ArrayList words = new ArrayList<String>(wordAndUrl.keySet());
        word.setText((String) words.get(numRan));
        return (String) word.getText();
    }

//    public void onClick1(View view) {
//
//    }

//    public void appendIdImg() throws IOException {
//        FileInputStream fstreamRead = new FileInputStream(String.valueOf(getResources().openRawResource(R.raw.word)));
//        FileInputStream fstreamWrite = new FileInputStream(String.valueOf(getResources().openRawResource(R.raw.wordappend)));
//        BufferedReader br = new BufferedReader(new InputStreamReader(fstreamRead));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fstreamWrite), true));
//        String eachLine;
//        int i = 0;
//        while ((eachLine = br.readLine()) != null) {
//            bw.write(eachLine+":"+imgIDs.get(i));
//            i++;
//            bw.newLine();
//        }
//        bw.close();
//        br.close();
//    }

//    public void appendIdImg2() throws IOException {
//        FileReader fileReader = new FileReader(String.valueOf(getResources().openRawResource(R.raw.word2)));
//        FileWriter fileWriter = new FileWriter(String.valueOf(getResources().openRawResource(R.raw.wordappend)));
//        int i = 0;
//        String eachLine = "";
//        while ((i = fileReader.read()) != -1) {
//            eachLine += (char)i;
//        }fileWriter.write(eachLine);
//        fileReader.close();
//        fileWriter.close();
//    }
}
