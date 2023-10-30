package JavaEEInitial.Threads.DemoOfThread;
import java.util.*;

/**
 * 2. 某个执行流因为某些原因阻塞了，但还需要做其他工作，可以使用多线程。
 */
public class BlockDemoOfThreads {
    // O(2^n)
    // 当 n 是 10，执行基本指令数是 1000
    // 当 n 是 20，                100W
    // 当 n 是 30                  10亿
    // 当 n 是 40                  1万亿
    // 执行效率很低，模拟阻塞的情况
    static long fib(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            long r = fib(n);
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();

            new Thread(() -> {
                long r = fib(n);
                System.out.println(r);
            }).start();
        }
    }
}
