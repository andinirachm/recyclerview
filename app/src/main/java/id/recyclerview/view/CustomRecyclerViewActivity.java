package id.recyclerview.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.recyclerview.R;
import id.recyclerview.adapter.CustomRecyclerAdapter;
import id.recyclerview.interfaces.ClickListener;
import id.recyclerview.model.UGCModel;
import id.recyclerview.utils.RecyclerTouchListener;

public class CustomRecyclerViewActivity extends AppCompatActivity {
    List<UGCModel> list = new ArrayList<>();
    CustomRecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recycler_view);

        createData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager manager = new GridLayoutManager(CustomRecyclerViewActivity.this, 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
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
