package com.team.hellochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.interf.PhoneNumberTextWatcher;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.LoadingDialog;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

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
        title=findViewById(R.id.bar_title);
        ivBack=findViewById(R.id.bar_back);

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
        if (v.getId()==R.id.bar_back){
            finish();
            return;
        }
        loadingDialog.show();
        loadingDialog.setLoadingText(R.string.signIn);
        String user = edUser.getText().toString();
        String phone = edPhone.getText().toString().replace(" ", "");
        String pass = edPass.getText().toString();
        String rePass = edRePass.getText().toString();
        if (user.length() == 0) {
            toast.showShort(R.string.nicknameNotEmpty);
            edUser.findFocus();
            loadingDialog.cancel();
        } else if (!verifyPhone(phone)) {
            toast.showShort(R.string.phoneError);
            edPhone.findFocus();
            loadingDialog.cancel();
        } else if (!verifyPass(pass, rePass)) {
            toast.showShort(R.string.passNotMatch);
            loadingDialog.cancel();
        } else {
            //TODO 联网注册 注册成功保存信息到本地，跳转到登录界面
            toast.showShort("注册成功");
            Intent intent=new Intent();
            intent.putExtra("user",user);
            finish();
            loadingDialog.cancel();
        }
    }

    private boolean verifyPass(String pass, String rePass) {
        return pass.equals(rePass);
    }

    private boolean verifyPhone(String phone) {
        return phone.length() == 11 && phone.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
    }
}
