package JavaEE.Threads.ThreadCommunication.ThreadSafe.JavaDSOfThreadSafe;
import java.util.*;
public class VectorInThreads {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        vector.add("hello world");

        // Vector 对象是线程安全的
        // 但是以下操作仍然是错误的，依旧不是线程安全的
        // 组合的操作仍然需要我们自行加锁处理
        if(vector.size() == 1){
            String str = vector.get(0);
        }
    }
}
