package com.example.japaneseapp.models;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;



public class ModelParent extends ExpandableGroup {
    private int anh;

    public ModelParent(String title, int anh, List items) {
        super(title, items);
        this.anh = anh;

    }


    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }
}
