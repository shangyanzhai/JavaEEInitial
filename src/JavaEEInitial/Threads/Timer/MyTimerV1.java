package JavaEEInitial.Threads.Timer;

import java.util.TimerTask;

public class MyTimerV1 {
    public void schedule(TimerTask task, long delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                task.run(); // 在当前线程执行该任务
            } catch (InterruptedException ignored) {}
        }).start();
    }

    public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                while (true) {
                    task.run(); // 在当前线程执行该任务
                    Thread.sleep(period);
                }
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
