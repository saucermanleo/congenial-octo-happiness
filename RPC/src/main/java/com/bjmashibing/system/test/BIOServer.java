package com.bjmashibing.system.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/20 0020 15:22
 */
public class BIOServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9090, 2);
            serverSocket.setReceiveBufferSize(20);
            while (true) {
                System.in.read();
                Socket client = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = client.getInputStream();
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                            byte[] bytes = new byte[1024];
                            int i;
                            while ((i = bufferedInputStream.read(bytes)) > 0) {
                                System.out.println(new String(bytes, 0, i));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                client.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
