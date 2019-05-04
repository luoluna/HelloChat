package com.team.hellochat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.hellochat.R;
import com.team.hellochat.adapter.ChatRoomAdapter;
import com.team.hellochat.bean.ChatMessage;
import com.team.hellochat.bean.ChatRoomItem;
import com.team.hellochat.manager.ChatRoomListManager;
import com.team.hellochat.manager.MessageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/3/29.
 * Email:sweventears@Foxmail.com
 */
public class ChatFragment extends Fragment {

    private View fragment;

    //top
    private TextView title;

    private RecyclerView recyclerView;
    private ChatRoomAdapter chatRoomAdapter;
    private List<ChatRoomItem> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_chat, null);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        initData();
    }

    protected void bindView() {
        title = fragment.findViewById(R.id.bar_title);
        recyclerView = fragment.findViewById(R.id.list_chat_rooms);
    }

    protected void initData() {
        title.setText(getString(R.string.chat_fragment_title));

        getChatRoomList();
        chatRoomAdapter = new ChatRoomAdapter(getActivity(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chatRoomAdapter);


    }

    private void getChatRoomList() {
//        ChatRoomListManager.getInstance().sort();
        List<ChatRoomItem> items = ChatRoomListManager.getInstance().getList();
        list.clear();
        for (int i = 0; i < items.size(); i++) {
            ChatMessage chatMessage = MessageManager.getInstance(getContext(), items.get(i).getFile()).getMessages();
            if (chatMessage.getList().size() > 0) {
                items.get(i).setMessage(chatMessage);
                items.get(i).setRecentMessage(chatMessage.getRecentMessage());
                list.add(items.get(i));
            }
        }
        if (chatRoomAdapter != null) {
            recyclerView.setAdapter(chatRoomAdapter);
        }
    }

    @Override
    public void onStart() {
        getChatRoomList();
        super.onStart();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getChatRoomList();
        }
    }
}
