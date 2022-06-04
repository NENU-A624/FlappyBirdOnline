import org.json.JSONObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args){
        try {
            //System.out.println("Socket 准备就绪...");
            ServerSocket serverSocket = new ServerSocket(9999);  //创建等待连接
            while (true){
                Socket socket = serverSocket.accept();         //接收客户端连接
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                new Thread(new Server_listen(socket)).start();
                new Thread(new Server_send(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
class Server_listen implements Runnable {
    private Socket socket;
    private ObjectInputStream ois;
    Server_listen(Socket socket) {
        this.socket = socket;
        this.ois = ois;
    }

    @Override
    public void run() {                   //监听和接收鸟的位置数据
        try {
            StringBuffer Birdmap = new StringBuffer();
            while (true) {
                Birdmap.append(ois.readObject());         //不是很懂json怎么解，
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
    class Server_send implements Runnable{    //发送给各个客户端鸟的位置数据
        private Socket socket;
        private ObjectOutputStream oos;
        Server_send(Socket socket){
            this.socket = socket;
            this.oos = oos;
        }

        @Override
        public void run() {
            try {
                String BirdGather = new String();
                JSONObject object = new JSONObject();
                object.put("鸟的集合",BirdGather);
                oos.writeObject(object);
                oos.flush();
                }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //json的  接收处理和发送
    //解析坐标   重绘展示鸟
    // 关闭连接不是很理解


