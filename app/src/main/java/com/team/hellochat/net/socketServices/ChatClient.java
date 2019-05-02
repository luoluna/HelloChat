package com.team.hellochat.net.socketServices;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.team.hellochat.net.entity.User;

public class ChatClient {
    public void chat(User user) {
        Socket s = null;
        DataOutputStream dos = null;
        try {
            s = new Socket("139.199.5.186", 3306);
            dos = new DataOutputStream(s.getOutputStream());
            //发送id到服务端
            dos.writeUTF(getIdStr(user.getU_ID()));

            //开启线程读取服务端消息
            new Thread(new ClientThread(s)).start();

            //发消息
            for (; ; ) {
                Scanner sc = new Scanner(System.in);
                System.out.println("非0群发");
                int q = Integer.valueOf(sc.nextLine());
                switch (q) {
                    case 0:
                        System.out.println("好友id");
                        int f = Integer.valueOf(sc.nextLine());
                        System.out.println("消息：");
                        String me = sc.nextLine();
                        dos.writeUTF(getIdStr(user.getU_ID()) + "" + q + getIdStr(f) + me);
                        break;

                    default:
                        System.out.println("群号");
                        int f1 = Integer.valueOf(sc.nextLine());
                        System.out.println("消息：");
                        dos.writeUTF(getIdStr(user.getU_ID()) + "" + q + getIdStr(f1) + sc.nextLine());
                        break;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIdStr(int in) {
        StringBuffer rec = new StringBuffer();
        int num = (in + "").length();
        for (int i = 0; i < 11 - num; i++) {
            rec.append(0);
        }
        rec.append(in);
        String id = rec.toString();
        return id;
    }
}

class ClientThread implements Runnable {
    Socket s = null;
    DataInputStream dis = null;

    public ClientThread(Socket s) {
        this.s = s;
        try {
            this.dis = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            for (; ; ) {
                System.out.println(ChatMessage.getMessage(this.dis.readUTF()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

