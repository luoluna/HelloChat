package com.team.hellochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.MainActivity;
import com.team.hellochat.R;
import com.team.hellochat.adapter.HeadStoreAdapter;
import com.team.hellochat.manager.UserManager;

import org.jetbrains.annotations.Nullable;

public class HeadPhotoStoreActivity extends BaseActivity implements View.OnClickListener {

    //top
    private ImageView barBack;
    private TextView title;


    private RecyclerView recyclerView;
    private HeadStoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_photo_store);

        bindView();
        initData();
    }

    private void bindView() {
        barBack = findViewById(R.id.bar_back);
        title = findViewById(R.id.bar_title);
        recyclerView = findViewById(R.id.head_photo_store_list);
    }

    private void initData() {
        barBack.setOnClickListener(this);
        title.setText("头像库");

        adapter = new HeadStoreAdapter(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnclickItemListener(new HeadStoreAdapter.OnclickItemListener() {
            @Override
            public void onClick(int avatar) {
                setResult(RESULT_OK);
                UserManager.getInstance().setAvatar(getApplicationContext(), avatar);
                finish();
            }
        });
    }

    @Override
    public void onClick(@Nullable View view) {
        if (view.getId() == R.id.bar_back) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
