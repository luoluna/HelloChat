package com.team.hellochat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.UserDatabaseManager;
import com.team.hellochat.utils.DialogUtil;
import com.team.hellochat.utils.RegMatchUtil;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.utils.WaitUtil;
import com.team.hellochat.view.LoadingDialog;

public class ForgetPassActivity extends BaseActivity implements View.OnClickListener {

    private static final int INPUT_USER = 0x31;
    private static final int VERIFY_USER = 0x32;
    private static final int INPUT_NEW_PASS = 0x33;
    //top
    private ImageView barBack;
    private TextView barTitle;
    private TextView barDone;

    private FrameLayout frameLayout;
    private LinearLayout lyInputUser, lyVerifyUser, lyInputNewPass;
    private TextView tvTips;
    private EditText edUser, edIdCard, edPass, edRePass;

    private static int step = 1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        bindView();
        initData();
    }

    private void bindView() {
        barBack = findViewById(R.id.bar_back);
        barTitle = findViewById(R.id.bar_title);
        barDone = findViewById(R.id.bar_done);

        frameLayout = findViewById(R.id.fragment_view);
        lyInputUser = findViewById(R.id.ly_input_user_name);
        lyVerifyUser = findViewById(R.id.ly_verify_user);
        lyInputNewPass = findViewById(R.id.ly_input_new_pass);

        edUser = findViewById(R.id.ed_user_name);
        tvTips = findViewById(R.id.tv_verify_user_tips);
        edIdCard = findViewById(R.id.ed_id_card);
        edPass = findViewById(R.id.input_new_pass);
        edRePass = findViewById(R.id.input_re_new_pass);
    }

    private void initData() {
        barTitle.setText(R.string.forget_password_activity_title);
        barDone.setText(R.string.next_step);

        showPanel(INPUT_USER);

        lyInputUser.setOnClickListener(this);
        lyVerifyUser.setOnClickListener(this);
        lyInputNewPass.setOnClickListener(this);
        barBack.setOnClickListener(this);
        barDone.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bar_back) {
            finish();
        } else if (v.getId() == R.id.bar_done) {
            final LoadingDialog loadingDialog = new LoadingDialog(this);
            loadingDialog.show();
            loadingDialog.hiddenText();
            new WaitUtil(this, () -> click(loadingDialog));
        }
    }

    private void click(LoadingDialog loadingDialog) {
        if (step == 1 && edUser.length() > 0) {
            user = UserDatabaseManager.getInstance().getUserByUser(edUser.getText().toString());
            if (user == null) {
                DialogUtil.onlyTips(this, "该用户不存在\n请重新输入");
            } else if (user.getIdCard() == null || user.getIdCard().length() == 0) {
                DialogUtil.onlyTips(this, "无验证方式,请联系客服进行处理");
            } else {
                showPanel(VERIFY_USER);
                step++;
            }
        } else if (step == 2 && edIdCard.length() >= 15) {
            if (user.getIdCard().equals(edIdCard.getText().toString())) {
                barDone.setText(R.string.finish_text);
                showPanel(INPUT_NEW_PASS);
                step++;
            } else {
                ToastUtil.showShort(getApplicationContext(), "身份不匹配");
            }
        } else if (step == 3) {
            fixPass(edPass.getText().toString(), edRePass.getText().toString());
        }
        loadingDialog.cancel();
    }

    private void fixPass(String pass, String rePass) {
        //TODO 修改密码
        if (!RegMatchUtil.IsPassword(pass) || pass.length() < 8) {
            ToastUtil.showShort(this, R.string.passNotMatch);
        } else if (pass.equals(rePass)) {
            boolean result = UserDatabaseManager.getInstance().fixPass(pass, edUser.getText().toString());
            if (result) {
                ToastUtil.showShort(this, "密码修改成功");
                finish();
            }
        }
    }

    private void showPanel(int panel) {
        lyInputUser.setVisibility(panel == INPUT_USER ? View.VISIBLE : View.GONE);
        lyVerifyUser.setVisibility(panel == VERIFY_USER ? View.VISIBLE : View.GONE);
        lyInputNewPass.setVisibility(panel == INPUT_NEW_PASS ? View.VISIBLE : View.GONE);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (step == 3) {
                barDone.setText(R.string.next_step);
                showPanel(VERIFY_USER);
                step--;
            } else if (step == 2) {
                showPanel(INPUT_USER);
                step--;
            } else if (step == 1) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        step = 1;
        super.onStart();
    }

    @Override
    protected void onStop() {
        step = 1;
        super.onStop();
    }
}
