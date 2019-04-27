package com.team.hellochat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.service.autofill.UserData;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.hellochat.BaseActivity;
import com.team.hellochat.R;
import com.team.hellochat.adapter.ChatMessageAdapter;
import com.team.hellochat.app.Setting;
import com.team.hellochat.bean.ChatMessage;
import com.team.hellochat.bean.ChatRoomItem;
import com.team.hellochat.bean.MessageInfo;
import com.team.hellochat.bean.MessageType;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.ChatRoomListManager;
import com.team.hellochat.manager.MessageManager;
import com.team.hellochat.manager.UserDatabaseManager;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.ToastUtil;
import com.team.hellochat.view.ChatMessageRecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_ICON;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TITLE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TYPE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_WITH_ID;
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
    private int icon;


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
            icon = intent.getIntExtra(CHAT_ROOM_ICON, 0);
            withId = intent.getIntExtra(CHAT_ROOM_WITH_ID, 0);
            putData();
            markRead();
        }

        chatMessageAdapter = new ChatMessageAdapter(this, list);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
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

    private void hiddenSoftInput(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void addAdapterCallBack() {
        chatMessageAdapter.setOnSendListener(new ChatMessageAdapter.OnSendListener() {
            @Override
            public void onSuccess(int count, MessageInfo messageInfo) {
                etMessage.setText(EMPTY_MESSAGE);
                MessageManager.getInstance().clearMessage();
                MessageManager.getInstance().addMessageInfo(getApplicationContext(), file, messageInfo);
                add2ChatList();
                layoutManager.scrollToPositionWithOffset(0, 0);
                receiveMessage();
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void receiveMessage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MessageInfo info = new MessageInfo();
                info.setUid(withId);
                User user=UserDatabaseManager.getInstance(getApplicationContext()).getUser(withId);
                info.setNickname(user.getNickname());
                info.setAvatar(user.getAvatar());
                info.setType(MessageType.TEXT);
                info.setInformation("你还有多久到站");
                info.setTime(System.currentTimeMillis());
                info.setRead(false);
                List<MessageInfo> uploads = new ArrayList<>();
                uploads.add(info);

                for (MessageInfo upload : uploads) {
                    MessageManager.getInstance().addMessageInfo(getApplicationContext(), file, upload);
                }
                MessageManager.getInstance().clearMessage();
                chatMessageAdapter.acceptMessage(uploads);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && isActivityTransitionRunning()) {
                    markRead();
                }
            }
        }, 3000);
    }

    /**
     * 设置数据
     */
    private void putData() {

        title.setText(topTitle);
        more.setImageResource(isGroup ? R.drawable.ic_chat_group_more : R.drawable.ic_chat_room_more);

        if (file.equals("")) {
            file = Setting.getChatRoomFile(withId);
        }
        message = MessageManager.getInstance(this, file).getMessages();
        list = message.getList();

    }

    /**
     * 移动到最下面
     */
    private void moveToBottom() {
        if (list.size() > 0) {
            layoutManager.scrollToPositionWithOffset(0, 0);
        }
    }

    /**
     * 添加该聊天室到聊天室列表
     */
    private void add2ChatList() {
        boolean hasFile = ChatRoomListManager.getInstance().hasFile(file);
        if (!hasFile) {
            ChatRoomItem item = new ChatRoomItem();
            item.setIcon(icon);
            item.setFile(file);
            item.setTitle(topTitle);
            item.setTop(false);
            item.setGroup(isGroup);
            item.setWithUid(withId);
            ChatRoomListManager.getInstance().addListItem(getApplicationContext(), item);
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
    public void onClick(@Nullable View v) {
        assert v != null;
        switch (v.getId()) {
            case R.id.bar_back:
                finish();
                break;
            case R.id.bar_more:
                ToastUtil.showShort(this, "查看聊天室详情");
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
                    ToastUtil.showError(this, R.string.send_message_not_allow_null);
                }
                break;
            case R.id.message_voice:
                break;
        }
    }

    private void markRead() {
        message = MessageManager.getInstance(this, file).getMessages();
        for (int i = 0; i < message.getList().size(); i++) {
            message.getList().get(i).setRead(true);
        }
        MessageManager.getInstance().setMessages(this, file, message);
    }

    @Override
    protected void onStart() {
        markRead();
        super.onStart();
    }

    @Override
    protected void onStop() {
        markRead();
        super.onStop();
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
