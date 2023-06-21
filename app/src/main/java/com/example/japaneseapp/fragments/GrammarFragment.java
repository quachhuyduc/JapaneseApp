package com.example.japaneseapp.fragments;


import static com.example.japaneseapp.utils.Constant.GRAMMAR;
import static com.example.japaneseapp.utils.Constant.N1;
import static com.example.japaneseapp.utils.Constant.N2;
import static com.example.japaneseapp.utils.Constant.N3;
import static com.example.japaneseapp.utils.Constant.N4;
import static com.example.japaneseapp.utils.Constant.N5;

import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;



import java.util.List;

import butterknife.BindView;



import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japaneseapp.R;
import com.example.japaneseapp.adapters.GrammarAdapter;
import com.example.japaneseapp.base.BaseFragment;
import com.example.japaneseapp.base.OnBackOriginScreenListener;
import com.example.japaneseapp.database.DatabaseManager;
import com.example.japaneseapp.models.Grammar;
import com.example.japaneseapp.utils.Constant;
import com.example.japaneseapp.utils.DividerItemDecoration;
import com.example.japaneseapp.utils.Logger;


public class GrammarFragment extends BaseFragment {
    private static final Logger LOGGER = Logger.getLogger(GrammarFragment.class);

    @BindView(R.id.rcvGrammar)
    RecyclerView rcv;

    private DatabaseManager dbManager;
    private GrammarAdapter adapter;
    private List<Grammar> grammarList;
    private DetailGrammarFragment detailGrammarFragment;

    private String grammar;
    private boolean isN1N2;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_grammar;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        grammar = bundle.getString(GRAMMAR);
        LOGGER.info(grammar);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        dbManager = new DatabaseManager(getContext());
        setTitleActionBar(grammar);

        grammarList = dbManager.getGrammarDetail(getSection(grammar));
        LOGGER.info(grammarList.size());

        rcv.setHasFixedSize(true);
        rcv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        rcv.setItemAnimator(new DefaultItemAnimator());
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GrammarAdapter(getContext(), grammarList, isN1N2);

        rcv.setAdapter(adapter);

        adapter.setOnItemGrammarClickListener(new GrammarAdapter.OnItemGrammarClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                handleItemClick(position);
            }
        });
    }

    private void handleItemClick(int position) {
        if (detailGrammarFragment == null) {
            detailGrammarFragment = new DetailGrammarFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_GRAMMAR, grammarList.get(position));
        detailGrammarFragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.container, detailGrammarFragment, detailGrammarFragment.getClass().getSimpleName()).commit();

        detailGrammarFragment.setOnBackOriginScreenListener(new OnBackOriginScreenListener() {
            @Override
            public void onBackOriginScreen(boolean requestUpdate) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.remove(detailGrammarFragment).commit();
                updateHomeActionbar(grammar);
            }
        });
    }

    private String getSection(String grammar) {
        if (grammar.equals("Ngữ pháp N5")) {
            return N5;
        }
        if (grammar.equals("Ngữ pháp N4")) {
            return N4;
        }
        if (grammar.equals("Ngữ pháp N3")) {
            return N3;
        }
        if (grammar.equals("Ngữ pháp N2")) {
            isN1N2 = true;
            return N2;
        }
        if (grammar.equals("Ngữ pháp N1")) {
            isN1N2 = true;
            return N1;
        }
        return "";
    }
}
