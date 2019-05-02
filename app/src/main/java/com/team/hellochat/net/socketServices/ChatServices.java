package com.team.hellochat.net.socketServices;

import com.team.hellochat.net.service.Impl.ChatGroupInfoServicesImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class ChatServices {
	ServerSocket ss = null;
	//客户端socket集合
	HashMap<String, Socket> allUser = null;

	public ChatServices() {
		try {
			this.ss = new ServerSocket(9090);
			this.allUser = new HashMap<String, Socket>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void statrtService() {
		while (true) {
			System.out.println("等待连接");
			try {
				new Thread(new ServerThread(this.ss.accept())).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("连接成功");
		}
	}

	public static void main(String[] args) {
		new ChatServices().statrtService();
	}
	
	class ServerThread implements Runnable{
		
		private Socket so;
		private DataInputStream dis;
		private DataOutputStream dos;

		public ServerThread(Socket so) {
			super();
			this.so = so;
			try {
				this.dis = new DataInputStream(so.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				allUser.put(dis.readUTF(), so);
				String message = null;
				while (true) {
					message = dis.readUTF();
					System.out.println(message.charAt(11));
					System.out.println(message);
					if(ChatMessage.getMessageType(message)) {
						this.dos = new DataOutputStream(allUser.get(ChatMessage.getRecipient(message)).getOutputStream());
						dos.writeUTF(message);
					}else {
						List<Integer> list = new ChatGroupInfoServicesImpl().usersGroup(Integer.valueOf(ChatMessage.getRecipient(message)));
						for (Integer integer : list) {
							StringBuffer rec = new StringBuffer();
							int num = integer.toString().length();
							for (int i = 0; i < 11-num; i++) {
								rec.append(0);
							}
							rec.append(integer);
							if(!allUser.containsKey(rec.toString()))
								continue;
							this.dos = new DataOutputStream(allUser.get(rec.toString()).getOutputStream());
							dos.writeUTF(message);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
					try {
						if(dos!=null)dos.close();
						if(dis!=null)dis.close();
						if(so!=null)so.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		}
		
	}
}
