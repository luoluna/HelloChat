package com.team.hellochat.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.base.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.utils.DialogUtil;

public class HelpFeedbackActivity extends BaseActivity implements View.OnClickListener {

    // top
    private ImageView barBack;
    private TextView barTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_feedback);

        bindView();
        initData();
    }

    private void bindView() {
        barBack = findViewById(R.id.bar_back);
        barTitle = findViewById(R.id.bar_title);
    }

    private void initData() {
        barTitle.setText(R.string.help_feedback_activity_title);

        DialogUtil.onlyTips(this, "暂无数据",false);

        barBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.ACTION_DOWN){
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
