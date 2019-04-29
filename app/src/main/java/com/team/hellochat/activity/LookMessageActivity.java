package com.team.hellochat.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.bean.MessageType;
import com.team.hellochat.utils.WindowUtil;

import java.util.Timer;
import java.util.TimerTask;

import static com.team.hellochat.app.App.IntentLabel.MESSAGE_INFO;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_TYPE;

public class LookMessageActivity extends BaseActivity implements View.OnTouchListener {

    private RelativeLayout total;
    private ImageView messageInfoIv;
    private TextView messageInfoTv;

    private MessageType type;
    private String message;
    private Timer timer = new Timer();
    private boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_message);

        WindowUtil.fullScreen(this);

        getBundle();
        bindView();
        initData();
    }

    private void getBundle() {
        Intent intent = getIntent();
        if (intent != null) {
            type = MessageType.getType(intent.getStringExtra(MESSAGE_TYPE));
            message = intent.getStringExtra(MESSAGE_INFO);
        }
        if (type == null) {
            type = MessageType.TEXT;
        }
    }

    private void bindView() {
        total = findViewById(R.id.rl_total);
        messageInfoIv = findViewById(R.id.message_info_iv);
        messageInfoTv = findViewById(R.id.message_info_tv);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initData() {
        if (type == MessageType.TEXT) {
            messageInfoIv.setVisibility(View.GONE);
            messageInfoTv.setVisibility(View.VISIBLE);
            messageInfoTv.setText(message);
        } else if (type == MessageType.IMAGE) {
            messageInfoIv.setVisibility(View.VISIBLE);
            messageInfoTv.setVisibility(View.GONE);
            Glide.with(this)
                    .load(message)
                    .into(messageInfoIv);
        }

//        total.setOnTouchListener(this);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            click = true;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    click = false;
                }
            };
            timer.schedule(task, 1000);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (click) {
                timer.cancel();
                finish();
            }
        }
        return false;
    }
}
