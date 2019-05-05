package com.team.hellochat.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.UserManager;

public class CreditActivity extends BaseActivity implements View.OnClickListener {

    //top
    private ImageView barBack;
    private TextView barTitle;

    private TextView tvCredit;
    private TextView tvTips;
    private RecyclerView recyclerView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        bindView();
        initData();
    }

    private void bindView() {
        barBack = findViewById(R.id.bar_back);
        barTitle = findViewById(R.id.bar_title);

        tvCredit = findViewById(R.id.tv_credit_point);
        tvTips = findViewById(R.id.tv_tips);
        recyclerView = findViewById(R.id.finish_task_up_credit_list);
    }

    private void initData() {
        barTitle.setText(R.string.credit_activity_title);

        user = UserManager.getInstance().getUser();
        tvCredit.setText(String.valueOf(user.getCreditPoint()));

        barBack.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_back:
                finish();
                break;
        }
    }
}
