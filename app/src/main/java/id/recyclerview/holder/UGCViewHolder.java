/*
 * Copyright (c) Andini Rachmah 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package id.recyclerview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import id.recyclerview.R;

/**
 * Created by Andini on 11/02/2017.
 */

public class UGCViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewTitle, textViewCategory;

    public UGCViewHolder(View view) {
        super(view);
        textViewTitle = (TextView) view.findViewById(R.id.text_view_title);
        textViewCategory = (TextView) view.findViewById(R.id.text_view_category);
    }

    
}