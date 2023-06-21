package com.example.japaneseapp.common;

import android.app.Dialog;
import android.content.Context;

import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.japaneseapp.R;

import butterknife.BindView;



public class CustomDialog extends Dialog {

    @BindView(R.id.tv_detail)
    TextView tvDetail;

    public CustomDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.item_dialog_detail);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }
}
