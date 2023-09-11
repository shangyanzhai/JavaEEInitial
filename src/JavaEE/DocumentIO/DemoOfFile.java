package JavaEE.DocumentIO;

import java.io.File;
import java.io.IOException;

public class DemoOfFile {
    public static void main(String[] args) throws IOException {
        //分隔符
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);

        //构造函数
        File file = new File("D:/temp/aaa.txt");
        File file1 = new File("D:/temp","aaa.txt");
        File file2 = new File(new File("D:/temp"),"aaa.txt");

        System.out.println(file.getParent());
        System.out.println(file.getName());
        System.out.println("==================");
        //相对路径: 当前目录下的hello.txt
        file = new File("./hello.txt");
        System.out.println(file.getPath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getCanonicalPath());

        //其他操作
        System.out.println("======================");
        //判断文件是否存在
        System.out.println(file.exists());
        //判断是否是目录
        System.out.println(file.isDirectory());
        //判断是否是普通文件
        System.out.println(file.isFile());
//        file = new File("D:\\temp\\hello.txt");
//        System.out.println(file.isFile());

        file = new File("D:/temp/aaa.txt");
        if (!file.exists()){
            //如果文件不存在, 就创建文件
            file.createNewFile();
        }


    }
}
