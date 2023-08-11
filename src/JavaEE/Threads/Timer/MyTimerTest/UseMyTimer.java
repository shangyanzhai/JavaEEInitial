package JavaEE.Threads.Timer.MyTimerTest;

public class UseMyTimer {
    static class Task1 extends MyTimerTask {
        @Override
        public void run() {
            System.out.println("第一个任务");
        }
    }

    static class Task2 extends MyTimerTask {
        @Override
        public void run() {
            System.out.println("第二个任务");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTimerV2 timer = new MyTimerV2();
        Task1 t1 = new Task1();
        Task2 t2 = new Task2();

        timer.schedule(t1, 5000);
        timer.schedule(t2, 7000);

        while (true) {}
    }
}
