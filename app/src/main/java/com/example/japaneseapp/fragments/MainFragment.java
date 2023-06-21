package com.example.japaneseapp.fragments;


import static com.example.japaneseapp.utils.Constant.GRAMMAR;
import static com.example.japaneseapp.utils.Constant.MSG;
import static com.example.japaneseapp.utils.Constant.PRIMARY;

import android.os.Bundle;

import android.view.Gravity;
import android.view.View;


import butterknife.BindView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.japaneseapp.R;
import com.example.japaneseapp.base.BaseFragment;
import com.example.japaneseapp.utils.Logger;


public class MainFragment extends BaseFragment implements NavidrawerFragment.OnItemClickListener {

    private static final Logger LOGGER = Logger.getLogger(MainFragment.class);

    @BindView(R.id.drawlayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private NavidrawerFragment navidrawerFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        updateUI();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void updateUI() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }

        setTitleActionBar(getString(R.string.app_name));
        showFragment(new FragmentAlpha());

        navidrawerFragment = (NavidrawerFragment) getChildFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navidrawerFragment.setUp(R.id.fragment_navigation_drawer, drawerLayout, toolbar);
        navidrawerFragment.setOnItemClickListener(this);
   //     handleClickNotification();
    }

    @Override
    public void onItemClick(String content) {
        LOGGER.info(content);
        Bundle bundle = new Bundle();


        if (content.equals("Bảng chữ cái")) {
            showFragment(new FragmentAlpha());
            setTitleActionBar("Bảng chữ cái");
            return;
        }
        if (content.equals("Sơ cấp 1") || content.equals("Sơ cấp 2")) {
            bundle.putSerializable(PRIMARY, content);
            switchFragment(new PrimaryAlphaFragment(), bundle);
            return;
        }
        if (content.equals("Trắc nghiệm")) {
            showFragment(new ExamFragment());
            setTitleActionBar("Trắc nghiệm");

            return;
        }

        if (content.equals("Hỏi đáp")) {
            showFragment(new FragmentQuestion());
            return;
        }
        bundle.putString(GRAMMAR, content);
        switchFragment(new GrammarFragment(), bundle);
    }

    public void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void switchFragment(Fragment fragment, Bundle bundle) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
