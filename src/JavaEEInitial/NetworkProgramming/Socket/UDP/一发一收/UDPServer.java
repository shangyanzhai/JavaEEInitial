package JavaEEInitial.NetworkProgramming.Socket.UDP.一发一收;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public DatagramSocket socket = null;
    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }
    private void start() throws IOException {
        //接收数据
        System.out.println("服务器启动成功....");
        /**
         * receive(DatagramPacket p)
         * DatagramPacket: 用来接收数据
         */
        while (true){
            byte[] buf = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length);
            socket.receive(datagramPacket);

            String request = new String(datagramPacket.getData(),0,
                    datagramPacket.getLength(),"UTF-8");
            //数据处理
            System.out.printf("接收到客户端数据[%s:%d]:%s\n",
                    datagramPacket.getAddress().getHostAddress(),
                    datagramPacket.getPort(),request);
        }

    }
    public static void main(String[] args) throws IOException {
        //启动服务器
        UDPServer udpServer = new UDPServer(9090);
        udpServer.start();
    }


}
