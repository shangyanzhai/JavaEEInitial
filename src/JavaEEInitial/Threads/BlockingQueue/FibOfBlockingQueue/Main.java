package JavaEEInitial.Threads.BlockingQueue.FibOfBlockingQueue;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        // 定义两个阻塞队列
        BlockingQueue<Integer> taskBlockingQueue = new LinkedBlockingQueue<>();
        BlockingQueue<NR> resultBlockingQueue = new LinkedBlockingQueue<>();

        // 创建打印线程
        PrintTask printTask = new PrintTask(resultBlockingQueue);
        Thread printThread = new Thread(printTask, "打印线程");
        printThread.start();

        // 创建计算线程
        CalcTask calcTask = new CalcTask(taskBlockingQueue, resultBlockingQueue);
        for (int i = 0; i < 4; i++) {
            Thread calcThread = new Thread(calcTask, "计算线程-" + i);
            calcThread.start();
        }

        // 主线程做自己的主任务
        Scanner scanner = new Scanner(System.in);
        MainTask mainTask = new MainTask(scanner, taskBlockingQueue);
        mainTask.run(); // 在主线程中自己执行这个任务
    }
}
