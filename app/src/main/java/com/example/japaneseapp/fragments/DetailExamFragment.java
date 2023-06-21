package com.example.japaneseapp.fragments;


import static com.example.japaneseapp.utils.Constant.DATA_EXAM;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;


import butterknife.BindView;



import androidx.annotation.Nullable;

import com.example.japaneseapp.R;
import com.example.japaneseapp.base.BaseFragment;
import com.example.japaneseapp.base.OnBackOriginScreenListener;


public class DetailExamFragment extends BaseFragment {

    @BindView(R.id.web1)
    WebView webView;

    private OnBackOriginScreenListener onBackOriginScreenListener;

    private int backPress = 0;

    public void setOnBackOriginScreenListener(OnBackOriginScreenListener onBackOriginScreenListener) {
        this.onBackOriginScreenListener = onBackOriginScreenListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail_exam;
    }

    @Override
    public void onResume() {
        super.onResume();
        handleBackPressToHide(onBackOriginScreenListener);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        hideToolbar();
        String url = getArguments().getString(DATA_EXAM);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    if (backPress < 3) {
                        if (webView.canGoBack()) {
                            webView.goBack();
                            backPress++;
                        } else {
                            onBackOriginScreenListener.onBackOriginScreen(false);
                            backPress = 0;
                        }
                    } else {
                        onBackOriginScreenListener.onBackOriginScreen(false);
                        backPress = 0;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void handleBackPressToHide(final OnBackOriginScreenListener onBackOriginScreenListener) {
        if (getView() != null) {
            getView().setFocusableInTouchMode(true);
        }
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        if (onBackOriginScreenListener != null) {
                            onBackOriginScreenListener.onBackOriginScreen(false);
                            backPress = 0;
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
