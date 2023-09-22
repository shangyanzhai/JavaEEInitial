package JavaEE.NetworkProgramming.Socket.TCP.一发一收;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public Socket socket = null;

    public TCPClient(String serverIp,int port) throws IOException {
        socket = new Socket(serverIp,port);
    }
    private void start() {
        System.out.println("和服务器建立连接成功....");
        try(OutputStream stream = socket.getOutputStream()) {
            //从控制台获取输入的内容
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.printf("请输入要发送的内容:");
                //输入的信息
                String request = scanner.nextLine();
                //通过PrintWrite封装, 写入字节流
                PrintWriter writer = new PrintWriter(stream);
                writer.println(request);
                //一定不能忘了.flush
                writer.flush();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        TCPClient tcpClient = new TCPClient("127.0.0.1",9090);
        tcpClient.start();
    }
}
