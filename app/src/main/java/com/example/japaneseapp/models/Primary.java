package com.example.japaneseapp.models;



public class Primary {
    private String lessonId;
    private String kaiwaTitle;
    private String kaiwaContent;

    public Primary(String lessonId, String kaiwaTitle, String kaiwaContent) {
        this.lessonId = lessonId;
        this.kaiwaTitle = kaiwaTitle;
        this.kaiwaContent = kaiwaContent;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getKaiwaTitle() {
        return kaiwaTitle;
    }

    public void setKaiwaTitle(String kaiwaTitle) {
        this.kaiwaTitle = kaiwaTitle;
    }

    public String getKaiwaContent() {
        return kaiwaContent;
    }

    public void setKaiwaContent(String kaiwaContent) {
        this.kaiwaContent = kaiwaContent;
    }
}
