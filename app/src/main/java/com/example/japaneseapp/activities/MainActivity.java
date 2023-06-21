package com.example.japaneseapp.activities;

import static com.example.japaneseapp.utils.Constant.MSG;

import android.os.Bundle;


import com.example.japaneseapp.R;

import com.example.japaneseapp.base.BaseActivity;
import com.example.japaneseapp.fragments.MainFragment;


import androidx.fragment.app.Fragment;


public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    @Override
    protected int actionBarIcon() {
        return R.drawable.ic_toolbar_home;
    }

    @Override
    protected String actionBarTitle() {
        return "NihongoApp";
    }

    @Override
    protected void handleToolbarOnClick() {

    }

    @Override
    protected void initView() {
        Fragment fragment = new MainFragment();
        if (getIntent() != null && getIntent().getAction() != null && getIntent().getAction().equals(MSG)) {
            Bundle bundle = getIntent().getExtras();
            bundle.putString(MSG, "new msg");
            fragment.setArguments(bundle);
        } else {
            fragment.setArguments(getIntent().getExtras());
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .commit();
    }
}

