package com.example.engkit.fragment.function;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.engkit.R;
import com.example.engkit.adapter.VocabularyAdapter;
import com.example.engkit.database.EngkitHelperDB;
import com.example.engkit.entities.Vocabulary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReviewVocabulary extends Fragment {

    private TextToSpeech tts;

    public ReviewVocabulary() {
        // Required empty public constructor
    }

    public static ReviewVocabulary newInstance(String param1, String param2) {
        ReviewVocabulary fragment = new ReviewVocabulary();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        createTTS();
        ListView listView = (ListView) view.findViewById(R.id.listVocabulary);
        listView.setAdapter(new VocabularyAdapter(view.getContext(), getVocabularyList(), tts));
        return view;
    }

    private void createTTS() {
        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.US);
                    if(result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                } else {
                    Log.e("error", "Initilization Failed!");
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private List<Vocabulary> getVocabularyList() {
        List<Vocabulary> vocabularyList = new ArrayList<>();
        try {
            InputStream is = getResources().openRawResource(R.raw.vocabulary_1);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("-");
                vocabularyList.add(new Vocabulary(data[0], data[1]));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vocabularyList;
    }
}