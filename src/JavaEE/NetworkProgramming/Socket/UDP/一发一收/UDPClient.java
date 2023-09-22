package JavaEE.NetworkProgramming.Socket.UDP.一发一收;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public DatagramSocket socket = null;
    public static final String serverIp = "127.0.0.1";
    public static final int serverPort = 9090;

    public UDPClient() throws SocketException {
        //系统随机分配端口号
        socket = new DatagramSocket();
    }

    private void start() throws IOException {
        System.out.println("客户端已启动...");
        //发送数据
        /**
         * send(DatagramPacket p)
         * DatagramPacket : 发送的内容
         */
        //发送内容
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要发送给服务器的信息:");
            String request = scanner.nextLine();
            //接收方的地址
            DatagramPacket packet = new DatagramPacket(request.getBytes(), 0,
                    request.getBytes().length, InetAddress.getByName(serverIp), serverPort);

            socket.send(packet);
        }

    }

    public static void main(String[] args) throws IOException {
        UDPClient udpClient = new UDPClient();
        udpClient.start();
    }
}