package com.application.q;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Message implements Serializable {
    String messageType;
    User sourceUser;
    Byte Data;
    int sourcePort;
    int sourceIP;
    User DestinationUser;
    Date sendDate;
    boolean issuccess;

    Message reciveMessage(int Port) throws IOException, ClassNotFoundException {
        System.out.println("开始接收信息");
        ServerSocket serverSocket=new ServerSocket(Port);
        Socket socket=serverSocket.accept();
        System.out.println("接收到消息");
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        Message message =(Message) objectInputStream.readObject();
        objectInputStream.close();
        socket.close();
        serverSocket.close();
        return message;
    }
    int sendMessage(Message message,int IP,int Port) throws IOException {
        Socket socket=new Socket(String.valueOf(IP),Port);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(message);
        System.out.println("发送完毕");
        return 1;
    }

}
