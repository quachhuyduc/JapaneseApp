package com.example.japaneseapp.fragments;


import static com.example.japaneseapp.utils.Constant.POSITION_LESSON;
import static com.example.japaneseapp.utils.Constant.PRIMARY;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.GridView;



import java.util.ArrayList;

import butterknife.BindView;



import androidx.annotation.Nullable;

import com.example.japaneseapp.R;
import com.example.japaneseapp.activities.DetailPrimaryActivity;
import com.example.japaneseapp.adapters.PrimaryAdapter;
import com.example.japaneseapp.base.BaseFragment;
import com.example.japaneseapp.database.DatabaseManager;
import com.example.japaneseapp.models.Primary;


public class PrimaryAlphaFragment extends BaseFragment implements PrimaryAdapter.OnClickItemListener {

    @BindView(R.id.gridviewPrimary1)
    GridView gridView;

    private DatabaseManager databaseManager;
    private PrimaryAdapter primaryAdapter;
    private ArrayList<Primary> primaryArrayList;

    private String primary;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_primary_alpha;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        primary = getArguments().getString(PRIMARY);
        setTitleActionBar(primary);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        databaseManager = new DatabaseManager(getActivity());
        primaryArrayList = new ArrayList<>();
        if (isPrimary1()) {
            primaryArrayList = databaseManager.getListPrimary1();
        } else {
            primaryArrayList = databaseManager.getListPrimary2();
        }
        primaryAdapter = new PrimaryAdapter(primaryArrayList, getContext());
        primaryAdapter.setOnClickItemListener(this);
        gridView.setAdapter(primaryAdapter);
    }

    @Override
    public void onClickItem(int position) {
        position = isPrimary1() ? position : position + 25;
        Intent intent = new Intent(getContext(), DetailPrimaryActivity.class);
        intent.putExtra(POSITION_LESSON, position);
        startActivity(intent);
    }

    private boolean isPrimary1() {
        return primary.equals("Sơ cấp 1");
    }
}
