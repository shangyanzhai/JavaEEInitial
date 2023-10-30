package JavaEEInitial.Threads.ThreadPoolExecutor.createMyThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;

/**
 * 只有正式工 - 按需创建(只有有任务才开始创建)
 */
public class ThreadPoolV2 {
    static class WorkerProducer implements Runnable {
        private Runnable firstTask;
        private final BlockingQueue<Runnable> queue;

        WorkerProducer(Runnable firstTask, BlockingQueue<Runnable> queue) {
            this.firstTask = firstTask;
            this.queue = queue;
        }

        @Override
        public void run() {
            firstTask.run();    // 执行第一个任务
            firstTask = null;   // 为了让对象被释放

            try {
                while (true) {
                    Runnable task = queue.take();
                    task.run();
                }
            } catch (InterruptedException ignored) {}
        }
    }

    private int countOfThreads;
    private final int corePoolSize;
    private final BlockingQueue<Runnable> queue;

    public ThreadPoolV2(int corePoolSize, BlockingQueue<Runnable> queue) {
        this.countOfThreads = 0;
        this.corePoolSize = corePoolSize;
        this.queue = queue;
    }

    // 这个方法可能被多个线程调用
    public synchronized void execute(Runnable command) {
        if (countOfThreads < corePoolSize) {
            // 需要去创建线程
            WorkerProducer workerProducer = new WorkerProducer(command, queue);
            new Thread(workerProducer).start();
            countOfThreads++;
        } else {
            if (!queue.offer(command)) {
                throw new RejectedExecutionException();
            }
        }
    }
}
