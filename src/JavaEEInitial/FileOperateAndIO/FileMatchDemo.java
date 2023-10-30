package JavaEEInitial.FileOperateAndIO;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class FileMatchDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的目录:");
        String rootPath = scanner.nextLine();
        System.out.println("请输入要查找的内容字符串:");
        String key = scanner.nextLine();
        File rootFile = new File(rootPath);
        if (!rootFile.exists() || !rootFile.isDirectory()){
            return;
        }
        //查找逻辑
        searchDir(rootFile, key);

    }

    /**
     * 查找指定目录下, 包含指定内容的文件
     * @param rootFile
     * @param key
     */
    private static void searchDir(File rootFile, String key) {
        File[] files = rootFile.listFiles();
        if (files==null || files.length==0){
            return;
        }
        for (File f:files){
            if (f.isDirectory()){
                searchDir(f,key);
            }else {
                //判断该文件是否包含指定字符串
                if (matchKey(f,key)){
                    System.out.println(f.getPath());
                }
            }
        }
    }

    /**
     * 判断文件的内容, 是否包含指定字符串
     * true : 包含, false: 不包含
     * @param f
     * @param key
     * @return
     */
    private static boolean matchKey(File f, String key) {
        StringBuilder builder = new StringBuilder();
        try(Reader reader = new FileReader(f)) {
            char[] buf = new char[1024];
            while (true){
                int len = reader.read(buf);
                if (len == -1){
                    break;
                }
                builder.append(buf,0,len);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString().contains(key);
    }
}
