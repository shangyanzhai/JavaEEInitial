package JavaEEInitial.FileOperateAndIO;

import java.io.*;
import java.util.Scanner;

public class FileCopyDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入源文件:");
        String inputFilePath = scanner.nextLine();
        System.out.println("请输入目标文件:");
        String outputFilePath = scanner.nextLine();
        File inputFile = new File(inputFilePath);
        if (!inputFile.exists() || !inputFile.isFile()){
            System.out.println("源文件不存在");
            return;
        }
        File outputFile = new File(outputFilePath);
        copy(inputFile,outputFile);
    }

    private static void copy(File inputFile, File outputFile) {
        //先读再写
        try(InputStream inputStream = new FileInputStream(inputFile)) {
            try(OutputStream outputStream = new FileOutputStream(outputFile)) {
                byte[] buf = new byte[1024];
                while (true){
                    int len = inputStream.read(buf);
                    //返回结果为-1, 表示文件读取结束
                    if (len==-1){
                        break;
                    }
                    //文件的写入
                    outputStream.write(buf,0,len);
                    outputStream.flush();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
