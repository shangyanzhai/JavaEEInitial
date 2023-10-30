package JavaEEInitial.Threads.Timer.MyTimerTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class MyTimerV2 {
    // 管理工作线程
    private final Thread worker;
    // 维护一个阻塞的优先级队列
    private final BlockingQueue<MyTimerTask> queue;

    public MyTimerV2() {
        queue = new PriorityBlockingQueue<>();
        WorkTask workTask = new WorkTask(queue);
        worker = new Thread(workTask, "定时器工作线程");
        worker.start();
    }

    public void schedule(MyTimerTask task, long delay) throws InterruptedException {
        // 计算任务要执行的时刻 = 当前时刻 + 延时
        long runAt = System.currentTimeMillis() + delay;
        task.setRunAtTimestamp(runAt);
        // 把任务放入队列中
        queue.put(task);
        synchronized (worker) {
            worker.notify();
//            System.out.println("worker.notify()");
        }
    }

    public void scheduleAtFixedRate(MyTimerTask task, long delay, long period) throws InterruptedException {
        // 计算任务要执行的时刻 = 当前时刻 + 延时
        long runAt = System.currentTimeMillis() + delay;
        task.setRunAtTimestamp(runAt);
        task.setLoop(true);
        task.setPeriod(period);

        // 把任务放入队列中
        queue.put(task);
        synchronized (worker) {
            worker.notify();
//            System.out.println("worker.notify()");
        }
    }
}