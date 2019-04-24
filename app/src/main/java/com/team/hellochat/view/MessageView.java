package com.team.hellochat.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.hellochat.R;
import com.team.hellochat.activity.LookMessageActivity;
import com.team.hellochat.bean.MessageType;

import static com.team.hellochat.app.App.IntentLabel.MESSAGE_INFO;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_TYPE;

/**
 * Created by Sweven on 2019/4/24.
 * Email:sweventears@Foxmail.com
 */
public class MessageView extends LinearLayout {

    public static final int WHO_ME = 0x60;
    public static final int WHO_OTHER = 0x61;
    private Context context;

    private RelativeLayout meRl, otherRl;
    private TextView meTv, otherTv;
    private ImageView meIv, otherIv;

    private int who;
    private MessageType type;
    private String message;

    public MessageView(Context context) {
        super(context);
        onCreate();
    }

    public MessageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    public MessageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate();
    }

    public MessageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        onCreate();
    }

    private void onCreate() {
        inflate(getContext(), R.layout.view_message_type, this);

        bindView();
        initData();
    }

    private void bindView() {
        meRl = findViewById(R.id.me_rl);
        otherRl = findViewById(R.id.other_rl);
        meTv = findViewById(R.id.me_tv);
        otherTv = findViewById(R.id.other_tv);
        meIv = findViewById(R.id.me_iv);
        otherIv = findViewById(R.id.other_iv);

    }

    private void initData() {
    }

    public void setMessage(int who, MessageType type, String message) {
        this.who = who;
        this.type = type;
        this.message = message;
        input();
    }

    private void input() {
        if (who == WHO_ME) {
            meRl.setVisibility(VISIBLE);
            otherRl.setVisibility(GONE);
            if (type == MessageType.TEXT) {
                meIv.setVisibility(GONE);
                meTv.setVisibility(VISIBLE);
                meTv.setText(message);
            } else if (type == MessageType.IMAGE) {
                meTv.setVisibility(GONE);
                meIv.setVisibility(VISIBLE);
                Glide.with(context).load(message).into(meIv);
            }
        } else if (who == WHO_OTHER) {
            otherRl.setVisibility(VISIBLE);
            meRl.setVisibility(GONE);
            if (type == MessageType.TEXT) {
                otherIv.setVisibility(GONE);
                otherTv.setVisibility(VISIBLE);
                otherTv.setText(message);
            } else if (type == MessageType.IMAGE) {
                otherTv.setVisibility(GONE);
                otherIv.setVisibility(VISIBLE);
                Glide.with(context).load(message).into(otherIv);
            }
        }
    }

    public int getWho() {
        return who;
    }

    public MessageType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
