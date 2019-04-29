package com.team.hellochat.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.adapter.CollectAdapter;
import com.team.hellochat.bean.Collect;
import com.team.hellochat.manager.CollectManager;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends BaseActivity implements View.OnClickListener {

    //top
    private ImageView ivBack;
    private TextView tvTitle;

    private RecyclerView recyclerView;
    private CollectAdapter collectAdapter;

    private List<Collect> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        bindView();
        initData();
    }

    private void bindView() {
        ivBack = findViewById(R.id.bar_back);
        tvTitle = findViewById(R.id.bar_title);

        recyclerView = findViewById(R.id.collects_list);
    }

    private void initData() {
        tvTitle.setText(getString(R.string.my_collect));

        list = CollectManager.getInstance().getList();
        collectAdapter = new CollectAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(collectAdapter);

        ivBack.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bar_back) {
            finish();
        }
    }
}
