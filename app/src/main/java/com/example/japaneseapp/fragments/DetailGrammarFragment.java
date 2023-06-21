package com.example.japaneseapp.fragments;


import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.japaneseapp.R;
import com.example.japaneseapp.base.BaseFragment;
import com.example.japaneseapp.base.OnBackOriginScreenListener;
import com.example.japaneseapp.models.Grammar;
import com.example.japaneseapp.utils.Constant;
import com.example.japaneseapp.utils.Logger;


import butterknife.BindView;


public class DetailGrammarFragment extends BaseFragment {

    public static final Logger LOGGER = Logger.getLogger(DetailGrammarFragment.class);

    @BindView(R.id.tvContent)
    TextView tvContent;

    private OnBackOriginScreenListener onBackOriginScreenListener;

    public void setOnBackOriginScreenListener(OnBackOriginScreenListener onBackOriginScreenListener) {
        this.onBackOriginScreenListener = onBackOriginScreenListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail_grammar;
    }

    @Override
    public void onResume() {
        super.onResume();
        handleBackPressToHide(onBackOriginScreenListener, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Grammar grammar = (Grammar) getArguments().getSerializable(Constant.KEY_GRAMMAR);
        if (grammar != null) {
            LOGGER.info(grammar.getContents());
            tvContent.setText(grammar.getContents());
            updateToolbar(grammar.getTitle());
        }
    }
}
