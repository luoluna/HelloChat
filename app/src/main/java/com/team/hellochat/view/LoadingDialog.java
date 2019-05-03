package com.team.hellochat.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.team.hellochat.R;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class LoadingDialog extends Dialog {

    private TextView tvLoading;

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.NormalDialogStyle);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_loading);

        tvLoading = findViewById(R.id.tv_loading);

        init();
    }

    private void init() {
        setCancelable(true);
    }

    public void hiddenText() {
        tvLoading.setVisibility(View.GONE);
    }

    public void setLoadingText(String text) {
        if (tvLoading != null) {
            tvLoading.setText(text);
        }
    }

    public void setLoadingText(CharSequence text) {
        if (tvLoading != null) {
            tvLoading.setText(text);
        }
    }

    public String getLoadingText() {
        if (tvLoading != null) {
            return tvLoading.getText().toString();
        } else {
            return getContext().getResources().getString(R.string.text_loading);
        }
    }

    public void setLoadingText(int resId) {
        if (tvLoading != null) {
            tvLoading.setText(resId);
        }
    }

    public void show() {
        if (!isShowing()) {
            super.show();
        }
    }

    public void cancel() {
        if (isShowing()) {
            super.cancel();
        }
    }
}
