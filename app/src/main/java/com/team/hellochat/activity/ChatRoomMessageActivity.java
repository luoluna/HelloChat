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
import com.team.hellochat.app.App;
import com.team.hellochat.bean.ChatMessage;
import com.team.hellochat.bean.ChatRoomItem;
import com.team.hellochat.bean.Friend;
import com.team.hellochat.bean.MessageInfo;
import com.team.hellochat.bean.MessageType;
import com.team.hellochat.manager.AddressBookManager;
import com.team.hellochat.manager.ChatRoomListManager;
import com.team.hellochat.manager.MessageManager;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.ChatMessageRecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TITLE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TYPE;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_FILE;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoomMessageActivity extends BaseActivity implements View.OnClickListener {

    private static final String EMPTY_MESSAGE = "";

    //top
    private ImageView back;
    private TextView title;
    private ImageView more;

    //center
    private ChatMessageRecyclerView messageRecyclerView;
    private ChatMessageAdapter chatMessageAdapter;
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
    private ChatMessage message = new ChatMessage();
    private List<MessageInfo> list = message.getList();
    private boolean isGroup;
    private String file;
    private String topTitle;
    private MessageInfo sendInfo;
    private int withId;


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
            withId = intent.getIntExtra(App.IntentLabel.CHAT_ROOM_WITH_ID, 0);
            putData();
        }

        chatMessageAdapter = new ChatMessageAdapter(this, list);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        messageRecyclerView.setLayoutManager(layoutManager);
        messageRecyclerView.setAdapter(chatMessageAdapter);
        moveToBottom();

        addAdapterCallBack();
//
//        etMessage.addTextChangedListener(new SendMessageTextWatch(etMessage).setSendMessageCallBack(new SendMessageCallBack() {
//            @Override
//            public void onResult(String message, String typeLabel) {
//                setSendInfo(MessageType.getType(typeLabel), message);
//                chatMessageAdapter.sendSelfMessage(sendInfo);
//            }
//        }));
        back.setOnClickListener(this);
        more.setOnClickListener(this);
        ivVoice.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        ivVideo.setOnClickListener(this);
    }

    private void addAdapterCallBack() {
        chatMessageAdapter.setOnSendListener(new ChatMessageAdapter.OnSendListener() {
            @Override
            public void onSuccess(int count, MessageInfo messageInfo) {
                layoutManager.scrollToPositionWithOffset(count - 1, 0);
                etMessage.setText(EMPTY_MESSAGE);
                MessageManager.getInstance().clearMessage();
                MessageManager.getInstance().addMessageInfo(getApplicationContext(), file, messageInfo);
                add2ChatList();
            }

            @Override
            public void onFail() {

            }
        });
    }

    /**
     * 添加该聊天室到聊天室列表
     */
    private void add2ChatList() {
        boolean hasFile = ChatRoomListManager.getInstance().hasFile(file);
        if (!hasFile) {
            ChatRoomItem item = new ChatRoomItem();
            if (!isGroup) {
                List<Friend> friends = AddressBookManager.getInstance().getFriends();
                for (int i = 0; i < friends.size(); i++) {
                    if (friends.get(i).getId() == withId) {
                        item.setIcon(friends.get(i).getAvatar());
                    }
                }
            }
            item.setFile(file);
            item.setTitle(topTitle);
            item.setTop(false);
            item.setGroup(isGroup);
            item.setWithUid(withId);
            ChatRoomListManager.getInstance().addListItem(getApplicationContext(), item);
        }
    }

    /**
     * 设置数据
     */
    private void putData() {

        title.setText(topTitle);
        more.setImageResource(isGroup ? R.drawable.ic_chat_group_more : R.drawable.ic_chat_room_more);

        message = MessageManager.getInstance(this, file).getMessages();
        list = message.getList();
    }

    /**
     *
     */
    private void moveToBottom() {
        if (list.size() > 0) {
            layoutManager.scrollToPositionWithOffset(message.getList().size() - 1, 0);
        }
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
                if (!message.equals(EMPTY_MESSAGE)) {
                    setSendInfo(MessageType.TEXT, message);
                    chatMessageAdapter.sendSelfMessage(sendInfo);
                } else {
                    ToastUtil.showError(this, "消息不能为空");
                }
                break;
            case R.id.message_voice:
                break;
        }
    }

    private void setSendInfo(MessageType type, String message) {
        sendInfo = new MessageInfo();
        sendInfo.setUid(UserManager.getInstance().getUid());
        sendInfo.setInformation(message);
        sendInfo.setType(type);
        sendInfo.setAvatar(UserManager.getInstance().getUser().getAvatar());
        sendInfo.setRead(true);
        sendInfo.setTime(System.currentTimeMillis());
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
