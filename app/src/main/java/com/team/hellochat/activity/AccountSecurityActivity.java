package com.team.hellochat.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.app.CreditRule;
import com.team.hellochat.app.Setting;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.UserDatabaseManager;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.DialogUtil;
import com.team.hellochat.utils.PreferenceUtil;
import com.team.hellochat.utils.RegMatchUtil;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.LoadingDialog;

import static android.view.KeyEvent.ACTION_DOWN;

public class AccountSecurityActivity extends BaseActivity implements View.OnClickListener {

    //top
    private ImageView barBack;
    private TextView barTitle;
    private TextView barDone;

    private TextView tvLabel1, tvLabel2;
    private EditText edTrueName, edIdCard, edEmail;

    private User user;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_security);

        bindView();
        initData();
    }

    private void bindView() {
        barBack = findViewById(R.id.bar_back);
        barTitle = findViewById(R.id.bar_title);
        barDone = findViewById(R.id.bar_done);

        tvLabel1 = findViewById(R.id.label_1);
        tvLabel2 = findViewById(R.id.label_2);
        edTrueName = findViewById(R.id.ed_true_name);
        edIdCard = findViewById(R.id.ed_id_card);
        edEmail = findViewById(R.id.ed_email);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        barTitle.setText(R.string.account_security_activity_title);
        barDone.setText("编辑");
        user = UserManager.getInstance().getUser();
        String name = new PreferenceUtil(this).getString("true_name");
        if (!Setting.isEmpty(name)) {
            edTrueName.setText(name.substring(0, 1) + "*");
        }
        String idCard = user.getIdCard();
        if (!Setting.isEmpty(idCard)) {
            edIdCard.setText(idCard.substring(0, 2) + "***" + idCard.substring(idCard.length() - 2));
        }
        if (!Setting.isEmpty(user.getEmail())) {
            edEmail.setText(user.getEmail());
        }
        setEnable(false);

        barBack.setOnClickListener(this);
        barDone.setOnClickListener(this);
    }

    private void setEnable(boolean enable) {
        String name = new PreferenceUtil(this).getString("true_name");
        edTrueName.setEnabled(Setting.isEmpty(name) && enable);
        edIdCard.setEnabled(Setting.isEmpty(user.getIdCard()) && enable);
        edEmail.setEnabled(enable);
        isEdit = enable;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_back:
                finish();
                break;
            case R.id.bar_done:
                if (barDone.getText().equals("编辑")) {
                    setEnable(true);
                    barDone.setText("完成");
                } else if (barDone.getText().equals("完成")) {
                    final LoadingDialog loadingDialog = new LoadingDialog(this);
                    loadingDialog.show();
                    loadingDialog.hiddenText();

                    String trueName = edTrueName.getText().toString();
                    String idCard = edIdCard.getText().toString();
                    String email = edEmail.getText().toString();
                    boolean isIdCard = RegMatchUtil.IsIDcard(idCard);
                    boolean isEmail = RegMatchUtil.isEmail(email);
                    if (!isIdCard && Setting.isEmpty(user.getIdCard())) {
                        ToastUtil.showError(this, "身份证不正确，请重新填写");
                    } else if (!email.equals("") && !isEmail) {
                        ToastUtil.showError(this, "邮箱格式不正确");
                    } else {
                        user.setEmail(email);
                        user.setIdCard(idCard);
                        setEnable(false);
                        barDone.setText("编辑");
                        new PreferenceUtil(this).save("true_name", trueName);
                        user.setCreditPoint(new CreditRule(user).getCreditPoint());
                        UserManager.getInstance().setUser(this, user);
                    }
                    loadingDialog.cancel();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == ACTION_DOWN) {
            if (isEdit) {
                DialogUtil.ShowTips(this, "还有信息未保存，是否退出？", this::finish);
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
