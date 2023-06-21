package com.example.japaneseapp.base;

import android.content.Context;
import android.os.Bundle;

import android.view.Gravity;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.japaneseapp.R;
import com.example.japaneseapp.utils.Logger;


import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements ViewTreeObserver.OnGlobalLayoutListener {

    private static final Logger LOGGER = Logger.getLogger(BaseFragment.class);

    protected View rootView;

    public abstract int getLayoutId();

    @Override
    public void onGlobalLayout() {
        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(layoutId, container, false);
            rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
            ButterKnife.bind(this, rootView);
        } catch (InflateException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    public View findViewById(int resId) {
        return rootView.findViewById(resId);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setTitleActionBar(String title) {
        TextView toolBarTitle = (TextView) getActivity().findViewById(R.id.id_toolbar_title);

        toolBarTitle.setText(title);

        toolBarTitle.setVisibility(View.VISIBLE);
    }

    public void updateBackActionbar(String title) {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFragmentBackStack();
                    hideSoftKeyboard();
                }
            });
        }
        setTitleActionBar(title);
    }

    public void updateHomeActionbar(String title) {
        unlockDrawerLayout();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        final DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawlayout);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_toolbar_home);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

        TextView toolBarTitle = (TextView) getActivity().findViewById(R.id.id_toolbar_title);
        toolBarTitle.setText(title);
    }

    public void updateBackToHide(final OnBackOriginScreenListener onBackOriginScreenListener, String title) {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    if (onBackOriginScreenListener != null) {
                        onBackOriginScreenListener.onBackOriginScreen(false);
                    }
                }
            });
        }
        setTitleActionBar(title);
    }

    public void handleBackToHide(OnBackOriginScreenListener onBackOriginScreenListener, boolean requestUpdate) {
        if (onBackOriginScreenListener != null) {
            onBackOriginScreenListener.onBackOriginScreen(requestUpdate);
        }
    }

    public void removeFragmentBackStack() {
        if (getFragmentManager() != null) {
            boolean canBack = getFragmentManager().getBackStackEntryCount() > 0;
            if (canBack) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        }
    }

    public void handleBackPress() {
        if (getView() != null) {
            getView().setFocusableInTouchMode(true);
        }
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    boolean canback = getFragmentManager().getBackStackEntryCount() > 0;
                    if (canback) {
                        FragmentManager manager = getFragmentManager();
                        manager.popBackStack();
                    }
                    return true;
                }
                return false;
            }
        });
    }

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
                    if (onBackOriginScreenListener != null) {
                        onBackOriginScreenListener.onBackOriginScreen(false);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void handleBackPressToHide(final OnBackOriginScreenListener onBackOriginScreenListener, final boolean
            requestUpdate) {
        if (getView() != null) {
            getView().setFocusableInTouchMode(true);
        }
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    if (onBackOriginScreenListener != null) {
                        onBackOriginScreenListener.onBackOriginScreen(requestUpdate);
                    }
                    return true;
                }
                return false;
            }
        });

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    if (onBackOriginScreenListener != null) {
                        onBackOriginScreenListener.onBackOriginScreen(true);
                    }
                }
            });
        }
        lockDrawerLayout();
    }

    public void updateToolbar(String title) {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
        }
        setTitleActionBar(title);
    }

    public void hideToolbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
    }

    public void hideSoftKeyboard() {
        try {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (NullPointerException ignored) {
            LOGGER.error(ignored.getMessage());
        }
    }

    public void lockDrawerLayout() {
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawlayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unlockDrawerLayout() {
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawlayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}
