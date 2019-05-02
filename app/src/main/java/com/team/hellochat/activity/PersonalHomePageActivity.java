package com.team.hellochat.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.app.App;
import com.team.hellochat.bean.HeadPicture;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.AddressBookManager;
import com.team.hellochat.manager.UserDatabaseManager;
import com.team.hellochat.utils.ToastUtil;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_ICON;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TITLE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TYPE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_WITH_ID;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_FILE;
import static com.team.hellochat.app.App.IntentLabel.USER_ID;

public class PersonalHomePageActivity extends BaseActivity implements View.OnClickListener {

    //top
    private ImageView barBack;
    private TextView barTitle;

    // main
    private CircleImageView ivHeadPicture;
    private TextView tvName, tvSignature, tvSex, tvAge, tvHobby, tvAddress, tvCreditPoint;
    private int uid;
    private User user;

    //bottom
    private LinearLayout lyStartChat, lyAddFriend;
    private String where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_home_page);

        bindView();
        initData();
    }

    private void bindView() {
        barTitle = findViewById(R.id.bar_title);
        barBack = findViewById(R.id.bar_back);

        ivHeadPicture = findViewById(R.id.iv_head_picture);
        tvName = findViewById(R.id.tv_nickname);
        tvSignature = findViewById(R.id.tv_signature);
        tvSex = findViewById(R.id.tv_sex);
        tvAge = findViewById(R.id.tv_age);
        tvHobby = findViewById(R.id.tv_hobby);
        tvAddress = findViewById(R.id.tv_address);
        tvCreditPoint = findViewById(R.id.tv_credit_point);

        lyStartChat = findViewById(R.id.ly_start_chat);
        lyAddFriend = findViewById(R.id.ly_add_friend);
    }

    private void initData() {
        barTitle.setText(R.string.personal_home_page_title);

        Intent intent = getIntent();
        if (intent != null) {
            uid = intent.getIntExtra(USER_ID, 0);
            where = intent.getStringExtra(App.IntentLabel.ACTIVITY_NAME);
            setInfo();
        }

        barBack.setOnClickListener(this);
        lyAddFriend.setOnClickListener(this);
        lyStartChat.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void setInfo() {
        user = UserDatabaseManager.getInstance().getUserByUid(uid);

        ivHeadPicture.setImageResource(HeadPicture.getResId(user.getAvatar()));
        tvName.setText(user.getNickname());
        tvSignature.setText(user.getSignature());
        tvSex.setText("性别：" + user.getSex().getLabel());
        tvAge.setText("\t年龄：" + user.getAge() + "岁");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < user.getHobby().length; i++) {
            if (user.getHobby()[i] != null) {
                builder.append(user.getHobby()[i]).append("、");
            }
        }
        if (builder.length() > 0) {
            builder.delete(builder.length() - 1, builder.length());
        }
        tvHobby.setText("爱好：" + builder.toString());

        tvAddress.setText("故乡：" + user.getAddress());
        tvCreditPoint.setText("信用点：" + user.getCreditPoint());

        lyAddFriend.setVisibility(AddressBookManager.getInstance().isFriend(user.getId()) ? View.GONE : View.VISIBLE);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ly_add_friend:
                ToastUtil.showShort(this, "已发送添加请求");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AddressBookManager.getInstance().addNewFriend(getApplicationContext(), user.getId());
                    }
                }, 10000);
                break;
            case R.id.ly_start_chat:
                if (where == null) {
                    Intent intent = new Intent(this, ChatRoomMessageActivity.class);
                    intent.putExtra(CHAT_ROOM_TYPE, false);
                    intent.putExtra(MESSAGE_FILE, "");
                    intent.putExtra(CHAT_ROOM_TITLE, user.getNickname());
                    intent.putExtra(CHAT_ROOM_ICON, user.getAvatar());
                    intent.putExtra(CHAT_ROOM_WITH_ID, user.getId());
                    startActivity(intent);
                    finish();
                } else if (where.equals(ChatRoomMessageActivity.class.getName())) {
                    finish();
                }
        }
    }
}
