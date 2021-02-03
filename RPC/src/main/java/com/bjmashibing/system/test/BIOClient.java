package com.bjmashibing.system.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/20 0020 16:07
 */
public class BIOClient {
    public static void main(String[] args) {
        try (Socket socket  = new Socket("222.209.28.186", 12345)) {
            socket.setTcpNoDelay(false);
            socket.setKeepAlive(true);
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String s = bufferedReader.readLine();
                outputStream.write(s.getBytes());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
