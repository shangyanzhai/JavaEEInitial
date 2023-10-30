package JavaEEInitial.Threads.BlockingQueue.FibOfBlockingQueue;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

// 给主线程执行的任务
// 利用 scanner 读取输入的 n，把 n 放入任务队列中
public class MainTask implements Runnable {
    private final Scanner scanner;
    private final BlockingQueue<Integer> taskBlockingQueue;

    public MainTask(Scanner scanner, BlockingQueue<Integer> taskBlockingQueue) {
        this.scanner = scanner;
        this.taskBlockingQueue = taskBlockingQueue;
    }


    @Override
    public void run() {
        try {
            while (true) {
                System.out.print("请输入要计算的 n: ");
                int n = scanner.nextInt();
                taskBlockingQueue.put(n);
            }
        } catch (InterruptedException ignored) {}
    }
}