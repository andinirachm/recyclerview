package id.recyclerview.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.recyclerview.OnScrollListenerExtended;
import id.recyclerview.R;
import id.recyclerview.adapter.ListUGCRecyclerAdapter;
import id.recyclerview.controller.GeneralController;
import id.recyclerview.controller.UGCController;
import id.recyclerview.model.UGCHomeResponseModel;

public class ListRecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListUGCRecyclerAdapter adapter;
    List<UGCHomeResponseModel> list = new ArrayList<>();

    int limit = 10;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recycler_view);

        getFirstData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ListUGCRecyclerAdapter(recyclerView, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnScrollListener(setupScroll(linearLayoutManager));
    }

    private void getFirstData() {
        int index = 0;
        UGCController.getLatestUGC(index, limit, new GeneralController.ConsumeApiListenerArr<UGCHomeResponseModel>() {
            @Override
            public void onReceive(boolean status, String message, List data) {
                if (status) {
                    for (int i = 0; i < data.size(); i++) {
                        UGCHomeResponseModel latestUGCResponseModel = (UGCHomeResponseModel) data.get(i);
                        Log.e("Din", "" + latestUGCResponseModel.getTitle());
                        list.add(latestUGCResponseModel);
                        adapter.notifyItemInserted(list.size());
                    }
                    isLoading = false;
                } else {
                    Log.e(getClass().getSimpleName(), "Failed get data");
                }
            }
        });
    }

    public void load(final int index, final int limit) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UGCController.getLatestUGC(index + 1, limit, new GeneralController.ConsumeApiListenerArr<UGCHomeResponseModel>() {
                    @Override
                    public void onReceive(boolean status, String message, List data) {
                        if (status) {
                            for (int i = 0; i < data.size(); i++) {
                                UGCHomeResponseModel latestUGCResponseModel = (UGCHomeResponseModel) data.get(i);
                                Log.e("RECYCLER UGC : ", i + " - " + latestUGCResponseModel.getTitle());
                                list.add(latestUGCResponseModel);
                                adapter.notifyItemInserted(list.size());
                            }
                            adapter.setLoaded();
                        } else {
                            Log.e(getClass().getSimpleName(), "Failed get data");
                        }
                    }
                });
            }
        }, 3000);

    }

    private OnScrollListenerExtended setupScroll(RecyclerView.LayoutManager layoutManager) {
        return new OnScrollListenerExtended((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                /*adapter.isLoading();
                adapter.addLoadingFooter();
                adapter.addLoadingView();*/
                load(list.size(), 10);
            }
        };
    }
}
