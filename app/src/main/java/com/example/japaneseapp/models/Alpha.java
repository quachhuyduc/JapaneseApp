package com.example.japaneseapp.models;

import java.io.Serializable;


public class Alpha implements Serializable{
    String alpha;
    String write;
    String voice;

    public Alpha(String alpha, String write, String voice) {
        this.alpha = alpha;
        this.write = write;
        this.voice = voice;
    }

    public Alpha() {
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
