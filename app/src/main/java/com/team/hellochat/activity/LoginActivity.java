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
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.LoadingDialog;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final int START = 0;
    private static final int SUCCESS = 1;
    private static final int FAILS = -1;

    private EditText edLogName;
    private EditText edLogPass;
    private Button btnLogIn;
    private TextView tvForgetPass;
    private TextView tvRegister;
    private LoadingDialog loading;

    private ToastUtil toast;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == START) {
                String name = edLogName.getText().toString();
                String pass = edLogPass.getText().toString();
                if (name.equals("111111") && pass.equals("111111")) {
                    handler.sendEmptyMessage(SUCCESS);
                } else {
                    handler.sendEmptyMessage(FAILS);
                }
            } else if (msg.what == SUCCESS) {
                LogInManager.getInstance().setLog(getApplicationContext(), true);
                UserManager.getInstance().logIn(getApplication(), edLogName.getText().toString(), edLogPass.getText().toString());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            } else if (msg.what == FAILS) {
                toast.showError("用户名或密码错误");
            }
            loading.cancel();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toast = new ToastUtil(this);
        bindView();
        initData();
    }

    private void bindView() {
        edLogName = findViewById(R.id.input_log_name);
        edLogPass = findViewById(R.id.input_password);
        btnLogIn = findViewById(R.id.btn_log_in);
        tvForgetPass = findViewById(R.id.tv_forget_pass);
        tvRegister = findViewById(R.id.tv_register);

        loading = new LoadingDialog(this);
    }

    private void initData() {
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
        return !name.isEmpty() && !pass.isEmpty();
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
                toast.showShort("该功能暂未开放");
//                startActivity(new Intent(this, ForgetPassActivity.class));
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
