package A624.com.FlappyBirdOnline;

import org.json.JSONObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private  Socket socket;
    public  boolean connection_state = false;

//    public static void main(String[] args){
//        while (!connection_state) {
//            connect();                          //调用下面的方法向服务器请求连接
//            try {
//                Thread.sleep(3000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

    public void connect(){
        try {
            socket = new Socket("192.168.1.9",20000);
            System.out.println("OK");
            connection_state = true;
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            new Thread(new Client_listen(socket,ois)).start();
            new Thread(new Client_send(socket,oos)).start();
//            new Thread(new Client_heart(socket,oos)).start();
        }catch (Exception e){
            e.printStackTrace();
            connection_state = false;
        }
    }

    public void reconnect(){
            System.out.println("正在尝试重新链接.....");
            try {
                connect();
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}
class Client_listen implements Runnable{
    private Socket socket;
    private ObjectInputStream ois;

    Client_listen(Socket socket,ObjectInputStream ois){
        this.socket = socket;
        this.ois = ois;
    }
    @Override
    public void run(){      //接收并实时更新鸟的位置直接绘制不会了，不会解析json和绘制
        try {
            while (true){

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Client_send implements Runnable{
    private Socket socket;
    private ObjectOutputStream oos;

    Client_send(Socket socket, ObjectOutputStream oos){
        this.socket = socket;
        this.oos = oos;
    }
    @Override
    public void run(){
        try {
//            while (true){
//                int X = MyView.returnBirdX();
//                int Y = MyView.returnBirdY();
//                JSONObject object = new JSONObject();
//                object.put("鸟的x坐标",X);
//                object.put("鸟的y坐标",Y);
//                oos.writeObject(object);                            //向服务器发送位置坐标
//                oos.flush();
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

