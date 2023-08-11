package JavaEE.Threads.Timer.MyTimerTest;

import java.util.concurrent.BlockingQueue;

public class WorkTask implements Runnable {
    private final BlockingQueue<MyTimerTask> queue;

    public WorkTask(BlockingQueue<MyTimerTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
//                System.out.println("准备获取任务");
                MyTimerTask task = queue.take();
//                System.out.println("获取到任务: " + task.getRunAtTimestamp());
                long now = System.currentTimeMillis();
                if (task.getRunAtTimestamp() <= now) {
                    // 这个应该要被执行了
                    task.run();
                } else {
                    // 任务的时间还没到
                    queue.put(task);
                    long delay = task.getRunAtTimestamp() - now;
                    synchronized (Thread.currentThread()) {
//                        System.out.println("wait(" + delay + ")");
                        Thread.currentThread().wait(delay);
                    }
                }
            }
        } catch (InterruptedException ignored) {}
    }
}