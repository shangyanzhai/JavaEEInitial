package JavaEE.DocumentOperateAndIO;

import java.io.FileWriter;
import java.io.Writer;

public class FileWriterDemo {
    public static void main(String[] args) {
        try(Writer writer = new FileWriter("D:/temp/write.txt")) {
            writer.write("你好, Java200");
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
