package JavaEEInitial.FileOperateAndIO;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FileOutputStreamDemo {
    public static void main(String[] args) {
        //write(int b)
//        write1();
        //write(byte[] b)
//        write2();
        //write(byte[] b, int off, int len)
//        write3();
        //PrintWriter
//        write4();
        write5();
    }

    private static void write5() {
//        try(PrintWriter writer = new PrintWriter("D:/temp/out.txt")){
//            writer.println("你好,世界");
//            writer.println("你好, Java");
        //如果不写，不能复现，可能是资源关闭之前帮我调用了flush()操作
//            writer.flush();//如果不写flush()是有可能没写进去的，但是 FileOutputStream 不写 flush() 是一定会写进去的 具体是否写进去要看其是否实现/重写了flush()
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        try{
            PrintWriter writer = new PrintWriter("D:/temp/out.txt");
            writer.println("你好,世界");
            writer.println("你好, Java");
//            writer.flush();//如果不写flush()是有可能没写进去的，但是 FileOutputStream 不写 flush() 是一定会写进去的 具体是否写进去要看其是否实现/重写了flush()
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void write4() {
        //FileOutputStream(File file, boolean append)//将boolean类型的append设为true，可以将其设置为追加方式，而不是替换方式
        try(OutputStream outputStream = new FileOutputStream("D:/temp/out4.txt",true)) {
            try(PrintWriter writer = new PrintWriter(outputStream)) {
//                writer.write("你好,世界");//write.write 这种方式不换行
//                writer.write("你好, Java");
                writer.println("你好,世界");//write.println 这种方式换行
                writer.println("你好, Java");

                outputStream.flush();//FileOutputStream 不写 flush() 是一定会写进去的 具体是否写进去要看其是否实现/重写了flush()
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * int write(byte[] b, int off,int len)
     */
    private static void write3() {
        try(OutputStream outputStream = new FileOutputStream("D:/temp/out3.txt")) {
            outputStream.write("hello222".getBytes(),3,2);

            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * void write(byte[] b)
     */
    private static void write2() {
        try(OutputStream outputStream = new FileOutputStream("D:/temp/out2.txt")) {
            outputStream.write("hello222".getBytes());

            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * void write(int b)
     */
    private static void write1() {
        try(OutputStream outputStream = new FileOutputStream("D:/temp/out.txt")) {
            outputStream.write('h');
            outputStream.write('e');
            outputStream.write('l');
            outputStream.write('l');
            outputStream.write('o');

            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
