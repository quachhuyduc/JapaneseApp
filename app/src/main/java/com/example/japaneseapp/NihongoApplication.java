package com.example.japaneseapp;

import android.app.Application;

import com.example.japaneseapp.utils.Logger;




public class NihongoApplication extends Application {

    public static final Logger LOGGER = Logger.getLogger(NihongoApplication.class);

    static volatile NihongoApplication instance;

    public static NihongoApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LOGGER.info(instance);
    }
}
