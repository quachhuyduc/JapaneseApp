package com.example.japaneseapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.japaneseapp.R;
import com.example.japaneseapp.adapters.VocabularyAdapter;
import com.example.japaneseapp.database.DatabaseManager;
import com.example.japaneseapp.models.Vocabulary;
import com.example.japaneseapp.utils.Constant;

import java.util.ArrayList;
import java.util.Locale;

public class Vocabulary1Fragment extends Fragment implements VocabularyAdapter.OnCLickImageSpeakListener {
    private View view;
    private RecyclerView recyclerView;
    private DatabaseManager databaseManager;
    private ArrayList<Vocabulary> vocabularies;
    private VocabularyAdapter vocabularyAdapter;
    private TextToSpeech textToSpeech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerVocabulary);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        addData();
        vocabularyAdapter = new VocabularyAdapter(vocabularies);
        recyclerView.setAdapter(vocabularyAdapter);
        vocabularyAdapter.setOnCLickImageSpeakListener(this);
    }

    private void addData() {
        databaseManager = new DatabaseManager(getActivity());
        vocabularies = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        int position = intent.getIntExtra(Constant.POSITION_LESSON, 0);
        vocabularies = databaseManager.getListVocabulary(String.valueOf((position + 1)));
    }

    @Override
    public void onCLickImage(View view, int position) {
        String mp3 = vocabularies.get(position).getFileMp3();
        final String content = vocabularies.get(position).getWord();
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.JAPAN);
                    textToSpeech.speak(content, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}
