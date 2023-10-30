package JavaEEInitial.Threads.Timer;

import java.util.Timer;
import java.util.TimerTask;

public class UseTimer {
    static class MyTask extends TimerTask {
        @Override
        public void run() {
            // 任务要做的事情是什么
            System.out.println("Hello World");
        }
    }

    public static void main1(String[] args) {
        Timer timer = new Timer();  // 定时器对象
        MyTask task = new MyTask(); // 需要被执行的任务
//        timer.schedule(task, 5000);    // 5s 后执行一次
        // 2s 后第一次执行，随后每 1s 执行一次
        timer.scheduleAtFixedRate(task, 2000, 1000);

        while (true) {} // 通过一个死循环证明任务一定不是主线程执行的
    }

    public static void main(String[] args) {
        MyTimerV1 timer = new MyTimerV1();
        MyTask task = new MyTask();
//        timer.schedule(task,5000);
        timer.scheduleAtFixedRate(task,2000,1000);
        while(true){

        }
    }
}