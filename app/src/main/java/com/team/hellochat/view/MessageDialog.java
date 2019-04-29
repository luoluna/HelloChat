package com.team.hellochat.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.hellochat.R;
import com.team.hellochat.utils.WindowUtil;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class MessageDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private TextView tvTips, TvConfirm, tvCancel;
    private LinearLayout item;

    private OnClickChooseListener onClickChooseListener;

    private int progress = 0;

    public MessageDialog(@NonNull Context context) {
        super(context, R.style.NormalDialogStyle);
        this.context = context;
        setContentView(R.layout.dialog_normal_customer);
        setCanceledOnTouchOutside(true);
        bindView();
        defaultData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void bindView() {
        tvTips = findViewById(R.id.tips);
        TvConfirm = findViewById(R.id.confirm);
        tvCancel = findViewById(R.id.cancel);
        item=findViewById(R.id.item);
    }

    private void defaultData() {
        tvTips.setText("确定？");
        TvConfirm.setText("是");
        tvCancel.setText("否");
        TvConfirm.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        int width= WindowUtil.getWindowWidth((Activity) context);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) item.getLayoutParams();
        layoutParams.width=width/2;
        item.setLayoutParams(layoutParams);
    }

    public void setTips(String tips) {
        tvTips.setText(tips);
    }

    public void setTips(SpannableStringBuilder tips) {
        tvTips.setText(tips);
    }

    public void setConfirm(String confirm) {
        TvConfirm.setText(confirm);
    }

    public void setCancel(String cancel) {
        tvCancel.setText(cancel);
    }

    public void setOnClickChooseListener(OnClickChooseListener onClickChooseListener) {
        this.onClickChooseListener = onClickChooseListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                if (onClickChooseListener != null) {
                    onClickChooseListener.onConfirm();
                }
                break;
            case R.id.cancel:
                if (onClickChooseListener != null) {
                    onClickChooseListener.onCancel();
                }
                break;
        }
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
