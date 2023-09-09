package JavaEE.Threads.ThreadPoolExecutor;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseThreadPool {
    static class ForeverTask implements Runnable {
        private final int id;

        public ForeverTask(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "ForeverTask{" +
                    "id=" + id +
                    '}';
        }

        @Override
        public void run() {
            try {
                TimeUnit.DAYS.sleep(365);
            } catch (InterruptedException ignored) {}
        }
    }

    static class NormalTask implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main1(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 10,
                20L, TimeUnit.SECONDS,
                queue
        );
        pool.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 14; i++) {
            NormalTask task = new NormalTask();
            pool.execute(task);
        }

        for (int i = 1; i <= 30; i++) {
            System.out.println(i);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    // 演示拒绝策略
    public static void main2(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 10,
                20L, TimeUnit.SECONDS,
                queue,
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
        );

        for (int i = 0; i < 14; i++) {
            ForeverTask task = new ForeverTask(i);
            System.out.println("提交任务数: " + i);
            pool.execute(task);
        }

        System.out.println(queue.peek());
        ForeverTask task = new ForeverTask(14);
        System.out.println("提交任务数: " + 14);
        pool.execute(task);
        System.out.println(queue.peek());
        task = new ForeverTask(14);
        System.out.println("提交任务数: " + 14);
        pool.execute(task);
        System.out.println(queue.peek());
        task = new ForeverTask(14);
        System.out.println("提交任务数: " + 14);
        pool.execute(task);
        System.out.println(queue.peek());
        task = new ForeverTask(14);
        System.out.println("提交任务数: " + 14);
        pool.execute(task);
        System.out.println(queue.peek());
    }

    // 演示线程数量的变化
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 10,
                20L, TimeUnit.SECONDS,
                queue
        );

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; true; i++) {
            System.out.print("按一次回车，提交一次任务: ");
            scanner.nextLine();
            ForeverTask task = new ForeverTask(i);
            System.out.println("提交任务数: " + i);
            pool.execute(task);
        }
    }
}
