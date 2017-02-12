package id.recyclerview.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.recyclerview.R;
import id.recyclerview.adapter.HorizontalRecyclerAdapter;
import id.recyclerview.interfaces.ClickListener;
import id.recyclerview.interfaces.OnLoadMoreListener;
import id.recyclerview.model.UGCModel;
import id.recyclerview.utils.RecyclerTouchListener;

public class HorizontalScrollViewActivity extends AppCompatActivity {
    List<UGCModel> list = new ArrayList<>();
    HorizontalRecyclerAdapter adapter;
    RecyclerView horizontalRecyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view);

        createData();

        horizontalRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(HorizontalScrollViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(horizontalLayoutManagaer);
        horizontalRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new HorizontalRecyclerAdapter(list, horizontalRecyclerView);
        horizontalRecyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                list.add(null);
                adapter.notifyItemInserted(list.size() - 1);

                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Remove loading item
                        list.remove(list.size() - 1);
                        adapter.notifyItemRemoved(list.size());

                        //Load data
                        int index = list.size();
                        int end = index + 20;

                        for (int i = index + 1; i < end; i++) {
                            UGCModel ugcModel = new UGCModel(i, "Title " + i, "Category " + i);
                            list.add(ugcModel);
                            adapter.notifyItemInserted(list.size());
                        }

                        adapter.setLoaded();
                    }
                }, 1000);
            }
        });
        horizontalRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), horizontalRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                UGCModel ugcModel = list.get(position);
                Toast.makeText(getApplicationContext(), ugcModel.title + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                UGCModel ugcModel = list.get(position);
                Toast.makeText(getApplicationContext(), ugcModel.title + " is selected! Long clicked", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void createData() {
        for (int i = 1; i <= 20; i++) {
            UGCModel ugcModel = new UGCModel(i, "Title " + i, "Category " + i);
            list.add(ugcModel);
        }
    }
}
