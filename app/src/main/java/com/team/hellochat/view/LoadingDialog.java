package com.team.hellochat.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.TextView;

import com.team.hellochat.R;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class LoadingDialog extends Dialog {

    private TextView tvLoading;

    public LoadingDialog(@NonNull Context context) {
        super(context);
//        this(context, R.style.loading);
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
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = -1;
        params.width = -1;
        params.format = 1;
        params.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        getWindow().setAttributes(params);
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

    public void setLoadingText(int resId) {
        if (tvLoading != null) {
            tvLoading.setText(resId);
        }
    }

    public String getLoadingText() {
        if (tvLoading != null) {
            return tvLoading.getText().toString();
        } else {
            return getContext().getResources().getString(R.string.text_loading);
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
