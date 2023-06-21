package com.example.japaneseapp.fragments;

import static com.example.japaneseapp.utils.Constant.IS_FINISH;
import static com.example.japaneseapp.utils.Constant.VOCABULARY;
import static com.example.japaneseapp.utils.Constant.WORD;

import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.japaneseapp.R;
import com.example.japaneseapp.base.BaseFragment;
import com.example.japaneseapp.models.Vocabulary;
import com.example.japaneseapp.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


public class PagerVocabFragment extends BaseFragment implements View.OnClickListener {

    private static final Logger LOGGER = Logger.getLogger(PagerVocabFragment.class);

    @BindView(R.id.tvMean)
    TextView tvMean;

    @BindView(R.id.radA)
    RadioButton radA;

    @BindView(R.id.radB)
    RadioButton radB;

    @BindView(R.id.radC)
    RadioButton radC;

    @BindView(R.id.radD)
    RadioButton radD;

    @BindView(R.id.radGrp)
    RadioGroup radGrp;

    @BindView(R.id.tvResult)
    TextView tvResult;

    @BindView(R.id.btnNext)
    Button btnNext;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    Vocabulary vocabulary;
    List<String> answers;

    private OnNextListener onNextListener;

    private String result;

    private boolean isCorrect;

    public void setOnNextListener(OnNextListener onNextListener) {
        this.onNextListener = onNextListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pager_vocab;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vocabulary = (Vocabulary) getArguments().getSerializable(WORD);
        boolean isFinish = getArguments().getBoolean(IS_FINISH);
        if (isFinish) {
            btnNext.setText("Finish");
        }

        String data = getArguments().getString(VOCABULARY);
        result = vocabulary.getWord();
//
        if (vocabulary != null) {
            tvMean.setText(vocabulary.getMean());
        }

        Type listType = new TypeToken<ArrayList<String>>() {

        }.getType();

        Gson gson = new Gson();
        answers = gson.fromJson(data, listType);

        randomAnswer();

        btnNext.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void randomAnswer() {
        int tmp = Integer.parseInt(vocabulary.getFileMp3());

        switch (tmp % 4) {
            case 1: {
                radA.setText(vocabulary.getWord());
                radB.setText(answers.get(0));
                radC.setText(answers.get(1));
                radD.setText(answers.get(2));
                break;
            }
            case 2: {
                radB.setText(vocabulary.getWord());
                radA.setText(answers.get(0));
                radD.setText(answers.get(1));
                radC.setText(answers.get(2));
                break;
            }
            case 3: {
                radC.setText(vocabulary.getWord());
                radB.setText(answers.get(0));
                radA.setText(answers.get(1));
                radD.setText(answers.get(2));
                break;
            }
            case 0: {
                radD.setText(vocabulary.getWord());
                radB.setText(answers.get(0));
                radC.setText(answers.get(1));
                radA.setText(answers.get(2));
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSubmit) {
            handleActionSubmit();
        }
        if (id == R.id.btnNext) {
            handleActionNext();
        }
    }

    private void handleActionNext() {
        onNextListener.onNext(isCorrect);
    }

    private void handleActionSubmit() {
        btnSubmit.setVisibility(View.GONE);
        btnNext.setVisibility(View.VISIBLE);
        int checkedId = radGrp.getCheckedRadioButtonId();
        tvResult.setVisibility(View.VISIBLE);
        switch (checkedId) {
            case R.id.radA: {
                if (radA.getText().equals(vocabulary.getWord())) {
                    radA.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    radB.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radC.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radD.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    isCorrect = true;
                    tvResult.setText("Đúng rồi!");
                } else {
                    radA.setTextColor(ContextCompat.getColor(getActivity(), R.color.red_600));
                    radB.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radC.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radD.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    isCorrect = false;
                    tvResult.setText("Sai rồi! \n Đáp án là " + result);
                }
                break;
            }
            case R.id.radB: {
                if (radB.getText().equals(vocabulary.getWord())) {
                    radB.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    radA.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radC.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radD.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    isCorrect = true;
                    tvResult.setText("Đúng rồi!");
                } else {
                    radB.setTextColor(ContextCompat.getColor(getActivity(), R.color.red_600));
                    radA.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radC.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radD.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    isCorrect = false;
                    tvResult.setText("Sai rồi! \n Đáp án là " + result);
                }
                break;
            }
            case R.id.radC: {
                if (radC.getText().equals(vocabulary.getWord())) {
                    radC.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    radB.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radA.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radD.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    isCorrect = true;
                    tvResult.setText("Đúng rồi!");
                } else {
                    radC.setTextColor(ContextCompat.getColor(getActivity(), R.color.red_600));
                    radB.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radA.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radD.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    isCorrect = false;
                    tvResult.setText("Sai rồi! \n Đáp án là " + result);
                }
                break;

            }
            case R.id.radD: {
                if (radD.getText().equals(vocabulary.getWord())) {
                    radD.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    radB.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radC.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radA.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    isCorrect = true;
                    tvResult.setText("Đúng rồi!");
                } else {
                    radD.setTextColor(ContextCompat.getColor(getActivity(), R.color.red_600));
                    radB.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radC.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    radA.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    isCorrect = false;
                    tvResult.setText("Sai rồi! \n Đáp án là " + result);
                }
                break;
            }
            default:
                btnNext.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.VISIBLE);
                tvResult.setText("Hãy chọn đáp án. . .");
                return;
        }

        radB.setClickable(false);
        radA.setClickable(false);
        radC.setClickable(false);
        radD.setClickable(false);
    }

    public interface OnNextListener {
        void onNext(boolean isCorrect);
    }
}
