/*
 * Copyright (c) Andini Rachmah 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package id.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.recyclerview.R;
import id.recyclerview.holder.UGCViewHolder;
import id.recyclerview.interfaces.OnLoadMoreListener;
import id.recyclerview.model.UGCModel;

/**
 * Created by Andini on 12/02/2017.
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<UGCViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    List<UGCModel> list;
    OnLoadMoreListener onLoadMoreListener;

    public CustomRecyclerAdapter(List<UGCModel> list) {
        this.list = list;
    }

    @Override
    public UGCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list, parent, false);
                UGCViewHolder rowOne = new UGCViewHolder(itemView);
                return rowOne;
            case 2:
                View itemView2 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list_2, parent, false);
                UGCViewHolder rowTwo = new UGCViewHolder(itemView2);
                return rowTwo;
            case 3:
                View itemView3 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list_3, parent, false);
                UGCViewHolder rowThree = new UGCViewHolder(itemView3);
                return rowThree;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(UGCViewHolder holder, int position) {
        UGCModel ugcModel = list.get(position);
        holder.textViewTitle.setText(ugcModel.title);
        holder.textViewCategory.setText(ugcModel.category);
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return 1;
        } else if (position % 2 == 0) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
