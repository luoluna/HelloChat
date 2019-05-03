package com.team.hellochat.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.app.Setting;
import com.team.hellochat.utils.DialogUtil;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    public static final int LOG_OUT = 0xff1;

    //top
    private ImageView barBack;
    private TextView barTitle;

    //main
    private RelativeLayout rlAccountSecurity, rlNotification, rlClearCache, rlCheckUpdate;
    private TextView tvLogOut, tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bindView();
        initData();
    }

    private void bindView() {
        barTitle = findViewById(R.id.bar_title);
        barBack = findViewById(R.id.bar_back);

        rlAccountSecurity = findViewById(R.id.account_security_item);
        rlNotification = findViewById(R.id.notification_item);
        rlClearCache = findViewById(R.id.clear_cache_item);
        rlCheckUpdate = findViewById(R.id.check_update_item);
        tvLogOut = findViewById(R.id.tv_log_out);
        tvVersion = findViewById(R.id.version_info);
    }

    private void initData() {
        barTitle.setText(R.string.setting_activity_title);

        tvVersion.setText(Setting.getVersionName(this));

        barBack.setOnClickListener(this);
        tvLogOut.setOnClickListener(this);
        rlAccountSecurity.setOnClickListener(this);
        rlNotification.setOnClickListener(this);
        rlClearCache.setOnClickListener(this);
        rlCheckUpdate.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_back:
                finish();
                break;
            case R.id.account_security_item:
                break;
            case R.id.notification_item:
                break;
            case R.id.clear_cache_item:
                DialogUtil.ShowTips(this, "将会清除聊天记录，是否继续？", () -> cleaCache());
                break;
            case R.id.check_update_item:
                break;
            case R.id.tv_log_out:
                logout();

                break;
        }
    }

    private void cleaCache() {

    }

    private void logout() {
        DialogUtil.ShowTips(this, "确定退出？",
                () -> {
                    setResult(LOG_OUT);
                    finish();
                });
    }
}
