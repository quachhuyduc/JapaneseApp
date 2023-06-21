package com.example.japaneseapp.fragments;



import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.japaneseapp.R;

import com.example.japaneseapp.base.BaseFragment;
import com.example.japaneseapp.base.OnBackOriginScreenListener;
import com.example.japaneseapp.utils.Constant;


public class ExamFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private Button buttonExam5, buttonExam4, buttonExam3, buttonExam2, buttonExam1;

    private DetailExamFragment detailExamFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_exam;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        initViews();
    }

    private void initViews() {
        buttonExam5 = (Button) view.findViewById(R.id.bt_exam5);
        buttonExam4 = (Button) view.findViewById(R.id.bt_exam4);
        buttonExam3 = (Button) view.findViewById(R.id.bt_exam3);
        buttonExam2 = (Button) view.findViewById(R.id.bt_exam2);
        buttonExam1 = (Button) view.findViewById(R.id.bt_exam1);

        buttonExam5.setOnClickListener(this);

        buttonExam4.setOnClickListener(this);
        buttonExam3.setOnClickListener(this);
        buttonExam2.setOnClickListener(this);
        buttonExam1.setOnClickListener(this);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_itemview_lesson);
        buttonExam5.setAnimation(animation);
        buttonExam4.setAnimation(animation);
        buttonExam3.setAnimation(animation);
        buttonExam2.setAnimation(animation);
        buttonExam1.setAnimation(animation);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Bundle bundle = new Bundle();


        if (id == R.id.bt_exam1) {
            bundle.putString(Constant.DATA_EXAM, "file:///android_asset/load/www/index.html");
        } else if (id == R.id.bt_exam2) {
            bundle.putString(Constant.DATA_EXAM, "file:///android_asset/load2/www2/index.html");
        } else if (id == R.id.bt_exam3) {
            bundle.putString(Constant.DATA_EXAM, "file:///android_asset/load3/www3/index.html");
        } else if (id == R.id.bt_exam4) {
            bundle.putString(Constant.DATA_EXAM, "file:///android_asset/load4/www4/index.html");
        } else {
            bundle.putString(Constant.DATA_EXAM, "file:///android_asset/load5/www5/index.html");
        }
        handleClick(bundle);

    }

    private void handleClick(Bundle bundle) {
        if (detailExamFragment == null) {
            detailExamFragment = new DetailExamFragment();
        }
        detailExamFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.container, detailExamFragment).commit();
        detailExamFragment.setOnBackOriginScreenListener(new OnBackOriginScreenListener() {
            @Override
            public void onBackOriginScreen(boolean requestUpdate) {
                FragmentTransaction tr = getFragmentManager().beginTransaction();
                tr.remove(detailExamFragment).commit();
                updateHomeActionbar("Trắc nghiệm");
            }
        });
    }
}
