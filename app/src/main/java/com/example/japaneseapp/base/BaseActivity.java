package com.example.japaneseapp.base;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.japaneseapp.R;

import butterknife.ButterKnife;



public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        if (hasActionBar()) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            TextView toolBarTitle = (TextView) findViewById(R.id.id_toolbar_title);
            setSupportActionBar(toolbar);
            if (actionBarIcon() != 0) {
                toolbar.setNavigationIcon(actionBarIcon());
            }
            if (actionBarTitle() != null) {
                toolBarTitle.setText(actionBarTitle());
            }

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleToolbarOnClick();
                }
            });
        }

        initView();
    }

    protected abstract int getLayoutId();

    protected abstract boolean hasActionBar();

    protected abstract int actionBarIcon();

    protected abstract String actionBarTitle();

    protected abstract void handleToolbarOnClick();

    protected abstract void initView();
}
