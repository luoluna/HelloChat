package com.team.hellochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.adapter.ChatMessageAdapter;
import com.team.hellochat.bean.ChatMessage;
import com.team.hellochat.bean.MessageInfo;
import com.team.hellochat.manager.MessageManager;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.ChatMessageRecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TITLE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TYPE;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_FILE;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoomMessageActivity extends BaseActivity implements View.OnClickListener {
    //top
    private ImageView back;
    private TextView title;
    private ImageView more;

    //center
    private ChatMessageRecyclerView messageRecyclerView;
    private ChatMessageAdapter chatMessageAdapter;
    private ChatMessage message = new ChatMessage();
    private LinearLayoutManager layoutManager;

    //bottom
    private EditText etMessage;
    private ImageView ivVoice;
    private Button btnSend;
    private ImageView ivPhone;
    private ImageView ivVideo;
    private ImageView ivImage;
    private ImageView ivCamera;
    private ImageView ivFaces;

    //data
    private boolean isGroup;
    private String file;
    private String topTitle;
    private List<MessageInfo> list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_message);

        bindView();
        initData();
    }

    private void bindView() {
        back = findViewById(R.id.bar_back);
        title = findViewById(R.id.bar_title);
        more = findViewById(R.id.bar_more);

        messageRecyclerView = findViewById(R.id.list_chat_message);

        etMessage = findViewById(R.id.message_text);
        ivVoice = findViewById(R.id.message_voice);
        btnSend = findViewById(R.id.btn_send_message);

        ivPhone = findViewById(R.id.iv_phone);
        ivVideo = findViewById(R.id.iv_video);
        ivImage = findViewById(R.id.iv_image);
        ivCamera = findViewById(R.id.iv_camera);
        ivFaces = findViewById(R.id.iv_faces);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            isGroup = intent.getBooleanExtra(CHAT_ROOM_TYPE, false);
            file = intent.getStringExtra(MESSAGE_FILE);
            topTitle = intent.getStringExtra(CHAT_ROOM_TITLE);
            putData();
        }

//        List<MessageInfo> list = new ArrayList<>();
//        MessageInfo info = new MessageInfo(3, "ლ(′◉❥◉｀ლ)", "https://avatar.csdn.net/6/2/C/3_qq_37160247.jpg", System.currentTimeMillis() + "", MessageType.TEXT, "你好啊");
//        list.add(info);
//        list.add(info);
//        message.setId(0);
//        message.setContent("");
//        message.setList(list);
        chatMessageAdapter = new ChatMessageAdapter(this, message.getList());
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        messageRecyclerView.setLayoutManager(layoutManager);
        messageRecyclerView.setAdapter(chatMessageAdapter);


        chatMessageAdapter.setOnSendListener(new ChatMessageAdapter.OnSendListener() {
            @Override
            public void onSuccess(int count) {
                layoutManager.scrollToPositionWithOffset(count - 1, 0);
                etMessage.setText("");
            }

            @Override
            public void onFail() {

            }
        });


        back.setOnClickListener(this);
        more.setOnClickListener(this);
        ivVoice.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        ivVideo.setOnClickListener(this);
    }

    private void putData() {

        title.setText(topTitle);
        more.setImageResource(isGroup ? R.drawable.ic_chat_group_more : R.drawable.ic_chat_room_more);

        message = MessageManager.getInstance(this, file).getMessages();
        messageRecyclerView.setAdapter(chatMessageAdapter);
        layoutManager.scrollToPositionWithOffset(message.getList().size() - 1, 0);
    }

    @Override
    public void onClick(@Nullable View v) {
        assert v != null;
        switch (v.getId()) {
            case R.id.bar_back:
                finish();
                break;
            case R.id.bar_more:
                if (isGroup) {
                    //TODO get group information
                } else {
                    //TODO get people information
                }
                break;
            case R.id.btn_send_message:
                String message = etMessage.getText().toString();
                if (!message.equals("")) {
                    chatMessageAdapter.sendSelfMessage(message);
                } else {
                    ToastUtil.showError(this, "消息不能为空");
                }
                break;
            case R.id.message_voice:
                break;
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
}
