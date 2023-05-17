package JavaEE.Threads;

import java.util.concurrent.TimeUnit;

public class CommonThreadMethod {
    //通用方法

    /**
     * //Thread.sleep(Timeout)
     * //外在现象：该语句会使得程序休眠（sleep）一段时间，才继续后序代码的执行。
     * //内部实际上：该线程让出 CPU，并且线程的状态被系统修改为 Timed-Waiting，
     * 直到指定时间过后，系统把线程的状态修改为 Runnable，线程才可能重新被分配 CPU，继续后序代码的执行。
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        System.out.println("A");
        thread.sleep(3 * 1000);

        //变形
        TimeUnit.DAYS.sleep(1);
        TimeUnit.HOURS.sleep(1);
        TimeUnit.MICROSECONDS.sleep(1);
        System.out.println("B");
    }

    /**
     * //Thread.yield()
     * //让出CPU
     * //只是让出 CPU，但状态仍然是 Runnable（就绪），随时可以再次被调度。适合做一些长期任务时，让出 CPU 让其他线程也可以使用 CPU.
     */
    static class PrintThread extends Thread {
        private final String target;
        private final boolean isYield;

        PrintThread(String target, boolean isYield) {
            this.target = target;
            this.isYield = isYield;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(target);
                if (isYield) {
                    Thread.yield();
                }
            }
        }
    }

    public static void main2(String[] args) {
//        PrintThread printA = new PrintThread("A", false);
        PrintThread printA = new PrintThread("A", true);
        PrintThread printB = new PrintThread("B", false);

        printA.start();
        printB.start();
    }


    /**
     * //Thread.currentThread()
     * //获取当前线程的实例引用。
     */
    static class PrintTask implements Runnable {
        @Override
        public void run() {

            Thread thread = Thread.currentThread();
//            System.out.println(thread == this);    // 无法比较
            System.out.println((Runnable)thread == this);
            System.out.println(thread.getName());
        }
    }

    static class PrintName extends Thread {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println(thread == this);
            System.out.println(thread.getName());
        }
    }

    public static void main3(String[] args) {
//        Thread thread = Thread.currentThread();
//
//        System.out.println(thread.getName());

        PrintName t1 = new PrintName();
        PrintName t2 = new PrintName();
        t2.start();
    }

    //线程的常见控制方法

    /**
     * 启动线程 thread.start()
     * 启动线程，即将线程的状态从 NEW 变成 RUNNABLE
     * (实际只是就绪状态，什么时候真正被调度到 CPU 上开始执行线程中的语句，对我们来说是随机的）
     */
    //对比
    public static void main(String[] args) {

    }
}
