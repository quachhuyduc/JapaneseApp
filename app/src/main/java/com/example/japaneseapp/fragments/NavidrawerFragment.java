package com.example.japaneseapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japaneseapp.R;

import com.example.japaneseapp.adapters.ExpandleAdapter;
import com.example.japaneseapp.base.BaseFragment;
import com.example.japaneseapp.models.ModelChild;
import com.example.japaneseapp.models.ModelParent;

import com.example.japaneseapp.utils.CircleImageView;
import com.example.japaneseapp.utils.ImageLoaderUtils;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class NavidrawerFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.rcvNavi)
    RecyclerView rcvNavi;

    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.btnLogout)
    Button btnLogout;

    private View mContainerView;

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    private OnDrawerListener mDrawerListener;

    private OnItemClickListener onItemClickListener;

    private ExpandleAdapter expandleAdapter;

    private List<ModelParent> modelParents;
    private List<ModelChild> modelChildren;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navidrawer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvNavi.setLayoutManager(new LinearLayoutManager(getContext()));
        addData();
        expandleAdapter = new ExpandleAdapter(modelParents, getContext());

        rcvNavi.setAdapter(expandleAdapter);

        expandleAdapter.setOnClickChildListener(new ExpandleAdapter.OnClickChildListener() {
            @Override
            public void onClickChild(String content) {
                onItemClickListener.onItemClick(content);
                mDrawerLayout.closeDrawers();
            }
        });

      //  updateUI();
        btnLogout.setOnClickListener(this);
    }

    public void setUp(int fragmentId, final DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
                hideSoftKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mContainerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void addData() {
        modelParents = new ArrayList<>();
        modelChildren = new ArrayList<>();

        ModelParent modelParentBaihoc = new ModelParent("Bài học", R.drawable.ic_lesson, Arrays.asList(new ModelChild("Bảng chữ cái")
                , new ModelChild("Sơ cấp 1"), new ModelChild("Sơ cấp 2")));

        ModelParent modelParentNguPhap = new ModelParent("Ngữ pháp tổng hợp", R.drawable.ic_grammar, Arrays.asList(new ModelChild("Ngữ pháp N5")
                , new ModelChild("Ngữ pháp N4"), new ModelChild("Ngữ pháp N3"), new ModelChild("Ngữ pháp N2"), new ModelChild("Ngữ pháp N1")));

        ModelParent modelParentTranning = new ModelParent("Luyện bài", R.drawable.test, Arrays.asList(new ModelChild("Trắc nghiệm")));



        modelParents.add(modelParentBaihoc);
        modelParents.add(modelParentNguPhap);
        modelParents.add(modelParentTranning);

    }

    @Override
    public void onClick(View view) {

    }



    public interface OnItemClickListener {
        void onItemClick(String content);
    }

    public interface OnDrawerListener {
        void onDrawerItemSelected(View view, int position);
    }
}
