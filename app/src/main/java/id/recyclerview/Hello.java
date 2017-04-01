package id.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import id.recyclerview.view.CustomRecyclerViewActivity;
import id.recyclerview.view.GridRecyclerViewActivity;
import id.recyclerview.view.HorizontalScrollViewActivity;
import id.recyclerview.view.ListRecyclerViewActivity;

public class Hello extends AppCompatActivity implements View.OnClickListener {
    Button btnList, btnGrid, btnCustom, btnHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList = (Button) findViewById(R.id.btn_list);
        btnGrid = (Button) findViewById(R.id.btn_grid);
        btnCustom = (Button) findViewById(R.id.btn_custom);
        btnHorizontal = (Button) findViewById(R.id.btn_hor);

        btnList.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
        btnCustom.setOnClickListener(this);
        btnHorizontal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnList) {
            startActivity(new Intent(Hello.this, ListRecyclerViewActivity.class));
        } else if (view == btnGrid) {
            startActivity(new Intent(Hello.this, GridRecyclerViewActivity.class));
        } else if (view == btnCustom) {
            startActivity(new Intent(Hello.this, CustomRecyclerViewActivity.class));
        } else {
            startActivity(new Intent(Hello.this, HorizontalScrollViewActivity.class));
        }
    }
}
