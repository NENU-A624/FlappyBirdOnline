package A624.com.FlappyBirdOnline;

import org.json.JSONObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    public static boolean connection_state = false;

    public static void main(String[] args){
        while (!connection_state) {
            connect();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void connect(){
        try {
            socket = new Socket("Server的ip", 00000);
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
    public static void reconnect(){
        while (!connection_state){
            System.out.println("正在尝试重新链接.....");
            connect();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
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
    public void run(){
        try {
            while (true){
                System.out.println(ois.readObject());
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
        Scanner scanner = new Scanner(System.in);
        try {
            while (true){
                int X = MyView.returnbirdx();
                int Y = MyView.returnbirdy();
                JSONObject object = new JSONObject();
                object.put("鸟的x坐标",X);
                object.put("鸟的y坐标",Y);
                oos.writeObject(object);
                oos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                socket.close();
                Client.connection_state = false;
                Client.reconnect();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }
}

