package id.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.recyclerview.R;
import id.recyclerview.holder.LoadMoreViewHolder;
import id.recyclerview.holder.UGCViewHolder;
import id.recyclerview.interfaces.OnLoadMoreListener;
import id.recyclerview.model.UGCHomeResponseModel;

/**
 * Created by Andini on 11/02/2017.
 */

public class ListUGCRecyclerAdapter extends RecyclerView.Adapter {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    List<UGCHomeResponseModel> list;
    OnLoadMoreListener onLoadMoreListener;

    private boolean isLoading;
    private int visibleThreshold = 10;
    private int lastVisibleItem, totalItemCount;
    Context context;

    public ListUGCRecyclerAdapter(List<UGCHomeResponseModel> list) {
        this.list = list;
    }


    public ListUGCRecyclerAdapter(final RecyclerView recyclerView, final List<UGCHomeResponseModel> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UGCHomeResponseModel ugcModel = list.get(position);

        if (holder instanceof UGCViewHolder) {
            UGCViewHolder userViewHolder = (UGCViewHolder) holder;
            userViewHolder.textViewTitle.setText(ugcModel.getTitle());
            userViewHolder.textViewCategory.setText(ugcModel.getDescription());
        } else if (holder instanceof LoadMoreViewHolder) {
            LoadMoreViewHolder loadingViewHolder = (LoadMoreViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        return (position == list.size() - 1 && isLoading) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        //return list.size();
        return list == null ? 0 : list.size();
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

    public void isLoading() {
        isLoading = true;
    }

    public boolean addLoadingView() {
        if (getItemViewType(list.size() - 1) != VIEW_TYPE_LOADING) {
            //add(null);
            return true;
        }
        return false;
    }

    public boolean removeLoadingView() {
        if (list.size() > 1) {
            int loadingViewPosition = list.size() - 1;
            if (getItemViewType(loadingViewPosition) == VIEW_TYPE_LOADING) {
                //remove(loadingViewPosition);
                return true;
            }
        }
        return false;
    }
    
      /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(UGCHomeResponseModel r) {
        list.add(r);
        notifyItemInserted(list.size() - 1);
    }

    public void addAll(List<UGCHomeResponseModel> moveResults) {
        for (UGCHomeResponseModel result : moveResults) {
            add(result);
        }
    }

    public void remove(UGCHomeResponseModel r) {
        int position = list.indexOf(r);
        if (position > -1) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoading = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoading = true;
        add(new UGCHomeResponseModel());
    }

    public void removeLoadingFooter() {
        isLoading = false;

        int position = list.size() - 1;
        UGCHomeResponseModel result = getItem(position);

        if (result != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public UGCHomeResponseModel getItem(int position) {
        return list.get(position);
    }
}
