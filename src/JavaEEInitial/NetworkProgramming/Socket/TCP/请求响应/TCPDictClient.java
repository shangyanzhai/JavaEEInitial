package JavaEEInitial.NetworkProgramming.Socket.TCP.请求响应;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPDictClient {
    public Socket socket = null;

    public TCPDictClient(String serverIp, int port) throws IOException {
        socket = new Socket(serverIp,port);
    }
    private void start() {
        System.out.println("和服务器建立连接成功....");
        try(OutputStream stream = socket.getOutputStream()) {
            try(InputStream inputStream = socket.getInputStream()) {
                //从控制台获取输入的内容
                Scanner scanner = new Scanner(System.in);
                while (true){
                    System.out.println("请输入要发送的内容:");
                    //输入的信息
                    String request = scanner.nextLine();
                    //通过PrintWrite封装, 写入字节流
                    PrintWriter writer = new PrintWriter(stream);
                    writer.println(request);
                    //一定不能忘了.flush
                    writer.flush();

                    //接收响应
                    Scanner responseScanner = new Scanner(inputStream);
                    String response = responseScanner.nextLine();
                    System.out.printf("发送的内容:%s, 接收的内容:%s\n",request,response);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        TCPDictClient tcpClient = new TCPDictClient("127.0.0.1",9090);
        tcpClient.start();
    }
}
