package com.example.japaneseapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.japaneseapp.R;

import com.example.japaneseapp.models.ModelChild;
import com.example.japaneseapp.models.ModelParent;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;


public class ExpandleAdapter extends ExpandableRecyclerViewAdapter<ExpandleAdapter.ParentViewHolder, ExpandleAdapter.ChildrenViewHolder> {

    private OnClickChildListener onClickChildListener;

    private Context context;

    public ExpandleAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        this.context = context;
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_menu_item, parent, false);
        return new ParentViewHolder(view);
    }

    public void setOnClickChildListener(OnClickChildListener onClickChildListener) {
        this.onClickChildListener = onClickChildListener;
    }

    @Override
    public ChildrenViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_submenu_item, parent, false);
        return new ChildrenViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(final ChildrenViewHolder holder, final int flatPosition, final ExpandableGroup group, final int childIndex) {
        final ModelChild modelChild = (ModelChild) group.getItems().get(childIndex);
        holder.textView.setText(modelChild.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChildListener.onClickChild(modelChild.getName());
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, final ExpandableGroup group) {
        ModelParent modelParent = (ModelParent) group;
        holder.textViewParent.setText(group.getTitle());
        holder.imageViewAnh.setImageResource(modelParent.getAnh());
    }

    public interface OnClickChildListener {
        void onClickChild(String content);
    }

    public class ChildrenViewHolder extends ChildViewHolder {
        private TextView textView;

        public ChildrenViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_main_nav_submenu_item_title);
        }
    }

    public class ParentViewHolder extends GroupViewHolder {
        private TextView textViewParent;
        private ImageView imageView;
        private ImageView imageViewAnh;

        private RelativeLayout relativeLayout;

        public ParentViewHolder(View itemView) {
            super(itemView);
            textViewParent = (TextView) itemView.findViewById(R.id.nav_menu_item_title);
            imageView = (ImageView) itemView.findViewById(R.id.nav_menu_item_arrow);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative);
            imageViewAnh = (ImageView) itemView.findViewById(R.id.nav_menu_item_icon);
        }

        @Override
        public void expand() {
            RotateAnimation rotate =
                    new RotateAnimation(360, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            imageView.setAnimation(rotate);
        }

        @Override
        public void collapse() {
            RotateAnimation rotate =
                    new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            imageView.setAnimation(rotate);
        }
    }
}
