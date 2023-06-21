package com.example.japaneseapp.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.japaneseapp.R;
import com.example.japaneseapp.database.DatabaseManager;
import com.example.japaneseapp.models.Structure;
import com.example.japaneseapp.utils.Constant;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class GrammarLessonFragment extends Fragment {
    @BindView(R.id.recycleview)
    ListView recycleview;
    Unbinder unbinder;
    private DatabaseManager databaseManager;
    private int position;
    private ArrayList<Structure> structures;
    private ArrayAdapter<Structure> arrayAdapter;
    private String content;
    private String test;


    public GrammarLessonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grammar_lesson, container, false);
        unbinder = ButterKnife.bind(this, view);
        structures = new ArrayList<>();
        databaseManager = new DatabaseManager(getActivity());
        Intent intent = getActivity().getIntent();
        position = intent.getIntExtra(Constant.POSITION_LESSON, 0);
        getData();
        return view;
    }

    private void getData() {
        structures = databaseManager.getListStructure(String.valueOf(position + 1));
        ArrayList<String> strings = new ArrayList<>();

        final ArrayList<String> stringsContent = new ArrayList<>();
        for (int i = 0; i < structures.size(); i++) {
            String title = structures.get(i).getNameStructure();
            content = structures.get(i).getContentStructure();
            strings.add(title);
            stringsContent.add(content);
        }
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, strings);
        recycleview.setAdapter(arrayAdapter);
        recycleview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                test = stringsContent.get(position);
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.item_dialog_detail);
                TextView textView = (TextView) dialog.findViewById(R.id.tv_detail);
                textView.setText(test);
                dialog.show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}



