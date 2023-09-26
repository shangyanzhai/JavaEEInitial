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

        /**
         * //创立线程池 ExecutorService
         * //ExecutorService是java中的一个异步执行的框架，通过使用ExecutorService可以方便的创建多线程执行环境。
         * 创建ExecutorService
         * 通常来说有两种方法来创建ExecutorService。
         *
         * 第一种方式是使用Executors中的工厂类方法，例如:
         * ExecutorService executor = Executors.newFixedThreadPool(10);
         * 除了newFixedThreadPool方法之外，Executors还包含了很多创建ExecutorService的方法。
         *
         * 第二种方法是直接创建一个ExecutorService， 因为ExecutorService是一个interface，我们需要实例化ExecutorService的一个实现。
         * 这里我们使用ThreadPoolExecutor来举例:
         *
         * ExecutorService executorService =
         *             new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
         *                     new LinkedBlockingQueue<Runnable>());
         */
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
            //此处的Scanner, 是为了更好地解析发送端发过来的数据
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
