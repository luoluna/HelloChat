package com.team.hellochat.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.MainActivity;
import com.team.hellochat.R;
import com.team.hellochat.utils.WindowUtil;

public class LaunchActivity extends BaseActivity {

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            countdown = countdown - 1000;
            appText.setText(countdown / 1000 + "");
            if (countdown > 0) {
                handler.postDelayed(this, 1000);
            } else {
                startActivity(new Intent(activity, MainActivity.class));
                finish();
            }
        }
    };

    private final static long TIME = 3000;
    private long countdown = TIME;

    private Activity activity;

    private ImageView appIcon;
    private TextView appText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        WindowUtil.fullScreen(this);
        activity = this;

        bindView();
        initData();
    }

    private void bindView() {
        appIcon = findViewById(R.id.app_launch_icon);
        appText = findViewById(R.id.app_launch_tv);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        appText.setText(countdown / 1000 + "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
