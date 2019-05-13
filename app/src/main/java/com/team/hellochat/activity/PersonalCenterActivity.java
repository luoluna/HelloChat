package com.team.hellochat.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.bean.HeadPicture;
import com.team.hellochat.bean.Sex;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.RegMatchUtil;
import com.team.hellochat.utils.ToastUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener {

    private static final int OPEN_HEAD_STORE = 0x01;
    //top
    private ImageView barBack;
    private TextView barTitle;

    private RelativeLayout rlHeadPicture, rlUsername, rlNickname, rlSex, rlHobby, rlSignature;
    private CircleImageView headPicture;
    private TextView tvUsername, tvNickname, tvNicknameFinish, tvSex, tvHobby, tvHobbyFinish;
    private EditText edNickname, edHobby, edSignature;

    private User user;
    private boolean amendNick = false, amendHobby = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);

        getData();
        bindView();
        initData();
    }

    private void getData() {
        user = UserManager.getInstance().getUser();
    }

    private void bindView() {
        barTitle = findViewById(R.id.bar_title);
        barBack = findViewById(R.id.bar_back);

        rlHeadPicture = findViewById(R.id.user_head_picture_view);
        rlUsername = findViewById(R.id.user_username_view);
        rlNickname = findViewById(R.id.user_nickname_view);
        rlSex = findViewById(R.id.user_sex_view);
        rlHobby = findViewById(R.id.user_hobby_view);
        rlSignature = findViewById(R.id.user_signature_view);

        headPicture = findViewById(R.id.user_head_picture);
        tvUsername = findViewById(R.id.user_username_tv);
        tvNickname = findViewById(R.id.user_nickname_tv);
        tvNicknameFinish = findViewById(R.id.user_nickname_finish);
        tvSex = findViewById(R.id.user_sex_tv);
        tvHobby = findViewById(R.id.user_hobby_tv);
        tvHobbyFinish = findViewById(R.id.user_hobby_finish);

        edNickname = findViewById(R.id.user_nickname_edit);
        edHobby = findViewById(R.id.user_hobby_edit);
        edSignature = findViewById(R.id.user_signature_edit);
    }

    private void initData() {
        barTitle.setText(R.string.personal_center_activity_title);

        setData();
        edNickname.setVisibility(View.GONE);
        tvNicknameFinish.setVisibility(View.GONE);
        edHobby.setVisibility(View.GONE);
        tvHobbyFinish.setVisibility(View.GONE);

        edHobby.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                String reg = "\\s{2,}";
                if (RegMatchUtil.isHobby(content)) {
                    content = content.replaceAll(reg, " ");
                    edHobby.setText(content);
                    edHobby.focusSearch(edHobby.getText().length());
                }
            }
        });

        rlHeadPicture.setOnClickListener(this);
        rlUsername.setOnClickListener(this);
        rlNickname.setOnClickListener(this);
        rlSex.setOnClickListener(this);
        rlHobby.setOnClickListener(this);
        rlSignature.setOnClickListener(this);

        tvNicknameFinish.setOnClickListener(this);
        tvHobbyFinish.setOnClickListener(this);

        barBack.setOnClickListener(this);
    }

    private void setData() {
        headPicture.setImageResource(HeadPicture.getResId(user.getAvatar()));

        tvUsername.setText(user.getUser());

        tvNickname.setText(user.getNickname());

        tvSex.setText(user.getSex().getLabel());

        setHobby(user.getHobby());

        edSignature.setText(user.getSignature());
    }

    private void setHobby(String[] hobby) {
        StringBuilder builder = new StringBuilder();
        for (String aHobby : hobby) {
            if (aHobby != null) {
                builder.append(aHobby).append("、");
            }
        }
        if (builder.length() > 0) {
            builder.delete(builder.length() - 1, builder.length());
        }
        tvHobby.setText(builder.toString());
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
            case R.id.user_head_picture_view:
                amendHeadPicture();
                break;
            case R.id.user_username_view:
                ToastUtil.showShort(this, "用户名无法修改");
                break;
            case R.id.user_nickname_view:
                if (!amendNick) {
                    edNickname.setVisibility(View.VISIBLE);
                    edNickname.setHint(tvNickname.getText().toString());
                    tvNickname.setVisibility(View.GONE);
                    tvNicknameFinish.setVisibility(View.VISIBLE);
                    amendNick = true;
                }
                break;
            case R.id.user_sex_view:
                amendSex();
                break;
            case R.id.user_hobby_view:
                if (!amendHobby) {
                    edHobby.setVisibility(View.VISIBLE);
                    edHobby.setText("");
                    tvHobby.setVisibility(View.GONE);
                    tvHobbyFinish.setVisibility(View.VISIBLE);
                    amendHobby = true;
                }
                break;
            case R.id.user_signature_view:
                break;
            case R.id.user_nickname_finish:
                hiddenInput();
                tvNickname.setVisibility(View.VISIBLE);
                String nick = edNickname.getText().toString().replace(" ", "");
                if (!nick.equals("")) {
                    tvNickname.setText(edNickname.getText().toString());
                    UserManager.getInstance().getUser().setNickname(nick);
                }
                edNickname.setText("");
                edNickname.setVisibility(View.GONE);
                tvNicknameFinish.setVisibility(View.GONE);
                amendNick = false;
                break;
            case R.id.user_hobby_finish:
                hiddenInput();
                tvHobby.setVisibility(View.VISIBLE);
                String s = edHobby.getText().toString().trim();
                if (!s.equals("")) {
                    String[] hobby = s.split(" ");
                    UserManager.getInstance().getUser().setHobby(hobby);
                    updateUser();
                    setHobby(hobby);
                }
                edHobby.setVisibility(View.GONE);
                tvHobbyFinish.setVisibility(View.GONE);
                amendHobby = false;
                break;
        }
    }

    private void hiddenInput() {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private void amendHeadPicture() {
        String[] s = new String[]{"头像库", "相机", "图库"};
        new AlertDialog.Builder(this)
                .setTitle("更换头像")
                .setItems(s, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), HeadPhotoStoreActivity.class);
                                startActivityForResult(intent, OPEN_HEAD_STORE);
                                break;
                            default:
                                ToastUtil.showShort(getApplicationContext(), "暂不支持，敬请期待~");
                                break;
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    private void amendSex() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] arr = new String[]{Sex.SECRECY.getLabel(), Sex.MAN.getLabel(), Sex.WOMEN.getLabel()};
        builder.setItems(arr, (dialog, which) -> {
            String sex = arr[which];
            UserManager.getInstance().getUser().setSex(Sex.getSex(sex));
            updateUser();
            tvSex.setText(user.getSex().getLabel());
        });
        builder.show();
    }

    private void updateUser() {
        UserManager.getInstance().update(getApplicationContext());
        user = UserManager.getInstance().getUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == OPEN_HEAD_STORE && resultCode == RESULT_OK) {
            user = UserManager.getInstance().getUser();
            headPicture.setImageResource(HeadPicture.getResId(user.getAvatar()));
        }
    }

    @Override
    protected void onStop() {
        UserManager.getInstance().getUser().setSignature(edSignature.getText().toString());
        updateUser();
        super.onStop();
    }
}
