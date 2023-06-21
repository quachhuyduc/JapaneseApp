package com.example.japaneseapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.japaneseapp.models.Character;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japaneseapp.R;
import com.example.japaneseapp.adapters.CharacterAdapter;
import com.example.japaneseapp.database.DatabaseManager;
import com.example.japaneseapp.utils.Constant;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CharacterFragment extends Fragment {


    @BindView(R.id.recycleviewCharacter)
    RecyclerView recycleviewCharacter;
    Unbinder unbinder;

    private CharacterAdapter characterAdapter;
    private ArrayList<Character> characters;
    private DatabaseManager databaseManager;
    private int position;

    public CharacterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character, container, false);
        unbinder = ButterKnife.bind(this, view);
        addData();
        return view;
    }

    private void addData() {
        Intent intent = getActivity().getIntent();
        position = intent.getIntExtra(Constant.POSITION_LESSON, 0);
        characters = new ArrayList<>();
        databaseManager = new DatabaseManager(getActivity());
        characters = databaseManager.getListCharacter(String.valueOf(position + 1));
        recycleviewCharacter.setLayoutManager(new LinearLayoutManager(getActivity()));
        characterAdapter = new CharacterAdapter(characters);
        recycleviewCharacter.setAdapter(characterAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
