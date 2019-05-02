package com.team.hellochat.net.socketServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ImageClient {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\C\\Desktop\\236382.jpg");
		FileInputStream fis = null;
		OutputStream os = null;
		Socket sc = null;

		try {
			InetAddress ser = InetAddress.getByName("localhost");

			sc = new Socket(ser, 8090);

			fis = new FileInputStream(file);

			os = sc.getOutputStream();
			byte[] b = new byte['Ѐ'];

			os.write(new String("09089").getBytes());
			int len = 0;
			while ((len = fis.read(b)) != -1) {
				os.write(b, 0, len);
			}
			sc.shutdownOutput();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
