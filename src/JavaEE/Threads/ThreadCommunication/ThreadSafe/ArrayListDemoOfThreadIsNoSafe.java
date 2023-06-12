package JavaEE.Threads.ThreadCommunication.ThreadSafe;
import java.util.*;

//线程安全在不同场合下的不同含义：
//形容一个程序是线程安全的：程序的运行结果是 100% 正确的
//形容一个类/对象是线程安全的：这个对象可以在多线程环境下使用
//数据结构阶段学习过的 ArrayList/LinkedList/PriorityQueue/TreeMap/TreeSet/HashMap/HashSet
//数据库阶段学习过的 Connection
//都不是线程安全的，即不能在多个线程之间共享同一份这些对象。
/**
 * 该写法是线程不安全的，是不能这么用的
 * 已知此是线程不安全的，只不过因为多线程方面，所以复现相对较难
 */
public class ArrayListDemoOfThreadIsNoSafe {
    private static final int N = 100_0000;
    private static ArrayList<Integer> list = new ArrayList<>();

    private static class Put extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                list.add(i);
            }
        }
    }

    private static class Pop extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N;) {
                if (!list.isEmpty()) {
                    System.out.println(list.remove(0));
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Put t1 = new Put();
        t1.start();
        Pop t2 = new Pop();
        t2.start();
    }
}
