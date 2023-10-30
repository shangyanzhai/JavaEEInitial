package JavaEEInitial.NetworkProgramming.Socket.UDP.请求响应;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * 字典客户端
 */
public class UDPDictClient {
    public DatagramSocket socket = null;
    public static final String serverIp = "127.0.0.1";
    public static final int serverPort = 9090;
    public UDPDictClient() throws SocketException {
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
        while (true){
            System.out.println("请输入要发送给服务器的信息:");
            String request = scanner.nextLine();
            //接收方的地址
            DatagramPacket packet = new DatagramPacket(request.getBytes(),0,
                    request.getBytes().length, InetAddress.getByName(serverIp),serverPort);

            socket.send(packet);
            //接收数据
            byte[] buf = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buf,buf.length);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0, responsePacket.getLength(),"UTF-8");
            System.out.printf("发送内容:%s,接收到服务器响应:%s\n",request,response);
        }

    }
    public static void main(String[] args) throws IOException {
        UDPDictClient udpClient = new UDPDictClient();
        udpClient.start();
    }


}
