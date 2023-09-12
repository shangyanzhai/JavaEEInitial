package JavaEE.DocumentOperateAndIO;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DemoOfFile {
    /** file方法
     *
     * 修饰符及返回值类型    方法签名            说明
     * String            getParent()         返回 File 对象的父目录文件路径
     * String            getName()           返回 FIle 对象的纯文件名称
     * String            getPath()           返回 File 对象的文件路径
     * String            getAbsolutePath()   返回 File 对象的绝对路径
     * String            getCanonicalPath()  返回 File 对象的修饰过的绝对路径
     * boolean           exists()            判断 File 对象描述的文件是否真实存在
     * boolean           isDirectory()       判断 File 对象代表的文件是否是一个目录
     * boolean           isFile()            判断 File 对象代表的文件是否是一个普通文件
     * boolean           createNewFile()     根据 File 对象，自动创建一个空文件。成功创建后返回 true
     * boolean           delete()            根据 File 对象，删除该文件。成功删除后返回 true
     * void              deleteOnExit()      根据 File 对象，标注文件将被删除，删除动作会到 JVM 运行结束时才会进行
     * String[]          list()              返回 File 对象代表的目录下的所有文件名
     * File[]            listFiles()         返回 File 对象代表的目录下的所有文件，以 File 对象表示
     * boolean           mkdir()             创建 File 对象代表的目录
     * boolean           mkdirs()            创建 File 对象代表的目录，如果必要，会创建中间目录
     * boolean           renameTo(File dest) 进行文件改名，也可以视为我们平时的剪切、粘贴操作
     * boolean           canRead()           判断用户是否对文件有可读权限
     * boolean           canWrite()          判断用户是否对文件有可写权限
     */
    public static void main(String[] args) throws IOException {
        //分隔符
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);

        //构造函数
        File file = new File("D:/temp/aaa.txt");
        File file1 = new File("D:/temp","aaa.txt");
        File file2 = new File(new File("D:/temp"),"aaa.txt");

        System.out.println("======================");
        System.out.println(file.getParent());//返回 File 对象的父目录文件路径
        System.out.println(file.getName());//返回 FIle 对象的纯文件名称

        System.out.println("======================");
        //相对路径: 当前目录下的hello.txt
        file = new File("./hello.txt");
        System.out.println(file.getPath());//返回 File 对象的文件路径（文件路径是相对路径返回相对路径，文件是绝对路径则返回绝对路径）
        System.out.println(file.getAbsoluteFile());//返回 File 对象的绝对路径
        System.out.println(file.getCanonicalPath());//返回 File 对象的绝对路径

        //判断文件
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

        //删除文件
        System.out.println("======================");
        File file3 = new File("D:/temp/hello.txt");
        if (file3.exists()){
//            file.delete();//根据 File 对象，删除该文件。成功删除后返回 true
            file3.deleteOnExit();//根据 File 对象，标注文件将被删除，删除动作会到 JVM 运行结束时才会进行
            System.out.println(file3.exists());
        }

        //返回文件目录下所有文件名
        System.out.println("======================");
        File file4 = new File("D:\\temp");
        String[] files = file4.list();
//        Arrays.stream(files).forEach(System.out::print);
        Arrays.stream(files).forEach(x -> System.out.print(x + " "));

        File[] files1 = file4.listFiles();
        Arrays.stream(files1).forEach(x-> System.out.print(x.getName() + " "));

        //创建代表目录
        System.out.println("======================");
//        File file5 = new File("D:\\temp\\aa");
        File file5 = new File("D:\\temp\\aa\\bb");
        file5.mkdir();//false
        file5.mkdirs();//true

        //判断用户对文件权限
        System.out.println("======================");
        File file6 = new File("D:/temp/aaa.txt");
        System.out.println(file4.canRead());//判断用户是否对文件有可读权限
        System.out.println(file4.canWrite());//判断用户是否对文件有可读权限

        System.out.println("======================");
        //进行文件改名，也可以视为我们平时的剪切、粘贴操作
        File file7 = new File("D:/temp/cat.jpg");
        file5.renameTo(new File("D:/temp/aa/bb/cat1.jpg"));
    }
}
