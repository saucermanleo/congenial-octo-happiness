package com.zy.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while(true){
            Socket socket  = serverSocket.accept();
            executorService.submit(()->{
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedInputStream =new BufferedReader( new InputStreamReader(inputStream));
                    String line;
                    while(true){
                        line =  bufferedInputStream.readLine();
                        if(line == null){
                            break;
                        }else{
                            System.out.println(line);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }



    }
}
