package JavaEE.Threads.ThreadPoolExecutor.createMyThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 都是临时工 + 按需创建
 */
public class ThreadPoolV3 {
    static class WorkerProducer implements Runnable {
        private Runnable firstTask;
        private final BlockingQueue<Runnable> queue;
        private final long timeout;
        private final TimeUnit unit;

        WorkerProducer(Runnable firstTask, BlockingQueue<Runnable> queue, long timeout, TimeUnit unit) {
            this.firstTask = firstTask;
            this.queue = queue;
            this.timeout = timeout;
            this.unit = unit;
        }

        @Override
        public void run() {
            firstTask.run();    // 执行第一个任务
            firstTask = null;   // 为了让对象被释放

            try {
                while (true) {
                    Runnable task = queue.poll(timeout, unit);
                    if (task == null) {
                        // 超过 timeout 时间仍然没有取到任务
                        break;
                    }
                    task.run();
                }
            } catch (InterruptedException ignored) {}
        }
    }

    private int countOfThreads;
    private final int corePoolSize;
    private final BlockingQueue<Runnable> queue;
    private final long timeout;
    private final TimeUnit unit;

    public ThreadPoolV3(int corePoolSize, BlockingQueue<Runnable> queue, long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
        this.countOfThreads = 0;
        this.corePoolSize = corePoolSize;
        this.queue = queue;
    }

    // 这个方法可能被多个线程调用
    public synchronized void execute(Runnable command) {
        if (countOfThreads < corePoolSize) {
            // 需要去创建线程
            WorkerProducer workerProducer = new WorkerProducer(command, queue, timeout, unit);
            new Thread(workerProducer).start();
            countOfThreads++;
        } else {
            if (!queue.offer(command)) {
                throw new RejectedExecutionException();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolV3 pool = new ThreadPoolV3(
                10,
                new ArrayBlockingQueue<>(3),
                20, TimeUnit.SECONDS
        );

        for (int i = 0; i < 20; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }
}
