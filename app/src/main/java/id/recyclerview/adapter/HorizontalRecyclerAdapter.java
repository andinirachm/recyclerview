/*
 * Copyright (c) Andini Rachmah 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package id.recyclerview.adapter;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.recyclerview.R;
import id.recyclerview.holder.LoadMoreViewHolder;
import id.recyclerview.holder.UGCViewHolder;
import id.recyclerview.interfaces.OnLoadMoreListener;
import id.recyclerview.model.UGCModel;

/**
 * Created by Andini on 12/02/2017.
 */

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private List<UGCModel> horizontalList;
    OnLoadMoreListener onLoadMoreListener;

    public HorizontalRecyclerAdapter(List<UGCModel> horizontalList, RecyclerView recyclerView) {
        this.horizontalList = horizontalList;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            });
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
            return new UGCViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_2, parent, false);
            return new LoadMoreViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UGCModel ugcModel = horizontalList.get(position);

        if (holder instanceof UGCViewHolder) {
            UGCViewHolder userViewHolder = (UGCViewHolder) holder;
            userViewHolder.textViewTitle.setText(ugcModel.title);
            userViewHolder.textViewCategory.setText(ugcModel.category);
        } else if (holder instanceof LoadMoreViewHolder) {
            LoadMoreViewHolder loadingViewHolder = (LoadMoreViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return horizontalList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void load(List list, RecyclerView.Adapter adapter, final int limit) {
        final List<UGCModel> list2 = list;
        final HorizontalRecyclerAdapter adapter2 = (HorizontalRecyclerAdapter) adapter;
        Log.e("haint", "Load More");
        list2.add(null);
        adapter.notifyItemInserted(list2.size() - 1);

        //Load more data for reyclerview
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Remove loading item
                list2.remove(list2.size() - 1);
                adapter2.notifyItemRemoved(list2.size());

                //Load data
                int index = list2.size();
                int end = index + limit;

                for (int i = index + 1; i < end; i++) {
                    UGCModel ugcModel = new UGCModel(i, "Title " + i, "Category " + i);
                    list2.add(ugcModel);
                    adapter2.notifyItemInserted(list2.size());
                }

                adapter2.setLoaded();
            }
        }, 1000);
    }
}
