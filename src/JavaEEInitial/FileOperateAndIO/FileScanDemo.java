package JavaEEInitial.FileOperateAndIO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileScanDemo {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //输入要查找的目录
        System.out.println("请输入要查找的目录:");
        String rootPath = scanner.nextLine();
        System.out.println("请输入要查找的文件:");
        String fileName = scanner.nextLine();
        File rootFile = new File(rootPath);
        if (!rootFile.isDirectory()) {
            System.out.println("目录不存在");
            return;
        }
        //1. 扫描
        //用list来存储扫描出的文件
        List<File> toDeleFileList = new ArrayList<>();
        scan(rootFile, fileName, toDeleFileList);
        //2.确认是否删除
        confirmDelete(toDeleFileList);
    }

    private static void confirmDelete(List<File> toDeleFileList) {
        if (toDeleFileList.size() == 0) {
            return;
        }
        for (File file : toDeleFileList) {

            System.out.println("是否删除文件:" + file.getAbsolutePath());
            //获取用户的输入情况
            String confirm = scanner.nextLine();
            //使用 equals 方法时，常量写在前面
            //为了保证前面的一定不为null
            if ("y".equals(confirm)) {
                file.delete();
            }
        }
    }

    private static void scan(File rootFile, String fileName, List<File> toDeleFileList) {
        File[] files = rootFile.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                scan(f, fileName, toDeleFileList);
            } else {
                if (f.getName().equals(fileName)) {
                    toDeleFileList.add(f);
                }
            }
        }
    }
}
