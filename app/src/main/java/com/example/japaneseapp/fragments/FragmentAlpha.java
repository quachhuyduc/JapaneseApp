package com.example.japaneseapp.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.japaneseapp.R;
import com.example.japaneseapp.adapters.AlphaAdapter;
import com.example.japaneseapp.database.DatabaseManager;
import com.example.japaneseapp.models.Alpha;


import java.util.ArrayList;


public class FragmentAlpha extends Fragment {
    private View view;
    private DatabaseManager databaseManager;
    private ArrayList<Alpha> alphaArrayList;
    private GridView gridView;
    private AlphaAdapter alphaAdapter;


    public FragmentAlpha() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_alpha, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        gridView = (GridView) view.findViewById(R.id.gridview);
        alphaArrayList = new ArrayList<>();

        for(int i = 0; i < 80; i++){
            if(i == 36 && i == 38 && i == 46 && i == 47 && i == 48 && i == 50 && i==51 && i==52 && i==53){
                alphaArrayList.add(new Alpha("","",""));
            }
            alphaArrayList.add(new Alpha("s"+i, "w_"+i, "a"+i+".mp3"));
        }
        alphaAdapter = new AlphaAdapter(alphaArrayList, getActivity());
        gridView.setAdapter(alphaAdapter);
    }

}
