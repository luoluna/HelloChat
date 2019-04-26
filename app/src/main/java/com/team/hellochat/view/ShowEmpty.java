package com.team.hellochat.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.hellochat.R;

/**
 * Created by Sweven on 2019/4/7.
 * Email:sweventears@Foxmail.com
 */
public class ShowEmpty extends LinearLayout {

    private ImageView iv;
    private TextView tv;
    private AnimationDrawable drawable;

    public ShowEmpty(Context context) {
        super(context);
        init();
    }

    public ShowEmpty(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShowEmpty(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_no_video, this);

        iv = findViewById(R.id.iv_no_video);
        tv = findViewById(R.id.tv_no_video);
    }

    public ShowEmpty load() {
        setText(R.string.loading);
        setImageView(R.drawable.ic_refresh_anim);
        drawable = (AnimationDrawable) iv.getDrawable();
        drawable.start();
        show();
        return this;
    }

    public ShowEmpty loadNoData() {
        setText(R.string.load_fail);
        setImageView(R.drawable.default_this_null_ic);
        return this;
    }

    public ShowEmpty setText(int resId) {
        tv.setText(resId);
        return this;
    }

    public ShowEmpty setText(CharSequence text) {
        tv.setText(text);
        return this;
    }

    public ShowEmpty setImageView(int resId) {
        iv.setImageResource(resId);
        return this;
    }

    public ShowEmpty setImageView(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
        return this;
    }

    public void hidden() {
        setVisibility(GONE);
        if (drawable.isRunning()) {
            drawable.stop();
        }
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public boolean isShown() {
        return super.isShown();
    }
}
