package com.example.japaneseapp.fragments;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.japaneseapp.R;
import com.example.japaneseapp.database.DatabaseManager;
import com.example.japaneseapp.models.Primary;
import com.example.japaneseapp.utils.Constant;


import java.util.ArrayList;


public class TalkingFragment extends Fragment implements View.OnClickListener {
    public static int oneTimeOnly = 0;
    private View view;
    private TextView textViewTitle, textViewContent;
    private Button buttonPlay;
    private DatabaseManager databaseManager;
    private ArrayList<Primary> primaryArrayList;
    private ArrayList<Integer> arrayList;
    private int position;
    private ImageView imageViewBack, imageViewNext, imageViewPlay, imageViewStop;

    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_talking, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        imageViewBack = (ImageView) view.findViewById(R.id.iv_previous);
        imageViewNext = (ImageView) view.findViewById(R.id.iv_next);
        imageViewPlay = (ImageView) view.findViewById(R.id.iv_play);
        imageViewStop = (ImageView) view.findViewById(R.id.iv_stop);
        imageViewPlay.setOnClickListener(this);
        imageViewStop.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
        imageViewNext.setOnClickListener(this);
        databaseManager = new DatabaseManager(getActivity());
        textViewTitle = (TextView) view.findViewById(R.id.tv_title);
        textViewContent = (TextView) view.findViewById(R.id.tv_content);
       /* buttonPlay = (Button) view.findViewById(R.id.bt_play);
        buttonPlay.setOnClickListener(this);*/
        Intent intent = getActivity().getIntent();
        position = intent.getIntExtra(Constant.POSITION_LESSON, 0);
        primaryArrayList = databaseManager.getListTalking(String.valueOf(position + 1));
        String title = primaryArrayList.get(0).getKaiwaTitle();
        String content = primaryArrayList.get(0).getKaiwaContent();
        textViewTitle.setText("Title: " + title);
        textViewContent.setText(content);
        addData();
        mediaPlayer = MediaPlayer.create(getContext(), arrayList.get(position));

    }

    private void addData() {
        arrayList = new ArrayList<>();
        arrayList.add(R.raw.k1);
        arrayList.add(R.raw.k2);
        arrayList.add(R.raw.k3);
        arrayList.add(R.raw.k4);
        arrayList.add(R.raw.k5);
        arrayList.add(R.raw.k6);
        arrayList.add(R.raw.k7);
        arrayList.add(R.raw.k8);
        arrayList.add(R.raw.k9);
        arrayList.add(R.raw.k10);
        arrayList.add(R.raw.k11);
        arrayList.add(R.raw.k12);
        arrayList.add(R.raw.k13);
        arrayList.add(R.raw.k14);
        arrayList.add(R.raw.k15);
        arrayList.add(R.raw.k16);
        arrayList.add(R.raw.k17);
        arrayList.add(R.raw.k18);
        arrayList.add(R.raw.k19);
        arrayList.add(R.raw.k20);
        arrayList.add(R.raw.k21);
        arrayList.add(R.raw.k22);
        arrayList.add(R.raw.k23);
        arrayList.add(R.raw.k24);
        arrayList.add(R.raw.k25);
        arrayList.add(R.raw.k26);
        arrayList.add(R.raw.k27);
        arrayList.add(R.raw.k28);
        arrayList.add(R.raw.k29);
        arrayList.add(R.raw.k30);
        arrayList.add(R.raw.k31);
        arrayList.add(R.raw.k32);
        arrayList.add(R.raw.k33);
        arrayList.add(R.raw.k34);
        arrayList.add(R.raw.k35);
        arrayList.add(R.raw.k36);
        arrayList.add(R.raw.k37);
        arrayList.add(R.raw.k38);
        arrayList.add(R.raw.k39);
        arrayList.add(R.raw.k40);
        arrayList.add(R.raw.k41);
        arrayList.add(R.raw.k42);
        arrayList.add(R.raw.k43);
        arrayList.add(R.raw.k44);
        arrayList.add(R.raw.k45);
        arrayList.add(R.raw.k46);
        arrayList.add(R.raw.k47);
        arrayList.add(R.raw.k48);
        arrayList.add(R.raw.k49);
        arrayList.add(R.raw.k50);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_next:
                double thoiGianDB = startTime + 5000;
                mediaPlayer.seekTo((int) thoiGianDB);
                break;
            case R.id.iv_play:

                mediaPlayer.start();
                imageViewPlay.setAlpha((float) 0.1);
                imageViewStop.setAlpha((float) 1.0);
                imageViewPlay.setEnabled(false);
                imageViewStop.setEnabled(true);
                startTime = mediaPlayer.getCurrentPosition();
                finalTime = mediaPlayer.getDuration();
                break;
            case R.id.iv_previous:
                double thoiGianD = startTime - 5000;
                mediaPlayer.seekTo((int) thoiGianD);
                break;
            case R.id.iv_stop:
                mediaPlayer.pause();
                imageViewPlay.setEnabled(true);
                imageViewStop.setEnabled(false);
                imageViewStop.setAlpha((float) 0.1);
                imageViewPlay.setAlpha((float) 1.0);
                break;
        }


    }
}
