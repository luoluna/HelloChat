package com.team.hellochat.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.ToastUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener {

    //top
    private ImageView barBack;
    private TextView barTitle;

    private RelativeLayout rlHeadPicture, rlUsername, rlNickname, rlSex, rlHobby, rlSignature;
    private CircleImageView headPicture;
    private TextView tvUsername, tvNickname, tvNicknameFinish, tvSex, tvHobby, tvHobbyFinish;
    private EditText edNickname, edHobby, edSignature;

    private User user;

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
        headPicture.setImageResource(user.getAvatar());

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
            case R.id.user_head_picture_view:
                break;
            case R.id.user_username_view:
                ToastUtil.showShort(this, "用户名无法修改");
                break;
            case R.id.user_nickname_view:
                edNickname.setVisibility(View.VISIBLE);
                edNickname.setText("");
                edNickname.setHint(tvNickname.getText().toString());
                tvNickname.setVisibility(View.GONE);
                tvNicknameFinish.setVisibility(View.GONE);
                break;
            case R.id.user_sex_view:
                amendSex();
                break;
            case R.id.user_hobby_view:
                edHobby.setVisibility(View.VISIBLE);
                edHobby.setText("");
                tvHobby.setVisibility(View.GONE);
                tvHobbyFinish.setVisibility(View.GONE);
                break;
            case R.id.user_signature_view:
                break;
            case R.id.user_nickname_finish:
                break;
            case R.id.user_hobby_finish:
                break;
        }
    }

    private void amendSex() {

    }
}
