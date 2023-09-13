package JavaEE.DocumentOperateAndIO;

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

        try{
            PrintWriter writer = new PrintWriter("D:/temp/out.txt");
            writer.println("你好,世界");
            writer.println("你好, Java");
//            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void write4() {
        try(OutputStream outputStream = new FileOutputStream("D:/temp/out4.txt",true)) {
            try(PrintWriter writer = new PrintWriter(outputStream)) {
                writer.println("你好,世界");
                writer.println("你好, Java");

                outputStream.flush();
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
