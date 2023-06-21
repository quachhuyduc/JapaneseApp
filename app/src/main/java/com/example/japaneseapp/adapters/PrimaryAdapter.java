package com.example.japaneseapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;


import com.example.japaneseapp.R;
import com.example.japaneseapp.models.Primary;


import java.util.ArrayList;



public class PrimaryAdapter extends BaseAdapter {
    private ArrayList<Primary> primaryArrayList;
    private Context context;
    private OnClickItemListener onClickItemListener;

    public PrimaryAdapter(ArrayList<Primary> primaryArrayList, Context context) {
        this.primaryArrayList = primaryArrayList;
        this.context = context;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @Override
    public int getCount() {
        return primaryArrayList.size();
    }

    @Override
    public Primary getItem(int position) {
        return primaryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_itemview_lesson);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_primary1, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Bước 2: Đổ dã liệu tương ứng từ mảng
        Primary primary = primaryArrayList.get(position);
        String lesson = "Bài " + primary.getLessonId();
        holder.btnPrimaryLesson.setText(lesson);
        // Lắng nghe sự kiện
        convertView.startAnimation(animation);
        holder.btnPrimaryLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItem(position);
            }
        });

        return convertView;
    }

    public interface OnClickItemListener {
        void onClickItem(int position);
    }

    private static class ViewHolder {
        private Button btnPrimaryLesson;

        ViewHolder(View view) {
            btnPrimaryLesson = (Button) view.findViewById(R.id.bt_primaryLesson);
        }
    }
}