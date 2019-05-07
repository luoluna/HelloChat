package com.team.hellochat.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.adapter.CreditAdapter;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.UserManager;

import java.util.Timer;
import java.util.TimerTask;

public class CreditActivity extends BaseActivity implements View.OnClickListener {

    public static final int GOTO_ACCOUNT = 0x01;
    private static final String DEFAULT_CREDIT = "000";

    //top
    private ImageView barBack;
    private TextView barTitle;

    private TextView tvCredit;
    private TextView tvTips;
    private RecyclerView recyclerView;
    private CreditAdapter creditAdapter;

    private User user;
    private Timer timer;

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

        tvCredit.setText(DEFAULT_CREDIT);
        setInfo();
        creditAdapter = new CreditAdapter(this, user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(creditAdapter);

        barBack.setOnClickListener(this);
    }

    private void setInfo() {
        user = UserManager.getInstance().getUser();
        creditNum = 0;
        setCredit();
    }

    int creditNum = 0;

    private void setCredit() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (creditNum <= user.getCreditPoint()) {
                    setCreditText(creditNum);
                    creditNum++;
                } else {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 100, 10);
    }

    private void setCreditText(int credit) {
        String s = credit < 10 ? "00" + credit : (credit < 100 ? "0" + credit : credit) + "";
        runOnUiThread(() -> tvCredit.setText(s));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bar_back) {
            finish();
        }
    }

    @Override
    protected void onStart() {
        setInfo();
        if (creditAdapter != null) {
            creditAdapter.update(user);
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        timer.cancel();
        super.onStop();
    }
}
