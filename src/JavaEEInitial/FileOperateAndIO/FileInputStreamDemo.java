package JavaEEInitial.FileOperateAndIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 1.abstract int    read()    一次只能读一个字节 （byte）
 *     他的返回值是abstract int类型 , 因为返回 -1 的时候代表读完了
 *     因为 -128 ～ 127 或 0 ～ 255 (无符号) 每一个都有都有固定对应的值 , 所以 需要定义一个比 byte 更大的返回值
 *     为什么不选择使用short呢 ？ 是因为文本文件同样存在这类方法，为了统一 所以选择 int 类型
 *
 * 2.int    read(byte[] b)    一次读多个字节，返回值是读取数据的长度，读取的数据存放在我传入的byte[] b 数组里
 *
 * 3.int    read(byte[] b,int off,int len)
 *                            一次读多个字节，读取的数据按照我传入的 offset 位置开始放入，一次希望读取长度为len的数据，返回本次读取数据的长度
 *
 * 4.void   close()     资源关闭
 */
public class FileInputStreamDemo {
    public static void main(String[] args) {
        //read()方法读取数据
//        read1();
        //read()方法的简化写法
//        read2();
        //read(byte[] b, int off, int len)
//        read3();
        //读取中文文档
//        read4();
        //使用Scanner来读文件
        read5();
    }

    private static void read5() {
//        Scanner scanner = new Scanner("D:/temp/hello.txt");//直接传进去一个地址
//        while (scanner.hasNext()){
//            String str = scanner.nextLine();
//            System.out.println(str);
//        }
        try(InputStream inputStream = new FileInputStream("D:/temp/hello.txt")) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()){
                String str = scanner.nextLine();
                System.out.println(str);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 在文本文件中，文本文件只有中文或者中文字符，该类方法还能实现，一但文本文件中出现中英文混合，则会出现乱码
     */

    private static void read4() {
        try(InputStream inputStream = new FileInputStream("D:/temp/hello.txt")) {
            byte[] buf = new byte[1024];
            while (true){
                int num = inputStream.read(buf);
                if (num == -1){
                    break;
                }
                for (int i = 0; i < num; i+=3) {
                    System.out.println(new String(buf,i,3));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void read3() {
        try(InputStream inputStream = new FileInputStream("D:/temp/hello.txt")) {
            byte[] buf = new byte[1024];
            while (true){
                int num = inputStream.read(buf);
                if (num == -1){
                    break;
                }
                for (int i = 0; i < num; i++) {
                    System.out.println((char)buf[i]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过read()方法读取数据
     */
    public static void read1(){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:/temp/hello.txt");
            while (true){
                int b = inputStream.read();
                if (b == -1){
                    break;
                }
                System.out.println((char)b);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void read2(){
        /**
         * try(InputStream inputStream = new FileInputStream("D:/temp/hello.txt")){//public abstract class InputStream implements Closeable
         *
         * }catch(IOException e){
         *
         * }
         *
         * 之所以能直接把对象定义在try的括号中是因为 InputStream 实现了 Closeable 接口
         * 他会自动的将定义的流关闭
         */
        try(InputStream inputStream = new FileInputStream("D:/temp/hello.txt")) {//public abstract class InputStream implements Closeable
            while (true){
                /**
                 * // abstract int    read()
                 * //一次只能读一个字节 （byte）
                 * //他的返回值是abstract int类型
                 * //因为返回 -1 的时候代表读完了
                 * //因为 -128 ～ 127 或 0 ～ 255 每一个都有都有固定对应的值
                 * //所以 需要定义一个比 byte 更大的返回值
                 * //为什么不选择使用short呢 是因为文本文件同样存在这类方法
                 * //为了统一 所以选择 int 类型
                 */
                int b = inputStream.read();
                if (b == -1){
                    break;
                }
                System.out.println((char)b);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
