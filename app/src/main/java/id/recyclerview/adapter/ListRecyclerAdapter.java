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
 * Created by Andini on 11/02/2017.
 */

public class ListRecyclerAdapter extends RecyclerView.Adapter {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    List<UGCModel> list;
    OnLoadMoreListener onLoadMoreListener;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public ListRecyclerAdapter(List<UGCModel> list) {
        this.list = list;
    }

    public ListRecyclerAdapter(RecyclerView recyclerView, List<UGCModel> list) {
        this.list = list;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UGCModel ugcModel = list.get(position);

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
    public int getItemViewType(int position) {
       /* if (position % 3 == 0) {
            return 1;
        } else if (position % 2 == 0) {
            return 2;
        } else {
            return 3;
        }*/

        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new UGCViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load, parent, false);
            return new LoadMoreViewHolder(view);
        }
        return null;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void load(List list, RecyclerView.Adapter adapter, final int limit) {
        final List<UGCModel> list2 = list;
        final ListRecyclerAdapter adapter2 = (ListRecyclerAdapter) adapter;
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
