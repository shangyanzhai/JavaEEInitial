package JavaEEInitial.Threads.BlockingQueue.FibOfBlockingQueue;

import java.util.concurrent.BlockingQueue;

// 给打印线程的任务
// 从打印队列获取 <n, r> 对，打印结果
public class PrintTask implements Runnable {
    private final BlockingQueue<NR> resultBlockingQueue;

    public PrintTask(BlockingQueue<NR> resultBlockingQueue) {
        this.resultBlockingQueue = resultBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                NR nr = resultBlockingQueue.take();
                System.out.printf("fib(%d) 的结果是 %d\n", nr.n, nr.r);
            }
        } catch (InterruptedException ignored) {}
    }
}
