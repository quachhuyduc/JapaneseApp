package com.example.japaneseapp.models;

import java.io.Serializable;



public class Vocabulary implements Serializable {
    private String fileMp3;
    private String session;
    private String word;
    private String meanE;
    private String mean;

    public Vocabulary(String fileMp3, String session, String word, String meanE, String mean) {
        this.fileMp3 = fileMp3;
        this.session = session;
        this.word = word;
        this.meanE = meanE;
        this.mean = mean;
    }

    public Vocabulary() {
    }

    public String getFileMp3() {
        return fileMp3;
    }

    public void setFileMp3(String fileMp3) {
        this.fileMp3 = fileMp3;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeanE() {
        return meanE;
    }

    public void setMeanE(String meanE) {
        this.meanE = meanE;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
