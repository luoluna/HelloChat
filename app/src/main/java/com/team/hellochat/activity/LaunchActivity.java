package com.team.hellochat.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.base.BaseActivity;
import com.team.hellochat.MainActivity;
import com.team.hellochat.R;
import com.team.hellochat.manager.LogInManager;
import com.team.hellochat.utils.WindowUtil;

public class LaunchActivity extends BaseActivity {

    private final static long TIME = 3000;

    private static final int COUNTDOWN = 0x01;
    private static final int FINISH = 0x03;

    private long countdown = TIME;
    private Activity activity;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COUNTDOWN) {
                if (countdown <= 0) {
                    handler.sendEmptyMessage(FINISH);
                } else {
                    countdown = countdown - 1000;
                    handler.sendEmptyMessageDelayed(COUNTDOWN, 1000);
                }
            } else if (msg.what == FINISH) {
                if (!LogInManager.getInstance().isLogin()) {
                    startActivity(new Intent(activity, LoginActivity.class));
                } else {
                    startActivity(new Intent(activity, MainActivity.class));
                }
                finish();
            }
        }
    };
    private ImageView appIcon;
    private TextView appText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        WindowUtil.fullScreen(this);
        activity = this;

        init();
    }

    private void init() {
        appIcon = findViewById(R.id.app_launch_icon);
        appText = findViewById(R.id.app_launch_tv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.sendEmptyMessage(COUNTDOWN);
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
