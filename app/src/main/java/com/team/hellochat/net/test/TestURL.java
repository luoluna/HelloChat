package com.team.hellochat.net.test;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestURL {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080//chatServer/friendServlet?type=select");
            if (url != null) {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(3000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                //post传递参数
                String params = "f_userID=6&f_friendID";
                OutputStream out = urlConnection.getOutputStream();
                out.write(params.getBytes());
                out.flush();
                out.close();
                int len;
                byte[] b = new byte[1024];
                //获取返回数据
                while ((len = urlConnection.getInputStream().read(b)) != -1)
                    System.out.println(new String(b, 0, len));
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
