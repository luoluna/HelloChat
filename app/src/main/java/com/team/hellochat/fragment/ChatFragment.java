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

import com.team.hellochat.R;
import com.team.hellochat.adapter.ChatRoomAdapter;
import com.team.hellochat.bean.ChatRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/3/29.
 * Email:sweventears@Foxmail.com
 */
public class ChatFragment extends Fragment {

    private View fragment;

    private RecyclerView recyclerView;
    private ChatRoomAdapter chatRoomAdapter;
    private List<ChatRoom> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_chat, null);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.appColor));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        initData();
    }

    protected void bindView() {
        recyclerView = fragment.findViewById(R.id.list_chat_rooms);
    }

    protected void initData() {
        ChatRoom room = new ChatRoom(0, "https://pic.sogou.com/d?query=%CD%B7%CF%F1&mode=1&st=191&did=11", "顽皮的小猴", "在吗？", 1, 1554106007);
        list.add(room);
        chatRoomAdapter = new ChatRoomAdapter(getActivity(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chatRoomAdapter);

    }


}
