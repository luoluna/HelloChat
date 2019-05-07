package com.team.hellochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.bean.User;
import com.team.hellochat.interf.PhoneNumberTextWatcher;
import com.team.hellochat.manager.UserDatabaseManager;
import com.team.hellochat.utils.RegMatchUtil;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.LoadingDialog;

import org.jetbrains.annotations.Nullable;

import static com.team.hellochat.app.App.SharedLabel.USER_NAME;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    public static final int FINISH_REGISTER = 0x25;
    //topBar
    private TextView title;
    private ImageView ivBack;

    private EditText edUser, edPhone, edPass, edRePass;
    private TextView tvSureRegister;
    private LoadingDialog loadingDialog;

    private ToastUtil toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toast = new ToastUtil(this);

        bindView();
        initDate();
    }

    private void bindView() {
        title = findViewById(R.id.bar_title);
        ivBack = findViewById(R.id.bar_back);

        edUser = findViewById(R.id.input_user);
        edPhone = findViewById(R.id.input_phone_number);
        edPass = findViewById(R.id.input_password);
        edRePass = findViewById(R.id.input_re_password);

        tvSureRegister = findViewById(R.id.tv_sure_register);

        loadingDialog = new LoadingDialog(this);
    }

    private void initDate() {

        title.setText(R.string.register_text);
        ivBack.setOnClickListener(this);

        edPhone.addTextChangedListener(new PhoneNumberTextWatcher(edPhone));

        tvSureRegister.setOnClickListener(this);
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
        if (v.getId() == R.id.bar_back) {
            finish();
            return;
        }
        loadingDialog.show();
        loadingDialog.setLoadingText(R.string.signIn);
        String username = edUser.getText().toString();
        String phone = edPhone.getText().toString().replace(" ", "");
        String pass = edPass.getText().toString();
        String rePass = edRePass.getText().toString();
        if (username.length() == 0) {
            toast.showShort(R.string.usernameNotEmpty);
        } else if (!verifyPhone(phone)) {
            toast.showShort(R.string.phoneError);
        } else if (!verifyPass(pass)) {
            toast.showShort(R.string.passNotMatch);
        } else if (!verifyPass(pass, rePass)) {
            toast.showShort(R.string.passNotMatch2);
        } else {
            //TODO 联网注册 注册成功保存信息到本地，跳转到登录界面
            User user = new User();
            user.setUser(username);
            user.setPhone(phone);
            user.setPassword(pass);
            if (UserDatabaseManager.getInstance().getUserByUser(username) != null) {
                toast.showShort("用户名已存在");
            } else if (UserDatabaseManager.getInstance().getUserByPhone(phone) != null) {
                toast.showShort("手机号已被注册");
            } else {
                toast.showShort("注册成功");
                UserDatabaseManager.getInstance().registerNewUser(this, user);
                Intent intent = new Intent();
                intent.putExtra(USER_NAME, username);
                setResult(FINISH_REGISTER, intent);
                finish();
            }
        }
        loadingDialog.cancel();
    }

    private boolean verifyPass(String pass) {
        return RegMatchUtil.IsPassword(pass) && pass.length() >= 6;
    }

    private boolean verifyPass(String pass, String rePass) {
        return pass.equals(rePass);
    }

    private boolean verifyPhone(String phone) {
        return phone.length() == 11 && RegMatchUtil.IsPhone(phone);
    }
}
