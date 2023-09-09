package JavaEE.Threads.ThreadPoolExecutor.createMyThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;

/**
 * 只有正式员工 + 提前创建好线程 + 拒绝策略是抛出异常
 */
public class ThreadPoolV1 {
    private static class WorkerProduce implements Runnable {
        private final BlockingQueue<Runnable> queue;

        private WorkerProduce(BlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Runnable task = queue.take();
                    task.run();
                }
            } catch (InterruptedException ignored) {};
        }
    }

    private final int corePoolSize;
    private final BlockingQueue<Runnable> queue;

    public ThreadPoolV1(int corePoolSize, BlockingQueue<Runnable> queue) {
        this.corePoolSize = corePoolSize;
        this.queue = queue;

        createWorkerThread(queue, corePoolSize);
    }

    private void createWorkerThread(BlockingQueue<Runnable> queue, int corePoolSize) {
        for (int i = 0; i < corePoolSize; i++) {
            Thread thread = new Thread(new WorkerProduce(queue), "线程池-线程-" + i);
            thread.start();
        }
    }

    public void execute(Runnable command) {
        if (!queue.offer(command)) {    // 不要使用阻塞的方式
            throw new RejectedExecutionException();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
        ThreadPoolV1 pool = new ThreadPoolV1(10, queue);
        for (int i = 0; i < 20; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": Hello World");
                }
            });
            Thread.sleep(50);
        }
    }
}
