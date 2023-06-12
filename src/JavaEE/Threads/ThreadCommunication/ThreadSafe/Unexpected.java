package JavaEE.Threads.ThreadCommunication.ThreadSafe;

/**
 * 什么是线程安全
 * 线程安全：多线程环境下，程序的运行结果 ！！！！100% ！！！！符合预期。
 * 出现过符合预期的结果，能否称为线程安全 —— 不能！
 *
 * 线程安全在不同场合下的不同含义：
 *
 * 形容一个程序是线程安全的：程序的运行结果是 100% 正确的
 *
 * 形容一个类/对象是线程安全的：这个对象可以在多线程环境下使用
 *
 * 数据结构阶段学习过的 ArrayList/LinkedList/PriorityQueue/TreeMap/TreeSet/HashMap/HashSet
 *
 * 数据库阶段学习过的 Connection
 *
 * 都不是线程安全的，即不能在多个线程之间共享同一份这些对象。
 */
//这个是线程不安全的
public class Unexpected {
    private static final int N = 100_0000;
    static int a = 0;

    // 定义一个线程，对 a 做 100_0000  ++ 操作
    static class Add extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                a++;
            }
        }
    }

    // 定义一个线程，对 a 做 100_0000  -- 操作
    static class Sub extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                a--;
            }
        }
    }

    /**
     * // 原则上，当两个线程都结束后，a 的值应该是 0
     * // 但是我们看到的结果不符合预期
     */
    public static void main(String[] args) throws InterruptedException {
        Add t1 = new Add();
        t1.start();

        Sub t2 = new Sub();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(a);
    }
}
