package com.example.japaneseapp.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.japaneseapp.R;
import com.example.japaneseapp.models.Alpha;


import java.io.IOException;
import java.util.ArrayList;



public class AlphaAdapter extends BaseAdapter {
    private ArrayList<Alpha> alphaArrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public AlphaAdapter(ArrayList<Alpha> alphaArrayList, Context context) {
        this.alphaArrayList = alphaArrayList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return alphaArrayList.size();
    }

    @Override
    public Alpha getItem(int position) {
        return alphaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_alpha, parent, false);
            holder = new ViewHolder();
            holder.imgAlpha = (ImageView) convertView.findViewById(R.id.img_alpha);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Bước 2: Đổ dã liệu tương ứng từ mảng
        Alpha alpha = alphaArrayList.get(position);
        String uri = "drawable/" + alpha.getAlpha();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        holder.imgAlpha.setImageResource(imageResource);

        // Lắng nghe sự kiện


        holder.imgAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 36 && position != 38 && position != 46 && position != 47 && position != 48
                        && position != 50 && position != 51 && position != 52 && position != 53) {
                    Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.item_dialog_alpha);
                    ImageView imgWrite = (ImageView) dialog.findViewById(R.id.imgWrite);
                    Alpha alpha1 = alphaArrayList.get(position);
                    String uri1 = "drawable/" + alpha1.getWrite();
                    int imageResource1 = context.getResources().getIdentifier(uri1, null, context.getPackageName());
                    imgWrite.setImageResource(imageResource1);
                    dialog.show();

                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(alpha1.getVoice());
                        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor()
                                , assetFileDescriptor.getStartOffset()
                                , assetFileDescriptor.getLength());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        return convertView;
    }

    private class ViewHolder {
        private ImageView imgAlpha;

    }
}