package com.example.japaneseapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.japaneseapp.models.Character;
import com.example.japaneseapp.R;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private ArrayList<Character> characters;


    public CharacterAdapter(ArrayList<Character> items) {
        characters = items;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false)); // TODO
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.tvWord.setText(character.getWord());
        holder.tvMean.setText(character.getMean());

    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.tv_mean)
        TextView tvMean;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
                                