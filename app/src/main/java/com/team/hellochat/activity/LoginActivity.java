package com.team.hellochat.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.MainActivity;
import com.team.hellochat.R;
import com.team.hellochat.manager.LogInManager;
import com.team.hellochat.manager.UserDatabaseManager;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.RegMatchUtil;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.LoadingDialog;

import org.jetbrains.annotations.Nullable;

import static com.team.hellochat.activity.RegisterActivity.FINISH_REGISTER;
import static com.team.hellochat.app.App.SharedLabel.USER_NAME;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final int PHONE = 0x21;
    public static final int EMAIL = 0x22;
    public static final int USER = 0x23;
    private static final int START = 0;
    private static final int SUCCESS = 1;
    private static final int FAILS = -1;
    private static final int GO_TO_REGISTER = 0x24;

    //top
    private TextView barTitle;

    private EditText edLogName;
    private EditText edLogPass;
    private Button btnLogIn;
    private TextView tvForgetPass;
    private TextView tvRegister;
    private LoadingDialog loading;

    private int logInAccount;

    private ToastUtil toast;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == START) {
                verifyPass();
            } else if (msg.what == SUCCESS) {
                UserManager.getInstance().logIn(getApplication(), logInAccount, edLogName.getText().toString(), edLogPass.getText().toString());
                LogInManager.getInstance().setLog(getApplicationContext(), true);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            } else if (msg.what == FAILS) {
                toast.showError("用户名不存在或密码错误");
            }
            loading.cancel();
        }
    };

    private void verifyPass() {
        String name = edLogName.getText().toString();
        String pass = edLogPass.getText().toString();

        String correctPass;
        if (logInAccount == PHONE) {
            correctPass = UserDatabaseManager.getInstance().getPassByPhone(name);
        } else if (logInAccount == EMAIL) {
            correctPass = UserDatabaseManager.getInstance().getPassByEmail(name);
        } else {
            correctPass = UserDatabaseManager.getInstance().getPassByUser(name);
        }
        if (correctPass == null) {
            handler.sendEmptyMessage(FAILS);
        } else {
            if (correctPass.equals(pass)) {
                handler.sendEmptyMessage(SUCCESS);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toast = new ToastUtil(this);
        bindView();
        initData();
    }

    private void bindView() {
        barTitle = findViewById(R.id.bar_title);

        edLogName = findViewById(R.id.input_log_name);
        edLogPass = findViewById(R.id.input_password);
        btnLogIn = findViewById(R.id.btn_log_in);
        tvForgetPass = findViewById(R.id.tv_forget_pass);
        tvRegister = findViewById(R.id.tv_register);

        loading = new LoadingDialog(this);
    }

    private void initData() {
        barTitle.setText(R.string.login_activity_title);

        if (LogInManager.getInstance().isLogin()) {
            UserManager manager = UserManager.getInstance();
            edLogName.setText(manager.getLoginName());
            edLogPass.setText(manager.getPassword());
        }

        btnLogIn.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgetPass.setOnClickListener(this);
    }

    private void logIn() {
        if (isNotEmpty()) {
            loading.show();
            loading.setLoadingText("登录中……");
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(START);
                }
            };
            new Handler().postDelayed(runnable, 1000);
        }

    }

    private boolean isNotEmpty() {
        String name = edLogName.getText().toString();
        String pass = edLogPass.getText().toString();
        name = name.replace(" ", "");
        verifyLogInAccountType(name);
        return !name.isEmpty() && !pass.isEmpty();
    }

    private void verifyLogInAccountType(String name) {
        if (RegMatchUtil.IsPhone(name)) {
            logInAccount = PHONE;
        } else if (RegMatchUtil.isEmail(name)) {
            logInAccount = EMAIL;
        } else {
            logInAccount = USER;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GO_TO_REGISTER && resultCode == FINISH_REGISTER) {
            if (data != null) {
                edLogName.setText(data.getStringExtra(USER_NAME));
                edLogPass.setText("");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_log_in:
                logIn();
                break;
            case R.id.tv_forget_pass:
                startActivity(new Intent(this, ForgetPassActivity.class));
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, GO_TO_REGISTER);
                break;
        }
    }
}
