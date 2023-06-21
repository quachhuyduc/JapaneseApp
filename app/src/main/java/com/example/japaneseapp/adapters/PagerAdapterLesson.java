package com.example.japaneseapp.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.japaneseapp.fragments.CharacterFragment;
import com.example.japaneseapp.fragments.GrammarLessonFragment;
import com.example.japaneseapp.fragments.TalkingFragment;
import com.example.japaneseapp.fragments.Vocabulary1Fragment;



public class PagerAdapterLesson extends FragmentStatePagerAdapter {
    private int tabCount;

    public PagerAdapterLesson(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Vocabulary1Fragment();
            case 1:
                return new GrammarLessonFragment();
            case 2:
                return new TalkingFragment();

            case 3:
                return new CharacterFragment();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Từ Vựng";
            case 1:
                return "Ngữ Pháp";
            case 2:
                return "Hội Thoại";
            case 3:
                return "Hán Tự";
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
