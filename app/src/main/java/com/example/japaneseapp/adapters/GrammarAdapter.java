package com.example.japaneseapp.adapters;

import android.content.Context;
import android.os.Build;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.japaneseapp.R;
import com.example.japaneseapp.models.Grammar;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.GrammarHolder> {

    private Context context;

    private List<Grammar> grammarList;

    private OnItemGrammarClickListener onItemGrammarClickListener;

    private boolean isN1N2;

    public GrammarAdapter(Context context, List<Grammar> grammarList, boolean isN1N2) {
        this.context = context;
        this.grammarList = grammarList;
        this.isN1N2 = isN1N2;
    }

    public void setOnItemGrammarClickListener(OnItemGrammarClickListener onItemGrammarClickListener) {
        this.onItemGrammarClickListener = onItemGrammarClickListener;
    }

    @Override
    public GrammarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grammar, parent, false);
        return new GrammarHolder(view);
    }

    @Override
    public void onBindViewHolder(final GrammarHolder holder, int position) {
        Grammar grammar = grammarList.get(position);
        if (!isN1N2) {
            int index = grammar.getTitle().lastIndexOf(":");
            String title = grammar.getTitle();
            StringBuilder builder = new StringBuilder(title);
            builder.insert(0, "<b>");
            builder.insert(index, "</b>");

            title = builder.toString();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.tvTitle.setText(Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY));
            } else {
                holder.tvTitle.setText(Html.fromHtml(title));
            }
        } else {
            holder.tvTitle.setText(grammar.getTitle());
        }

        holder.itemLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemGrammarClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return grammarList.size();
    }

    public interface OnItemGrammarClickListener {
        void onItemClick(View view, int position);
    }

    static class GrammarHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_linear)
        LinearLayout itemLinear;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        GrammarHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
