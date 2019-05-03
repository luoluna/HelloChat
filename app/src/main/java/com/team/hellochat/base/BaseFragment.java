package com.team.hellochat.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.hellochat.service.AddNewFriendService;
import com.team.hellochat.service.ChatService;
import com.team.hellochat.utils.LogUtil;
import com.team.hellochat.utils.ToastUtil;

/**
 * Created by Sweven on 2019/3/30.
 * Email:sweventears@Foxmail.com
 */
public class BaseFragment extends Fragment {

    protected Activity activity;
    protected ToastUtil toast;
    protected LogUtil log;

    private ServiceConnection chatSC, addNewFriendSC;
    private ChatService chatService;
    private AddNewFriendService addNewFriendService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        toast = new ToastUtil(activity);
        log = new LogUtil(this.getClass().getName());

        chatSC = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                chatService=((ChatService.MyBinder)service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        addNewFriendSC = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                addNewFriendService=((AddNewFriendService.MyBinder)service).getService();

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void bindView() {
    }

    protected void initData() {
    }

    @Override
    public void onStart() {
        activity.bindService(new Intent(activity, ChatService.class), chatSC, 0);
        activity.bindService(new Intent(activity, AddNewFriendService.class), addNewFriendSC, 0);
        super.onStart();
    }
}
