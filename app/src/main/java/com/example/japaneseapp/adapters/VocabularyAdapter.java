package com.example.japaneseapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.japaneseapp.R;
import com.example.japaneseapp.models.Vocabulary;


import java.util.ArrayList;



public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder> {
    private ArrayList<Vocabulary> vocabularies;
    private OnCLickImageSpeakListener onCLickImageSpeakListener;

    public VocabularyAdapter(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

    public void setOnCLickImageSpeakListener(OnCLickImageSpeakListener onCLickImageSpeakListener) {
        this.onCLickImageSpeakListener = onCLickImageSpeakListener;
    }

    @Override
    public VocabularyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vocabulary, parent, false);


        return new VocabularyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VocabularyViewHolder holder, int position) {
        Vocabulary vocabulary = vocabularies.get(position);
        holder.textViewWorld.setText(vocabulary.getWord());
        holder.textViewMeanE.setText(vocabulary.getMeanE());
        holder.textViewMean.setText(vocabulary.getMean());
        holder.imageViewFileMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCLickImageSpeakListener.onCLickImage(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    public interface OnCLickImageSpeakListener {
        void onCLickImage(View view, int position);
    }

    public class VocabularyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewWorld;
        private TextView textViewMeanE, textViewMean;
        private ImageButton imageViewFileMp3;

        public VocabularyViewHolder(View itemView) {
            super(itemView);
            textViewWorld = (TextView) itemView.findViewById(R.id.tv_world);
            textViewMeanE = (TextView) itemView.findViewById(R.id.tv_meanE);
            textViewMean = (TextView) itemView.findViewById(R.id.tv_mean);
            imageViewFileMp3 = (ImageButton) itemView.findViewById(R.id.img_speak);
        }
    }
}
