package JavaEE.NetworkProgramming.Socket.TCP.一发一收;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    public ServerSocket serverSocket = null;
    HashMap hashMap = new HashMap(1000);
    public TCPServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    private void start() throws IOException {
        System.out.println("服务器已启动...");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (true){
            Socket socket = serverSocket.accept();
            //把任务交给线程池来处理
            executorService.submit(()->{
                process(socket);
            });

        }

    }

    /**
     * 对请求进行处理
     * @param socket
     */
    public void process(Socket socket){
        try(InputStream inputStream = socket.getInputStream()) {
            //此处的Scanner, 是为了更好的解析发送端发过来的数据
            //把二进制数据转成字符
            Scanner scanner = new Scanner(inputStream);
            while (true){
                if (!scanner.hasNext()){
                    //断开连接
                    System.out.printf("和客户端断开连接[%s:%d]\n",
                            socket.getInetAddress().getHostAddress(),
                            socket.getPort());
                    break;
                }
                String request = scanner.nextLine();
                System.out.printf("接收到客户端请求[%s:%d]:%s \n",
                        socket.getInetAddress().getHostAddress(),
                        socket.getPort(),request);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        TCPServer tcpServer = new TCPServer(9090);
        tcpServer.start();
    }
}
