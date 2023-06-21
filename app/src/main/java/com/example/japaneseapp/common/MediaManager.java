package com.example.japaneseapp.common;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.util.ArrayList;



public class MediaManager {
    private ArrayList<String> arrayList;
    private Context context;
    public static final int IDE = 1;
    public static final int PLAYING = 2;
    public static final int STOPPED = 3;
    public static final int PAUSED = 4;
    private int mState = IDE;
    private MediaPlayer mediaPlayer;
    private int index = 0;

    public MediaManager(Context context) {
        this.context = context;
        initData();
        mediaPlayer = new MediaPlayer();
    }

    private void initData() {
        arrayList.add("k1.mp3");
        arrayList.add("k2.mp3");
        arrayList.add("k3.mp3");
        arrayList.add("k4.mp3");
        arrayList.add("k5.mp3");
        arrayList.add("k6.mp3");
        arrayList.add("k7.mp3");
        arrayList.add("k8.mp3");
        arrayList.add("k9.mp3");
        arrayList.add("k10.mp3");
        arrayList.add("k11.mp3");
        arrayList.add("k12.mp3");
        arrayList.add("k13.mp3");
        arrayList.add("k14.mp3");
        arrayList.add("k15.mp3");
        arrayList.add("k16.mp3");
        arrayList.add("k17.mp3");
        arrayList.add("k18.mp3");
        arrayList.add("k19.mp3");
        arrayList.add("k20.mp3");
        arrayList.add("k21.mp3");
        arrayList.add("k22.mp3");
        arrayList.add("k23.mp3");
        arrayList.add("k24.mp3");
        arrayList.add("k25.mp3");
        arrayList.add("k26.mp3");
        arrayList.add("k27.mp3");
        arrayList.add("k28.mp3");
        arrayList.add("k29.mp3");
        arrayList.add("k30.mp3");
        arrayList.add("k31.mp3");
        arrayList.add("k32.mp3");
        arrayList.add("k33.mp3");
        arrayList.add("k34.mp3");
        arrayList.add("k35.mp3");
        arrayList.add("k36.mp3");
        arrayList.add("k37.mp3");
        arrayList.add("k38.mp3");
        arrayList.add("k39.mp3");
        arrayList.add("k40.mp3");
        arrayList.add("k41.mp3");
        arrayList.add("k42.mp3");
        arrayList.add("k43.mp3");
        arrayList.add("k44.mp3");
        arrayList.add("k45.mp3");
        arrayList.add("k46.mp3");
        arrayList.add("k47.mp3");
        arrayList.add("k48.mp3");
        arrayList.add("k49.mp3");
        arrayList.add("k50.mp3");
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public boolean play() {
        try {
            if (mState == IDE || mState == STOPPED) {
                String path = arrayList.get(index);
                AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(path);
                mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor()
                        , assetFileDescriptor.getStartOffset()
                        , assetFileDescriptor.getLength());
                mediaPlayer.prepare();
                mediaPlayer.start();
                mState = PLAYING;
                return true;
            } else if (mState == PLAYING) {
                pause();
                return false;
            } else if (mState == PAUSED) {
                mediaPlayer.start();
                mState = PLAYING;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void pause() {
        mediaPlayer.pause();
        mState = PAUSED;
    }

    public void stop() {
        if (mState == PLAYING || mState == PAUSED) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mState = STOPPED;
        }
    }

    public boolean play(int position) {
        stop();
        index = position;
        return play();
    }

}
