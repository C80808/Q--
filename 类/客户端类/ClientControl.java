package Client;

import Common.Message;
import Common.User;

import javax.print.attribute.standard.Destination;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class ClientControl {
    String ip="";//服务器IP地址
    int port=10086;//默认端口
    ArrayList<User>list=new ArrayList<User>();

    public boolean loginRequest(User user) throws IOException {//登录请求
        Message message=new Message();
        message.sourceUser.setId(user.getId());
        message.sourceUser.setPassword(user.getPassword());
        message.sourceUser.setIp(InetAddress.getLocalHost().getHostAddress());
        message.sourceUser.setEstablishConnectionPort(port);
        message.sendMessage(message,ip,port);
        return true;
    }

    public boolean registerRequest(User user) throws IOException {//注册请求
        Message message=new Message();
        message.sourceUser.setId(user.getId());
        message.sourceUser.setPassword(user.getPassword());
        message.sourceUser.setIp(InetAddress.getLocalHost().getHostAddress());
        message.sourceUser.setEstablishConnectionPort(port);
        message.sendMessage(message,ip,port);
        return true;
    }

    public ArrayList getFeiends(User user){//获取好友信息


        return list;
    }

    public boolean addFrineds(User user) throws IOException {//添加好友
        list.add(user);
        Message message=new Message();
        message.DestinationUser.setId(user.getId());
        message.sendMessage(message, ip,port);

        return true;
    }

    public boolean deleteFrineds(User user) throws IOException {//删除好友
        list.remove(user);
        Message message=new Message();
        message.DestinationUser.setId(user.getId());
        message.sendMessage(message, ip,port);

        return true;
    }

    public <T>boolean modifyPersonallnformationRequest(String RequestType,T Information,User user){//修改个人信息
        if("userName".equals(RequestType)){
            user.setName((String) Information);
            return true;
        }
        if("password".equals(RequestType)){
            user.setPassword((String) Information);
            return true;
        }
        if("icon".equals(RequestType)){
            user.setUserlcon((Image) Information);
            return true;
        }
        return false;
    }

    public <T>T getUserInformation(User user,String RequestType){//获取用户信息
        if("userName".equals(RequestType)){
            return (T) user.getName();
        }
        if("password".equals(RequestType)){
            return (T) user.getPassword();
        }
        if("icon".equals(RequestType)){
            return (T) user.getUserlcon();
        }
        return (T) "查询失败";
    }

    public void userLoginInit(User user) throws IOException, ClassNotFoundException {//用户登录初始化
        while (getLoginStatus(user)==-1);
        receivingRequest();
        Message message=new Message();
        message.messageType="登录成功";
        message.sendMessage(message,ip,port);
        message.reciveMessage(port);

    }

    public int getLoginStatus(User user){//获取登录信息
        if (user.isIsonline()){
            return 1;
        }
        return -1;
    }

    public void connectionToOtherRequest(User SourceUser,User DestinationUser,int establishConnectionPort){//客户端请求与客户端连接


        try {
            Socket s=new Socket(DestinationUser.getIp(),port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receivingRequest(){//持久性接收其他客户端的连接请求
        try {
            ServerSocket ss=new ServerSocket(port);
            connectionEstablish(ss);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean connectionEstablish(ServerSocket ss){//被请求客户端建立连接
        try{
            Socket s = ss.accept();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
