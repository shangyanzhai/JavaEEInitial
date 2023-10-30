package JavaEEInitial.NetworkProgramming.Socket.UDP.请求响应;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

/**
 * 字典服务
 */
public class UDPDictServer {
    public DatagramSocket socket = null;
    public HashMap<String,String> dictMap = null;
    public UDPDictServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
        dictMap = new HashMap<>(4);
        dictMap.put("cat","猫");
        dictMap.put("dog","狗");
        dictMap.put("fox","狐狸");
        dictMap.put("pig","猪");
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
            DatagramPacket clientPacket = new DatagramPacket(buf,buf.length);
            socket.receive(clientPacket);
            String request = new String(clientPacket.getData(),0,
                    clientPacket.getLength(),"UTF-8");
            //数据处理

            //根据请求, 进行响应
            String response = handle(request);

            DatagramPacket packet = new DatagramPacket(response.getBytes(),
                    response.getBytes().length,clientPacket.getSocketAddress());
            socket.send(packet);

            System.out.printf("接收到客户端数据[%s:%d]:%s,响应内容: %s\n",
                    clientPacket.getAddress().getHostAddress(),
                    clientPacket.getPort(),request,response);
        }

    }

    private String handle(String request) {
        return  dictMap.getOrDefault(request,"无法翻译");
    }

    public static void main(String[] args) throws IOException {
        //启动服务器
        UDPDictServer udpServer = new UDPDictServer(9090);
        udpServer.start();
    }


}
