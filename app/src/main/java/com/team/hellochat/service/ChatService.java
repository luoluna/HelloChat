package com.team.hellochat.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import com.team.hellochat.app.MessageMap;
import com.team.hellochat.app.Setting;
import com.team.hellochat.bean.MessageInfo;
import com.team.hellochat.bean.MessageType;
import com.team.hellochat.bean.User;
import com.team.hellochat.interf.ChatCallBack;
import com.team.hellochat.manager.MessageManager;
import com.team.hellochat.manager.UserDatabaseManager;
import com.team.hellochat.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class ChatService extends Service {
    private static final String TAG = "ChatService-getMessage";
    private static final int HAS_MESSAGE = 0x01;
    Timer timer = new Timer(true);//定时器
    TimerTask timerTask;
    private MessageInfo info;
    private ChatCallBack chatCallBack;

    public void setChatCallBack(ChatCallBack chatCallBack) {
        this.chatCallBack = chatCallBack;
    }

    public ChatService() {
    }

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HAS_MESSAGE) {
                if (chatCallBack != null) {
                    chatCallBack.postMessage(msg.arg1, ((String) msg.obj));
                }
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (info != null) {
                    MessageManager
                            .getInstance()
                            .addMessageInfo(getApplicationContext(), Setting.getChatRoomFile(info.getUid()), info);
                    MessageManager.getInstance().clearMessage();
                    Message message = new Message();
                    message.obj = info.getInformation();
                    message.arg1 = info.getUid();
                    handler.sendEmptyMessage(HAS_MESSAGE);
                    info = null;
                }
                new LogUtil(TAG).i("死循环定时获取信息");
            }
        };
        timer.schedule(timerTask, 1000, 0);
        return START_STICKY;
    }

    public void sendMessage(String message, int withId) {
        info = new MessageInfo();
        info.setUid(withId);
        User user = UserDatabaseManager.getInstance().getUserByUid(withId);
        info.setNickname(user.getNickname());
        info.setAvatar(user.getAvatar());
        info.setType(MessageType.TEXT);
        info.setInformation(MessageMap.getBack(message));
        info.setTime(System.currentTimeMillis());
        info.setRead(false);
        MessageManager.getInstance().clearMessage();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        while (!timerTask.cancel()) ;
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public ChatService getService() {
            return ChatService.this;
        }
    }
}
