package com.example.japaneseapp.models;



public class Structure {
    private String lessonId;
    private String nameStructure;
    private String contentStructure;

    public Structure(String lessonId, String nameStructure, String contentStructure) {
        this.lessonId = lessonId;
        this.nameStructure = nameStructure;
        this.contentStructure = contentStructure;
    }

    public Structure() {
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getNameStructure() {
        return nameStructure;
    }

    public void setNameStructure(String nameStructure) {
        this.nameStructure = nameStructure;
    }

    public String getContentStructure() {
        return contentStructure;
    }

    public void setContentStructure(String contentStructure) {
        this.contentStructure = contentStructure;
    }
}
