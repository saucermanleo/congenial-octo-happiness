package com.zy.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost",8899);

        OutputStream outputStream = socket.getOutputStream();

        String message = "hello world\r";
        byte[] bytes = message.getBytes();
        outputStream.write(bytes);
        TimeUnit.SECONDS.sleep(5);
        outputStream.write(bytes);
        socket.close();

    }
}
