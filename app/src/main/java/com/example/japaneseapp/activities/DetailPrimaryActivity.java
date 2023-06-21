package com.example.japaneseapp.activities;


import static com.example.japaneseapp.utils.Constant.POSITION_LESSON;

import com.example.japaneseapp.R;
import com.example.japaneseapp.adapters.PagerAdapterLesson;
import com.example.japaneseapp.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;



import butterknife.BindView;



import androidx.viewpager.widget.ViewPager;

public class DetailPrimaryActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpagerDetail)
    ViewPager viewPager;

    PagerAdapterLesson pagerAdapterLesson;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_primary;
    }

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected int actionBarIcon() {
        return R.drawable.ic_back;
    }

    @Override
    protected String actionBarTitle() {
        int pos = getIntent().getIntExtra(POSITION_LESSON, 0) + 1;
        return "Bài " + String.valueOf(pos);
    }

    @Override
    protected void handleToolbarOnClick() {
        finish();
    }

    @Override
    protected void initView() {

        pagerAdapterLesson = new PagerAdapterLesson(getSupportFragmentManager(), 4);
        viewPager.setAdapter(pagerAdapterLesson);
        tabLayout.setupWithViewPager(viewPager);
    }
}
