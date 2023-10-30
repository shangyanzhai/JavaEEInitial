package JavaEEInitial.NetworkProgramming.Socket.TCP.请求响应;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPDictServer {
    public ServerSocket serverSocket = null;
    public HashMap<String,String> dictMap = new HashMap();
    public TCPDictServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        dictMap.put("cat","猫");
        dictMap.put("dog","狗");
        dictMap.put("fox","狐狸");
        dictMap.put("pig","猪");
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
            try(OutputStream outputStream = socket.getOutputStream()) {
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

                    //进行响应
                    String response = handle(request);
                    PrintWriter writer = new PrintWriter(outputStream);
                    writer.println(response);
                    writer.flush();
//                    outputStream.write(response.getBytes());
//                    outputStream.flush();
                    System.out.printf("接收到客户端请求[%s:%d]:%s,响应内容是:%s \n",
                            socket.getInetAddress().getHostAddress(),
                            socket.getPort(),request,response);

                }
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

    private String handle(String request) {
        return dictMap.getOrDefault(request,"无法翻译");
    }

    public static void main(String[] args) throws IOException {
        TCPDictServer tcpServer = new TCPDictServer(9090);
        tcpServer.start();
    }
}
