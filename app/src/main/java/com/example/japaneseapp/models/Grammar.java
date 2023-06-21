package com.example.japaneseapp.models;

import java.io.Serializable;


public class Grammar implements Serializable {

    private String id;

    private String title;

    private String contents;

    public Grammar() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
