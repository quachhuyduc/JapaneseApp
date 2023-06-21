package com.example.japaneseapp.activities;

import android.content.Intent;
import android.os.Bundle;

import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.japaneseapp.R;


public class DetailExamActivity extends AppCompatActivity {
    private TextView textView;
    private WebView webView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_exam);
        initViews();
    }

    private void initViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbarExam1);
        webView = (WebView) findViewById(R.id.web1);
        textView = (TextView) toolbar.findViewById(R.id.tv_hello);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        String titleExam = intent.getStringExtra("title");
        String url = intent.getStringExtra("data");
        textView.setText(titleExam);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);


    }
}
