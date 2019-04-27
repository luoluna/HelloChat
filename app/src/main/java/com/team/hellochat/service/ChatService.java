package com.team.hellochat.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import com.team.hellochat.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class ChatService extends Service {
    private static final String TAG = "ChatService-getMessage";
    Timer timer = new Timer(true);//定时器
    TimerTask timerTask;

    public ChatService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                new LogUtil(TAG).i("死循环定时获取信息");
            }
        };
        timer.schedule(timerTask, 1000, 0);
        return START_STICKY;
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
