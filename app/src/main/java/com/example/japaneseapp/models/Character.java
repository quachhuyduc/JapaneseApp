package com.example.japaneseapp.models;



public class Character {
    private String lesssonId;
    private String word;
    private String mean;

    public Character(String lesssonId, String word, String mean) {
        this.lesssonId = lesssonId;
        this.word = word;
        this.mean = mean;
    }

    public String getLesssonId() {

        return lesssonId;
    }

    public void setLesssonId(String lesssonId) {
        this.lesssonId = lesssonId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
