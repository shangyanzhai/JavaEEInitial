package JavaEE.FileOperateAndIO;

import java.io.FileReader;
import java.io.Reader;

public class FileReaderDemo {
    public static void main(String[] args) {
        try(Reader reader = new FileReader("D:/temp/hello.txt")) {
            char[] buf = new char[1024];
            while (true){
                int num = reader.read(buf);
                if (num ==-1){
                    break;
                }
                for (int i = 0; i < num; i++) {
                    System.out.println(buf[i]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
