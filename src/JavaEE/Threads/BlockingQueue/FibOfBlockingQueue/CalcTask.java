package JavaEE.Threads.BlockingQueue.FibOfBlockingQueue;

import java.util.concurrent.BlockingQueue;

// 给计算线程的任务
// 从计算队列中读取 n，计算 fib(n)，将结果放入结果队列
public class CalcTask implements Runnable {
    public CalcTask(BlockingQueue<Integer> taskBlockingQueue, BlockingQueue<NR> resultBlockingQueue) {
        this.taskBlockingQueue = taskBlockingQueue;
        this.resultBlockingQueue = resultBlockingQueue;
    }

    private static long fib(int n) {
        if (n < 0) {
            return -1;
        }

        if (n <= 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    private final BlockingQueue<Integer> taskBlockingQueue;
    private final BlockingQueue<NR> resultBlockingQueue;

    @Override
    public void run() {
        try {
            while (true) {
                int n = taskBlockingQueue.take();
                long r = fib(n);
                resultBlockingQueue.put(new NR(n, r));
            }
        } catch (InterruptedException ignored) {}
    }
}
