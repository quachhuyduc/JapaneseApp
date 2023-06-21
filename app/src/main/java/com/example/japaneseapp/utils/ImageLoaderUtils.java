package com.example.japaneseapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;



public class ImageLoaderUtils {

    public static void show(Context context, String uri, ImageView iv) {
        Glide.with(context)
                .load(uri)
                .crossFade()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);
    }
}
