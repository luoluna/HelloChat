package com.team.hellochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team.hellochat.base.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.app.Setting;
import com.team.hellochat.bean.ChatRoomItem;
import com.team.hellochat.manager.AddressBookManager;
import com.team.hellochat.manager.ChatRoomListManager;
import com.team.hellochat.manager.CollectManager;
import com.team.hellochat.utils.DialogUtil;
import com.team.hellochat.utils.PreferenceUtil;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.LoadingDialog;

import java.util.List;

import static com.team.hellochat.app.App.SharedLabel.ADDRESS_BOOK;
import static com.team.hellochat.app.App.SharedLabel.CHAT_ROOM_LIST;
import static com.team.hellochat.app.App.SharedLabel.COLLECTS;
import static com.team.hellochat.app.App.SharedLabel.MESSAGE_INFO;

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
                startActivity(new Intent(this, AccountSecurityActivity.class));
                break;
            case R.id.notification_item:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.clear_cache_item:
                DialogUtil.ShowTips(this, "将会清除聊天记录，是否继续？", this::cleaCache);
                break;
            case R.id.check_update_item:
                checkUpdate();
                break;
            case R.id.tv_log_out:
                logout();

                break;
        }
    }

    private void checkUpdate() {
        int currentCode = Setting.getVersionCode(this);
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
        loadingDialog.setLoadingText("检查更新中……");
        new Handler().postDelayed(() -> {
            ToastUtil.showShort(getApplicationContext(), "当前版本已是最新版本！");
            loadingDialog.cancel();
        }, 2000);
    }

    private void cleaCache() {
        ChatRoomListManager manager = ChatRoomListManager.getInstance();
        List<ChatRoomItem> items = manager.getList();
        for (int i = 0; i < items.size(); i++) {
            new PreferenceUtil(this, items.get(i).getFile()).remove(new String[]{MESSAGE_INFO});
        }
        new PreferenceUtil(this).remove(new String[]{CHAT_ROOM_LIST});
        ToastUtil.showShort(this, "清除成功");
    }

    private void logout() {
        DialogUtil.ShowTips(this, "确定退出？",
                () -> {
                    setResult(LOG_OUT);
                    finish();
                    ChatRoomListManager manager = ChatRoomListManager.getInstance();
                    List<ChatRoomItem> items = manager.getList();
                    for (int i = 0; i < items.size(); i++) {
                        new PreferenceUtil(this, items.get(i).getFile()).remove(new String[]{MESSAGE_INFO});
                    }
                    new PreferenceUtil(this).remove(new String[]{CHAT_ROOM_LIST, ADDRESS_BOOK, COLLECTS});
                    AddressBookManager.getInstance(this);
                    ChatRoomListManager.getInstance(this);
                    CollectManager.getInstance(this);
                });
    }
}
